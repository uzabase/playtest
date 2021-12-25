package com.uzabase.playtest.gauge.rest.json

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore
import com.uzabase.playtest.gauge.rest.JsonList
import com.uzabase.playtest.gauge.rest.Order
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

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、数値<orderKey>の昇順に並んでいる")
    fun assertOrderedByNumberAsc(arrayJsonPath: String, sortKey: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val response = JsonNode.of(json).get<List<Map<String, Any>>>(arrayJsonPath)!!
        val sorted = JsonList(response).sortByNumber(sortKey, Order.Asc)
        assertEquals(sorted, response)
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、数値<orderKey>の降順に並んでいる")
    fun assertOrderedByNumberDesc(arrayJsonPath: String, sortKey: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val response = JsonNode.of(json).get<List<Map<String, Any>>>(arrayJsonPath)!!
        val sorted = JsonList(response).sortByNumber(sortKey, Order.Desc)
        assertEquals(sorted, response)
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、タイムゾーン付きの日付/時間<orderKey>の昇順に並んでいる")
    fun assertOrderedByZonedDateTimeAsc(arrayJsonPath: String, sortKey: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val response = JsonNode.of(json).get<List<Map<String, Any>>>(arrayJsonPath)!!
        val sorted = JsonList(response).sortByZonedDateTime(sortKey, Order.Asc)
        assertEquals(sorted, response)
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、タイムゾーン付きの日付/時間<orderKey>の降順に並んでいる")
    fun assertOrderedByZonedDateTimeDesc(arrayJsonPath: String, sortKey: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val response = JsonNode.of(json).get<List<Map<String, Any>>>(arrayJsonPath)!!
        val sorted = JsonList(response).sortByZonedDateTime(sortKey, Order.Desc)
        assertEquals(sorted, response)
    }
}
