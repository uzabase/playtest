package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.datastore.ScenarioDataStore



interface StoreKey<T> {
    val key: String
}

object DataStore {
    fun <T> storeToScenario(key: StoreKey<T>, data: T) {
        ScenarioDataStore.put(key.key, data)
    }

    inline fun <reified T> loadFromScenario(key: StoreKey<T>): T {
        return when (val value = ScenarioDataStore.get(key.key)) {
            is T -> value
            else -> throw Exception()
        }
    }
}