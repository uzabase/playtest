package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.db.Table
import org.amshove.kluent.shouldBeEqualTo

class Sample {
    @Step("DB<db>のTable<tableName>の値をアサートできる")
    fun test(db: String, tableName: String) {
        val databaseInfo = GaugeDbConfig.get(db)
        val table = Table(
            tableName = tableName,
            schemaName = databaseInfo.schema,
            username = databaseInfo.user,
            password = databaseInfo.password,
            url = databaseInfo.url
        )
        table.where("priority", 1).first().column("memo").value shouldBeEqualTo "this is test"
    }
}
