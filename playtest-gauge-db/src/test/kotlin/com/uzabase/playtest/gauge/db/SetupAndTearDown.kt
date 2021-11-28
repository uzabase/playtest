package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.BeforeScenario
import com.uzabase.playtest.db.Database

class SetupAndTearDown {
    private val db = GaugeDbConfig.get("test_db")
    private val database = Database(db.driverClass, db.url, db.user, db.password, db.schema)

    @BeforeScenario(tags = ["truncate"])
    fun clean() {
        database.truncate("todos")
    }
}