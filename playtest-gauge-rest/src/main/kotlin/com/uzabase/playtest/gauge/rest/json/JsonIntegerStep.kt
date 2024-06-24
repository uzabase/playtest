package com.uzabase.playtest.gauge.rest.json

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore
import com.uzabase.playtest.json.JsonNode
import org.junit.jupiter.api.Assertions.*

class JsonIntegerStep {
    @Step("レスポンスのJSONの<jsonPath>が整数の<expected>である")
    fun assertEquals(jsonPath: String, expected: Int) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).get<Int>(jsonPath)
        assertEquals(expected, actual)
    }

    @Step("レスポンスのJSONの<jsonPath>が整数の<expected>でない")
    fun assertNotEquals(jsonPath: String, expected: Int) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).get<Int>(jsonPath)
        assertNotEquals(expected, actual)
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の、UniqueKey<uniqueKey>の値が<filterValue>である要素の<key>が、整数値の<expected>である")
    fun assertEqualsInUniqueObject(jsonPath: String, uniqueKey: String, filterValue: String, key: String, expected: Int) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val actual = JsonNode.of(json).getUniqElementInArray(jsonPath, uniqueKey, filterValue)
        assertEquals(expected, actual[key])
    }

    @Step("レスポンスのJSONの配列<jsonPath>に、整数値<value>が存在する")
    fun assertContains(jsonPath: String, value: Int) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val list = JsonNode.of(json).get<List<Int>>(jsonPath)!!
        assertTrue(list.contains(value))
    }

    @Step("レスポンスのJSONの配列<jsonPath>に、整数値<value>が存在しない")
    fun assertNotContains(jsonPath: String, value: Int) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val list = JsonNode.of(json).get<List<Int>>(jsonPath)!!
        assertFalse(list.contains(value))
    }
}
