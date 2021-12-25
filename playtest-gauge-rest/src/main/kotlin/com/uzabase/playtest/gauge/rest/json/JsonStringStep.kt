package com.uzabase.playtest.gauge.rest.json

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore
import com.uzabase.playtest.json.JsonNode
import org.junit.jupiter.api.Assertions.*

class JsonStringStep {
    @Step("レスポンスのJSONの<jsonPath>が文字列の<expected>である")
    fun assertEquals(jsonPath: String, expected: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).get<String>(jsonPath)
        assertEquals(expected, actual)
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の、UniqueKey<uniqueKey>の値が<filterValue>である要素の<key>が、文字列の<expected>である")
    fun assertEqualsInUniqueObject(jsonPath: String, uniqueKey: String, filterValue: String, key: String, expected: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).getUniqElementInArray(jsonPath, uniqueKey, filterValue)
        assertEquals(expected, actual[key])
    }

    @Step("レスポンスのJSONの配列<jsonPath>に、文字列<value>が存在する")
    fun assertContains(jsonPath: String, value: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val list = JsonNode.of(json).get<List<String>>(jsonPath)!!
        assertTrue(list.contains(value))
    }

    @Step("レスポンスのJSONの配列<jsonPath>に、文字列<value>が存在しない")
    fun assertNotContains(jsonPath: String, value: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val list = JsonNode.of(json).get<List<String>>(jsonPath)!!
        assertFalse(list.contains(value))
    }
}
