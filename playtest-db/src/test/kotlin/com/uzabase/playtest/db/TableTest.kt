package com.uzabase.playtest.db

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.sql.Connection
import java.sql.DriverManager

internal class TableTest {
    companion object {
        val conn: Connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", "")

        @AfterAll
        fun afterAll() {
            conn.close()
        }
    }

    @BeforeEach
    fun setup() {
        conn.createStatement().execute("CREATE SCHEMA IF NOT EXISTS test_schema")
        conn.createStatement().execute("""
            CREATE TABLE test_schema.test_table (
            id UUID primary key,
            string_column text
            )
        """.trimIndent())

        conn.createStatement().execute("""
            INSERT INTO test_schema.test_table VALUES ('bf9626ab-6ecd-4f15-be70-ac88fe4ba0f0', 'value1')
        """.trimIndent())
    }

    @Test
    fun 指定した条件で取得したレコードから任意のカラムに入っている文字列を取得する() {
        // given
        val table = Table("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", "", "test_schema", "test_table")
        // when
        val actual = table.where("string_column", "value1")
        // then
        actual.first().column("string_column").value shouldBeEqualTo "value1"

    }
}
