package com.uzabase.playtest.db

import org.dbunit.dataset.DefaultDataSet
import org.dbunit.dataset.DefaultTable
import org.dbunit.dataset.datatype.DataType
import kotlin.reflect.full.memberProperties

open class Entity(val tableName: String) {
    fun toDataSet(): DefaultDataSet {
        val columnProperties = this::class.memberProperties.filter { it.name != "tableName" }
        val columns = columnProperties.map { org.dbunit.dataset.Column(it.name, DataType.UNKNOWN) }.toTypedArray()
        val table = DefaultTable(this.tableName, columns)
        val values = columnProperties.map { it.getter.call(this) }.toTypedArray()
        table.addRow(values)
        return DefaultDataSet(table)
    }
}
