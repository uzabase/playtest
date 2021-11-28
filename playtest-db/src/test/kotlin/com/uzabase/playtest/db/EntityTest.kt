package com.uzabase.playtest.db

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

data class TestEntity(val value: String) : Entity("test")

internal class EntityTest {
    @Test
    fun EntityをDataSetに変換する() {
        val entity = TestEntity("test test")
        val table = entity.toDataSet().tables[0]
        table.tableMetaData.tableName shouldBeEqualTo "test"
        table.tableMetaData.columns.map { it.columnName } shouldBeEqualTo listOf("value")
        table.getValue(0, "value") shouldBeEqualTo "test test"
    }
}