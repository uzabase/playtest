package com.uzabase.playtest.db

import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.assertj.db.api.Assertions
import org.assertj.db.type.Source
import org.dbunit.operation.DatabaseOperation
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.sql.Connection
import java.sql.DriverManager

internal class DatabaseTest {
    companion object {
        val conn: Connection =
            DriverManager.getConnection("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", "")

        @AfterAll
        fun afterAll() {
            conn.close()
        }
    }

    @BeforeEach
    fun setup() {
        TableTest.conn.createStatement().execute("CREATE SCHEMA IF NOT EXISTS test_schema")
        TableTest.conn.createStatement().execute(
            """
            CREATE TABLE IF NOT EXISTS test_schema.todo (
            todo_id INTEGER primary key,
            memo varchar 
            )
        """.trimIndent()
        )
        TableTest.conn.createStatement().execute("delete from test_schema.todo;")
    }

    @AfterEach
    fun after() {
        unmockkAll()
    }

    @Test
    fun `csvからテーブルにデータをcleanInsertする`() {
        val database =
            Database(
                driverClass = org.h2.Driver::class.qualifiedName!!,
                url = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
                username = "sa",
                password = "",
                schema = "test_schema"
            )
        database.cleanInsert(File("src/test/resources/database"))

        val table = org.assertj.db.type.Table(
            Source("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", ""),
            "test_schema.todo"
        )

        Assertions.assertThat(table).row(0)
            .value().isEqualTo(1)
            .value().isEqualTo("test text")
        Assertions.assertThat(table).row(1)
            .value().isEqualTo(2)
            .value().isEqualTo("test text2")
    }

    @Test
    fun `オプションが指定されたとき、空文字を変換して、cleanInsertする`() {
        mockkStatic(DatabaseOperation::class)
        val database =
            Database(
                driverClass = org.h2.Driver::class.qualifiedName!!,
                url = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
                username = "sa",
                password = "",
                schema = "test_schema"
            )
        database.cleanInsert(File("src/test/resources/database"), true)

        val table = org.assertj.db.type.Table(
            Source("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", ""),
            "test_schema.todo"
        )
        Assertions.assertThat(table).row(2)
            .value("memo").isNull
    }

    @Test
    fun `変換用のマップが指定されたとき、空文字を変換して、cleanInsertする`() {
        mockkStatic(DatabaseOperation::class)
        val database =
            Database(
                driverClass = org.h2.Driver::class.qualifiedName!!,
                url = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
                username = "sa",
                password = "",
                schema = "test_schema"
            )
        database.cleanInsert(File("src/test/resources/database"), false, mapOf("change" to "after"))

        val table = org.assertj.db.type.Table(
            Source("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", ""),
            "test_schema.todo"
        )
        Assertions.assertThat(table).row(3)
            .value("memo").isEqualTo("after")
    }
}