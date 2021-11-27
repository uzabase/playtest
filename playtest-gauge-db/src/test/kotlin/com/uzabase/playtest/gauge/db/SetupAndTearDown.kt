package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.BeforeSuite
import com.uzabase.playtest.db.Database
import javassist.NotFoundException
import java.io.File

class SetupAndTearDown {
//    fun setup() {
//        val db = GaugeDbConfig.get("test_db")
//        val database = Database(db.driverClass, db.url, db.user, db.password, db.schema)
//        val data = javaClass.getResource("/test-db")?.toURI()?.let { File(it) } ?: throw NotFoundException("/test-db not found")
//        database.cleanInsert(data)
//    }
}