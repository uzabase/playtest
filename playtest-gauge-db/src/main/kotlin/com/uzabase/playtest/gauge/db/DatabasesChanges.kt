package com.uzabase.playtest.gauge.db

import javassist.NotFoundException
import org.assertj.db.type.Changes

object DatabasesChanges {
    private val map = GaugeDbConfig.getRecords().associateWith { DatabaseChanges(it) }

    fun startRecord() {
        map.forEach {
            it.value.setup()
        }
        map.forEach {
            it.value.startRecordOnce()
        }
    }

    fun stopRecords() {
        map.forEach {
            it.value.endRecordOnce()
        }
    }

    fun getChange(dbName: String): Changes {
        return map[dbName]?.get() ?: throw NotFoundException("$dbName changes not found")
    }
}