package com.uzabase

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.db.Table
import org.amshove.kluent.shouldBeEqualTo
import java.sql.Connection
import java.sql.DriverManager

class Database(
    private val url: String = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
    private val username: String = "sa",
    private val password: String = "",
) {
    @Step("<schemaName>スキーマの<tableName>テーブルの、<columnName>を<whereString>で取得した一意の<valueColumn>が文字列の<vale>である")
    fun getStringParameterFromDatabase(
        schemaName: String,
        tableName: String,
        columnName: String,
        whereString: String,
        valueColumn: String,
        value: String
    ) {
        val query = """
                    select $columnName from $schemaName.$tableName where $whereString;
                """.trimIndent()
        val result =
            Table(url, username, password, schemaName, tableName).where(
                columnName,
                whereString
            ).first().column(valueColumn).value
        value shouldBeEqualTo result
    }

    @Step("<schemaName>スキーマの<tableName>テーブルの、<columnName>を<whereString>で取得した一意の<valueColumn>が整数の<vale>である")
    fun getIntParameterFromDatabase(
        schemaName: String,
        tableName: String,
        columnName: String,
        whereString: String,
        valueColumn: String,
        value: Int
    ) {
        val query = """
                    select $columnName from $schemaName.$tableName where $whereString;
                """.trimIndent()
        val result =
            Table(url, username, password, schemaName, tableName).where(
                columnName,
                whereString
            ).first().column(valueColumn).value
        value shouldBeEqualTo result
    }

    @Step("テスト用のスキーマ、テーブルを用意する")
    fun setupTable() {
        val createAndInsertQuery = this.javaClass
            .classLoader
            .getResourceAsStream("setup.sql")
            ?.bufferedReader()
            ?.use { it.readText() }

        val conn: Connection =
            DriverManager.getConnection(url, username, password)

        conn.createStatement().execute(createAndInsertQuery)
    }

    @Step("テスト用のスキーマ、テーブルを削除する")
    fun truncateTable() {
        val truncateQuery = this.javaClass
            .classLoader
            .getResourceAsStream("truncate.sql")
            ?.bufferedReader()
            ?.use { it.readText() }

        val conn: Connection =
            DriverManager.getConnection(url, username, password)

        conn.createStatement().execute(truncateQuery)
    }
}