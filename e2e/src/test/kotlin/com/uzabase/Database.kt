package com.uzabase

import com.thoughtworks.gauge.Step
import org.amshove.kluent.shouldBeEqualTo

class Database {
    @Step("<schemaName>スキーマの<tableName>テーブルの、<columnName>を<whereString>で取得した一意の結果が文字列の<vale>である")
            fun getStringParameterFromDatabase(schemaName: String, tableName: String, columnName: String, whereString: String, value: String) {
                val query = """
                    select $columnName from $schemaName.$tableName where $whereString;
                """.trimIndent()
               val result = TODO() //いい感じにSQLを実行する
               value shouldBeEqualTo result
            }

    @Step("<schemaName>スキーマの<tableName>テーブルの、<columnName>を<whereString>で取得した一意の結果が整数の<vale>である")
    fun getIntParameterFromDatabase(schemaName: String, tableName: String, columnName: String, whereString: String, value: Int) {
        val query = """
                    select $columnName from $schemaName.$tableName where $whereString;
                """.trimIndent()
        val result = TODO() //いい感じにSQLを実行する
        value shouldBeEqualTo result
    }
}