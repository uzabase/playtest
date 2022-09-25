package com.uzabase.playtest.gauge.db

import org.assertj.db.type.Changes
import org.assertj.db.type.Source

data class DatabaseChangesKeys(private val dbName: String) {
    val changesKey = object : StoreKey<Changes> {
        override val key = dbName to "change"
    }
    val isStartKey = object : StoreKey<Boolean> {
        override val key = dbName to "start"
    }
    val isEndKey = object : StoreKey<Boolean> {
        override val key = dbName to "end"
    }
}

data class DatabaseChanges(private val dbName: String) {
    private val keys = DatabaseChangesKeys(dbName)

    fun setup() {
        DataStore.storeToScenario(keys.isStartKey, false)
        DataStore.storeToScenario(keys.isEndKey, false)
    }

    fun startRecordOnce() {
        if (isStarted()) return
        val changes = initialChanges()
        changes.setStartPointNow()
        DataStore.storeToScenario(keys.changesKey, changes)
        DataStore.storeToScenario(keys.isStartKey, true)
    }

    fun endRecordOnce() {
        if (isEnded()) return
        DataStore.loadFromScenario(keys.changesKey).setEndPointNow()
        DataStore.storeToScenario(keys.isEndKey, true)
    }

    fun initialChanges(): Changes {
        return GaugeDbConfig.get(dbName)
            .let { Source(it.url, it.user, it.password) }
            .let { Changes(it) }
    }

    fun get(): Changes = DataStore.loadFromScenario(keys.changesKey)

    fun isStarted() = DataStore.loadFromScenario(keys.isStartKey)

    fun isEnded() = DataStore.loadFromScenario(keys.isEndKey)
}
