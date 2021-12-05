package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.*
import com.uzabase.playtest.db.Database
import javassist.NotFoundException
import java.io.File


class SetupAndTearDown {
    private val db = GaugeDbConfig.get("test_db")
    private val database = Database(db.driverClass, db.url, db.user, db.password, db.schema)
    private val testDbChanges = DatabaseChanges("test_db")


    @BeforeScenario(tags = ["setup"])
    fun setupDb() {
        val data = javaClass.getResource("/test-db")?.toURI()?.let { File(it) }
            ?: throw NotFoundException("/test-db not found")
        database.cleanInsert(data)
    }

    @AfterScenario(tags = ["truncate"])
    fun clean() {
        database.truncate("todos")
    }

    @BeforeScenario()
    fun setup() {
        testDbChanges.setup()
    }

    @BeforeStep(tags = ["record-changes"])
    fun startRecord() {
        testDbChanges.startRecordIfNot()
    }

    @AfterStep(tags = ["record-changes"])
    fun endRecord() {
        testDbChanges.endRecordIfNot()
    }
}