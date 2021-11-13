package com.uzabase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Types
import java.time.OffsetDateTime
import java.time.ZoneId

internal class TableTest() {
    var conn: Connection = DriverManager.getConnection("jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1", "sa", "")

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

        //println(conn.createStatement().executeQuery("SELECT * from test_schema.test_table")
        //    .parseResultSet())
    }

    @Test
    fun 指定した条件で取得したレコードから任意のカラムに入っている文字列を取得する() {
        TODO()
    }

    private fun ResultSet.parseResultSet(): List<List<Any>> {
        val rows = mutableListOf<List<Any>>()
        while (this.next()) {
            (1..this.metaData.columnCount).map {
                when (this.metaData.getColumnType(it)) {
                    Types.VARCHAR -> this.getString(it)
                    Types.TIMESTAMP -> this.getTimestamp(it).let { OffsetDateTime.ofInstant(it.toInstant(), ZoneId.of("UTC"))}
                    else -> this.getObject(it)
                }
            }.toList().run { rows.add(this) }
        }
        return rows
    }
}