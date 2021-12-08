package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.BeforeScenario
import com.thoughtworks.gauge.BeforeSuite
import com.uzabase.playtest.db.Database
import javassist.NotFoundException
import java.io.File


class SetupAndTearDown {
    private val db = GaugeDbConfig.get("test_db")
    private val database = Database(db.driverClass, db.url, db.user, db.password, db.schema)
    private val testDbChanges = DatabaseChanges("test_db")

    @BeforeSuite()
    fun truncate() {
        database.truncate("todos")
    }

    @BeforeScenario(tags = ["_setup"])
    fun setupDb() {
        val data = javaClass.getResource("/test-db")?.toURI()?.let { File(it) }
            ?: throw NotFoundException("/test-db not found")
        database.cleanInsert(data)
    }

    @BeforeScenario(tags = ["_truncate"])
    fun setup() {
        database.truncate("todos")
    }
}