package com.uzabase.playtest.db

import org.assertj.db.type.Request
import org.assertj.db.type.Source

class Table(
    private val url: String,
    private val username: String,
    private val password: String,
    private val schemaName: String,
    private val tableName: String
) {
    private val source = Source(url, username, password)

    fun where(column: String, matchValue: String): List<Row> {
        val result = Request(source, "select * from $schemaName.$tableName where $column = '$matchValue'")

        var rows = mutableListOf<Row>()
        for (r in result.rowsList) {
            var cols = mutableListOf<Column>()
            for (c in r.valuesList) {
                cols.add(Column(c.columnName, c.value))
            }
            rows.add(Row(cols))
        }
        return rows
    }
}

data class Row(val columns: List<Column>) {
    fun column(name: String) = columns.first { c -> c.name.equals(name , ignoreCase = true) }
}

data class Column(val name: String, val value: Any?) {
}
