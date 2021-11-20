package com.uzabase.playtest.gauge.rest

import com.thoughtworks.gauge.datastore.ScenarioDataStore
import com.uzabase.playtest.gauge.rest.DataStore.loadFromScenario
import com.uzabase.playtest.gauge.rest.DataStore.storeToScenario
import io.mockk.*
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

internal class DataStoreKtTest {
    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun シナリオデータストアに値を保存する() {
        val key = "test"
        val value = "value"
        mockkStatic(ScenarioDataStore::class)
        every { ScenarioDataStore.put(any(), any()) } just runs
        storeToScenario(key, "value")
        verify { ScenarioDataStore.put(key, value) }
    }

    @Test
    fun シナリオデータストアから値を取得する() {
        val expected = "value"
        val key = "test"
        storeToScenario(key, expected)
        loadFromScenario<String>(key) `should be equal to` ScenarioDataStore.get(key)
    }
}
