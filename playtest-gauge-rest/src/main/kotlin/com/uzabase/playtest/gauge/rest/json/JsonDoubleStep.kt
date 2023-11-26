package com.uzabase.playtest.gauge.rest.json

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore
import com.uzabase.playtest.json.JsonNode
import org.junit.jupiter.api.Assertions.*

class JsonDoubleStep {
    @Step("レスポンスのJSONの<jsonPath>が小数の<expected>である")
    fun assertEquals(jsonPath: String, expected: Double) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).get<Double>(jsonPath)
        assertEquals(expected, actual)
    }

    @Step("レスポンスのJSONの<jsonPath>が小数の<expected>でない")
    fun assertNotEquals(jsonPath: String, expected: Double) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).get<Double>(jsonPath)
        assertNotEquals(expected, actual)
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の、UniqueKey<uniqueKey>の値が<filterValue>である要素の<key>が、小数値の<expected>である")
    fun assertEqualsInUniqueObject(jsonPath: String, uniqueKey: String, filterValue: String, key: String, expected: Double) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).getUniqElementInArray(jsonPath, uniqueKey, filterValue)
        assertEquals(expected, actual[key])
    }

    @Step("レスポンスのJSONの配列<jsonPath>に、小数値<value>が存在する")
    fun assertContains(jsonPath: String, value: Double) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val list = JsonNode.of(json).get<List<Double>>(jsonPath)!!
        assertTrue(list.contains(value))
    }

    @Step("レスポンスのJSONの配列<jsonPath>に、小数値<value>が存在しない")
    fun assertNotContains(jsonPath: String, value: Double) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val list = JsonNode.of(json).get<List<Double>>(jsonPath)!!
        assertFalse(list.contains(value))
    }
}
