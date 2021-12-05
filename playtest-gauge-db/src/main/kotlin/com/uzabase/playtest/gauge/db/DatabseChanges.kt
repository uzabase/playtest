package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.datastore.ScenarioDataStore
import org.assertj.db.type.Changes
import org.assertj.db.type.Source

data class DatabaseChanges(private val dbName: String) {
    private val changesKey = "$dbName changes key"
    private val isStartKey = "start $dbName record key"
    private val isEndKey = "end $dbName record key"

    fun setup() {
        ScenarioDataStore.put(isStartKey, false)
        ScenarioDataStore.put(isEndKey, false)
    }

    fun startRecordIfNot() {
        if (!(ScenarioDataStore.get(isStartKey) as Boolean)) {
            val config = GaugeDbConfig.get(dbName)
            val source = Source(config.url, config.user, config.password)
            val changes = Changes(source)
            changes.setStartPointNow()
            ScenarioDataStore.put(changesKey, changes)
            ScenarioDataStore.put(isStartKey, true)
        }
    }

    fun endRecordIfNot() {
        if (!(ScenarioDataStore.get(isEndKey) as Boolean)) {
            val changes = ScenarioDataStore.get(changesKey) as Changes
            changes.setEndPointNow()
            ScenarioDataStore.put(isEndKey, true)
        }
    }

    fun get(): Changes {
        return ScenarioDataStore.get(changesKey) as Changes
    }
}