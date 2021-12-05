package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.db.Database
import com.uzabase.playtest.db.Entity
import javassist.NotFoundException
import java.io.File
import java.sql.Date
import java.util.*

data class Todo(
    val id: UUID,
    val memo: String,
    val priority: Int,
    val progress_rate: Double,
    val registered_date: Date
) : Entity("todos")

class DatabaseSetup {
    private val db = GaugeDbConfig.get("test_db")
    private val database = Database(db.driverClass, db.url, db.user, db.password, db.schema)

    @Step("test_dbにテストデータをセットアップする")
    fun setupDatabase() {
        val data = javaClass.getResource("/test-db")?.toURI()?.let { File(it) }
            ?: throw NotFoundException("/test-db not found")
        database.insert(data)
    }

    @Step("test_dbのテーブルをtruncateする")
    fun truncate() {
        database.truncate("todos")
    }

    @Step("test_dbにレコードを挿入する")
    fun insert() {
        val entity = Todo(
            UUID.fromString("bf486f2f-99f8-4f73-a1af-f7ca4506ea53"),
            "test memo",
            10,
            0.5,
            Date.valueOf("2020-01-01")
        )
        database.insert(entity)
    }

    @Step("test_dbからレコードを削除する")
    fun delete() {
        val entity = Todo(
            UUID.fromString("bf486f2f-99f8-4f73-a1af-f7ca4506ea53"),
            "test memo",
            10,
            0.5,
            Date.valueOf("2020-01-01")
        )
        database.delete(entity)
    }

    @Step("test_dbの1レコードをアップデートする")
    fun update() {
        database.connection {
            it.connection.createStatement()
                .execute("update test.todos set memo = 'update memo' where id = '404e05a3-a34f-47d0-8997-968d90ba64ca';")
        }
    }
}
