package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.Step
import javassist.NotFoundException
import org.assertj.db.api.Assertions.assertThat
import org.assertj.db.type.*

class DatabaseStep {

    @Step(
        "DB<dbName>の<schemaName>スキーマの<tableName>テーブルの、<whereColumn>を<whereValue>で取得した一意の<valueColumn>が整数の<value>である",
        "DB<dbName>の<schemaName>スキーマの<tableName>テーブルの、<whereColumn>を<whereValue>で取得した一意の<valueColumn>が小数の<value>である",
        "DB<dbName>の<schemaName>スキーマの<tableName>テーブルの、<whereColumn>を<whereValue>で取得した一意の<valueColumn>が文字列の<value>である"
    )
    fun assertUniqueRecordValue(
        dbName: String,
        schemaName: String,
        tableName: String,
        whereColumn: String,
        whereValue: String,
        valueColumn: String,
        value: String
    ) {
        val config = GaugeDbConfig.get(dbName)
        val source = Source(config.url, config.user, config.password)

        val request = Request(source, "select * from $schemaName.$tableName where $whereColumn = $whereValue")

        assertThat(request).row().value(valueColumn).isEqualTo(value)
    }

    @Step("DB<dbName>の<schemaName>スキーマの<tableName>テーブルの、<whereColumn>を<whereValue>で取得した一意の<valueColumn>が日時の<value>である")
    fun assertUniqueRecordDateValue(
        dbName: String,
        schemaName: String,
        tableName: String,
        whereColumn: String,
        whereValue: String,
        valueColumn: String,
        value: String
    ) {
        val config = GaugeDbConfig.get(dbName)
        val source = Source(config.url, config.user, config.password)

        val request = Request(source, "select * from $schemaName.$tableName where $whereColumn = $whereValue")

        assertThat(request).row().value(valueColumn).isEqualTo(DateTimeValue.parse(value))
    }

    @Step(
        "DB<dbName>の<schemaName>スキーマの<tableName>テーブルの、条件<where>で取得した一意の<valueColumn>が整数の<value>である",
        "DB<dbName>の<schemaName>スキーマの<tableName>テーブルの、条件<where>で取得した一意の<valueColumn>が小数の<value>である",
        "DB<dbName>の<schemaName>スキーマの<tableName>テーブルの、条件<where>で取得した一意の<valueColumn>が文字列の<value>である"
    )
    fun assertUniqueRecordValue(
        dbName: String,
        schemaName: String,
        tableName: String,
        where: String,
        valueColumn: String,
        value: String
    ) {
        val config = GaugeDbConfig.get(dbName)
        val source = Source(config.url, config.user, config.password)

        val request = Request(source, "select * from $schemaName.$tableName where $where")

        assertThat(request).row().value(valueColumn).isEqualTo(value)
    }

    @Step("DB<dbName>の<schemaName>スキーマの<tableName>テーブルの、条件<where>で取得した一意の<valueColumn>が日時の<value>である")
    fun assertUniqueRecordDateTimeValue(
        dbName: String,
        schemaName: String,
        tableName: String,
        where: String,
        valueColumn: String,
        value: String
    ) {
        val config = GaugeDbConfig.get(dbName)
        val source = Source(config.url, config.user, config.password)

        val request = Request(source, "select * from $schemaName.$tableName where $where")

        assertThat(request).row().value(valueColumn).isEqualTo(DateTimeValue.parse(value))
    }

    @Step("DB<dbName>の<schemaName>スキーマの<tableName>テーブルの、<whereColumn>を<whereValue>で取得した一意の<valueColumn>が<year>年<month>月<day>日である")
    fun assertUniqueRecordDateValue(
        dbName: String,
        schemaName: String,
        tableName: String,
        whereColumn: String,
        whereValue: String,
        valueColumn: String,
        year: Int,
        month: Int,
        day: Int
    ) {
        val config = GaugeDbConfig.get(dbName)
        val source = Source(config.url, config.user, config.password)

        val request = Request(source, "select * from $schemaName.$tableName where $whereColumn = $whereValue")

        assertThat(request).row().value(valueColumn).isEqualTo(DateValue.of(year, month, day))
    }

    @Step("DB<dbName>の<schemaName>スキーマの<tableName>テーブルの、条件<where>で取得した一意の<valueColumn>が<year>年<month>月<day>日である")
    fun assertUniqueRecordDateValue(
        dbName: String,
        schemaName: String,
        tableName: String,
        where: String,
        valueColumn: String,
        year: Int,
        month: Int,
        day: Int
    ) {
        val config = GaugeDbConfig.get(dbName)
        val source = Source(config.url, config.user, config.password)

        val request = Request(source, "select * from $schemaName.$tableName where $where")

        assertThat(request).row().value(valueColumn).isEqualTo(DateValue.of(year, month, day))
    }

    @Step("DB<dbName>の<schemaName>スキーマの<tableName>テーブルのレコード数が<count>である")
    fun assertRecordCount(
        dbName: String,
        schemaName: String,
        tableName: String,
        count: Int
    ) {
        val config = GaugeDbConfig.get(dbName)
        val source = Source(config.url, config.user, config.password)
        val table = Table(source, "$schemaName.$tableName")
        assertThat(table).hasNumberOfRows(count)
    }

    @Step("DB<dbName>の<schemaName>スキーマの<tableName>テーブルで変更されたレコード数が<count>である")
    fun isModified(
        dbName: String,
        schemaName: String,
        tableName: String,
        count: Int
    ) {
        stopRecords()
        assertThat(getChange(dbName)).onTable("$schemaName.$tableName").ofModification().hasNumberOfChanges(count)
    }


    @Step("DB<dbName>の<schemaName>スキーマの<tableName>テーブルで削除されたレコード数が<count>である")
    fun isDeleted(
        dbName: String,
        schemaName: String,
        tableName: String,
        count: Int
    ) {
        stopRecords()
        assertThat(getChange(dbName)).onTable("$schemaName.$tableName").ofDeletion().hasNumberOfChanges(count)
    }

    @Step("DB<dbName>の<schemaName>スキーマの<tableName>テーブルで作成されたレコード数が<count>である")
    fun isCreated(
        dbName: String,
        schemaName: String,
        tableName: String,
        count: Int
    ) {
        stopRecords()
        assertThat(getChange(dbName)).onTable("$schemaName.$tableName").ofCreation().hasNumberOfChanges(count)
    }

    private fun stopRecords() {
        dbChangesMap.forEach {
            it.value.endRecordOnce()
        }
    }

    private fun getChange(dbName: String): Changes {
        return dbChangesMap[dbName]?.get() ?: throw NotFoundException("$dbName changes not found")
    }
}