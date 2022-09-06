package com.uzabase.playtest.db

import io.mockk.*
import org.assertj.db.api.Assertions
import org.assertj.db.type.Source
import org.dbunit.database.DatabaseConfig
import org.dbunit.database.IDatabaseConnection
import org.dbunit.ext.mysql.MySqlDataTypeFactory
import org.dbunit.ext.mysql.MySqlMetadataHandler
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.sql.Connection
import java.sql.DriverManager

data class User(
    val user_id: Int,
    val name: String
) : Entity("user")

internal class DatabaseTest {
    companion object {
        val conn: Connection =
            DriverManager.getConnection("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", "")

        @AfterAll
        fun afterAll() {
            conn.close()
        }
    }

    val target = Database(
        driverClass = org.h2.Driver::class.qualifiedName!!,
        url = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        username = "sa",
        password = "",
        schema = "test_schema"
    )

    @BeforeEach
    fun setup() {
        conn.createStatement().execute("CREATE SCHEMA IF NOT EXISTS test_schema")
        conn.createStatement().execute(
            """
            CREATE TABLE IF NOT EXISTS test_schema.todo (
            todo_id INTEGER primary key,
            memo varchar 
            )
        """.trimIndent()
        )
        conn.createStatement().execute(
            """
            CREATE TABLE IF NOT EXISTS test_schema.user (
            user_id INTEGER primary key,
            name varchar
            )
        """.trimIndent()
        )
        conn.createStatement().execute("delete from test_schema.todo;")
        conn.createStatement().execute("delete from test_schema.user;")
    }

    @AfterEach
    fun after() {
        unmockkAll()
    }

    @Test
    fun `csvからテーブルにデータをcleanInsertする`() {
        target.cleanInsert(File("src/test/resources/database"))
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
        target.cleanInsert(File("src/test/resources/database"), true)
        val table = org.assertj.db.type.Table(
            Source("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", ""),
            "test_schema.todo"
        )
        Assertions.assertThat(table).row(2)
            .value("memo").isNull
    }

    @Test
    fun `変換用のマップが指定されたとき、空文字を変換して、cleanInsertする`() {
        target.cleanInsert(File("src/test/resources/database"), false, mapOf("change" to "after"))

        val table = org.assertj.db.type.Table(
            Source("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", ""),
            "test_schema.todo"
        )
        Assertions.assertThat(table).row(3)
            .value("memo").isEqualTo("after")
    }

    @Test
    fun `csvからテーブルにデータをinsertする`() {
        conn.createStatement().execute(
            """
            INSERT INTO test_schema.todo VALUES (0, 'memo')
        """.trimIndent()
        )
        target.insert(File("src/test/resources/database"))

        val table = org.assertj.db.type.Table(
            Source("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", ""),
            "test_schema.todo"
        )
        Assertions.assertThat(table)
            .row(0).value("todo_id").isEqualTo(0)
        Assertions.assertThat(table)
            .row(1).value("todo_id").isEqualTo(1)
    }

    @Test
    fun `テーブルの1レコードをinsertする`() {
        val table = org.assertj.db.type.Table(
            Source("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", ""),
            "test_schema.user"
        )
        target.insert(User(1, "taro"))
        Assertions.assertThat(table)
            .row(0).value("user_id").isEqualTo(1)
        Assertions.assertThat(table)
            .row(0).value("name").isEqualTo("taro")
    }

    @Test
    fun `テーブルの1レコードをdeleteする`() {
        val table = org.assertj.db.type.Table(
            Source("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", ""),
            "test_schema.user"
        )
        conn.createStatement().execute(
            """
            INSERT INTO test_schema.user VALUES (1, 'taro')
        """.trimIndent()
        )

        target.delete(User(1, "taro"))
        Assertions.assertThat(table).isEmpty
    }

    @Test
    fun `指定したテーブルをtruncateする`() {
        val todo = org.assertj.db.type.Table(
            Source("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", ""),
            "test_schema.todo"
        )
        val user = org.assertj.db.type.Table(
            Source("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", ""),
            "test_schema.user"
        )
        conn.createStatement().execute(
            """
            INSERT INTO test_schema.todo VALUES (1, 'memo')
        """.trimIndent()
        )
        conn.createStatement().execute(
            """
            INSERT INTO test_schema.user VALUES (1, 'taro')
        """.trimIndent()
        )

        target.truncate("todo", "user")

        Assertions.assertThat(todo).isEmpty
        Assertions.assertThat(user).isEmpty
    }

    @Test
    fun DriverがPostgresの場合Postgres用のデータタイプの設定を有効にする() {
        val postgresDb = Database(
            driverClass = "org.postgresql.Driver",
            url = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
            username = "sa",
            password = "",
            schema = "test_schema"
        )
        val connection = mockk<IDatabaseConnection>()
        val config = mockk<DatabaseConfig>()

        every { connection.config } returns config
        every { config.setProperty(any(), any()) } just runs
        postgresDb.setConfig(connection)

        verify { config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, any<PostgresqlDataTypeFactory>()) }
    }

    @Test
    fun DriverがMySQLの場合MySQL用の設定を有効にする() {
        val mysqlDb = Database(
            driverClass = "com.mysql.jdbc.Driver",
            url = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
            username = "sa",
            password = "",
            schema = "test_schema"
        )
        val connection = mockk<IDatabaseConnection>()
        val config = mockk<DatabaseConfig>()

        every { connection.config } returns config
        every { config.setProperty(any(), any()) } just runs
        mysqlDb.setConfig(connection)

        verify { config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, any<MySqlDataTypeFactory>()) }
        verify { config.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, any<MySqlMetadataHandler>()) }
    }

    @Test
    fun DriverがMySQL8の場合MySQL用の設定を有効にする() {
        val mysqlDb = Database(
            driverClass = "com.mysql.cj.jdbc.Driver",
            url = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
            username = "sa",
            password = "",
            schema = "test_schema"
        )
        val connection = mockk<IDatabaseConnection>()
        val config = mockk<DatabaseConfig>()

        every { connection.config } returns config
        every { config.setProperty(any(), any()) } just runs
        mysqlDb.setConfig(connection)

        verify { config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, any<MySqlDataTypeFactory>()) }
        verify { config.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, any<MySqlMetadataHandler>()) }
    }
}