package com.uzabase.playtest.gauge.rest.json

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore
import com.uzabase.playtest.json.JsonNode
import org.junit.jupiter.api.Assertions.*

class JsonArrayStep {
    @Step("レスポンスのJSONの<jsonPath>の配列の長さが<length>である")
    fun assertJsonLengthEquals(jsonPath: String, length: Int) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).getArrayLength(jsonPath)
        assertEquals(length, actual)
    }

    @Step("レスポンスのJSONの<jsonPath>の配列に、Key<key>の値が<value>である要素が存在する")
    fun assertContainsElementInJson(jsonPath: String, key: String, value: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).getFilteredList(jsonPath, key, value)?.size
        assertNotNull(actual, "Array $jsonPath not found. JSON: $json")
        assertTrue(0 < actual!!)
    }
}
