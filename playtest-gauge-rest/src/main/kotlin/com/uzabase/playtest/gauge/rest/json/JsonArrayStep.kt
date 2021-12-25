package com.uzabase.playtest.gauge.rest.json

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore
import com.uzabase.playtest.gauge.rest.JsonList
import com.uzabase.playtest.gauge.rest.Order
import com.uzabase.playtest.json.JsonNode
import org.junit.jupiter.api.Assertions.*

class JsonArrayStep {
    @Step("レスポンスのJSONの<jsonPath>の配列の長さが<length>である")
    fun assertJsonLength(jsonPath: String, length: Int) {
        val actual = JsonNode.of(DataStore.loadResponseBodyFromScenario().string).getArrayLength(jsonPath)
        assertEquals(length, actual)
    }

    @Step("レスポンスのJSONの<jsonPath>の配列に、Key<key>の値が<value>である要素が存在する")
    fun assertJsonExistElement(jsonPath: String, key: String, value: String) {
        val actual = JsonNode.of(DataStore.loadResponseBodyFromScenario().string)
            .getFilteredList(jsonPath, key, value)?.size
        assertNotNull(actual, "Array $jsonPath not found.")
        assertTrue(0 < actual!!)
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、数値<orderKey>の昇順に並んでいる")
    fun assertOrderByNumberAsc(arrayJsonPath: String, sortKey: String) {
        val json = DataStore.loadResponseBodyFromScenario().string
        val response = JsonNode.of(json).get<List<Map<String, Any>>>(arrayJsonPath)!!
        val sorted = JsonList(response).sortByNumber(sortKey, Order.Asc)
        assertEquals(sorted, response)
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、数値<orderKey>の降順に並んでいる")
    fun assertOrderByNumberDesc(arrayJsonPath: String, sortKey: String) {
        val response =
            JsonNode.of(DataStore.loadResponseBodyFromScenario().string).get<List<Map<String, Any>>>(arrayJsonPath)!!
        val sorted = JsonList(response).sortByNumber(sortKey, Order.Desc)
        assertEquals(sorted, response)
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、タイムゾーン付きの日付/時間<orderKey>の昇順に並んでいる")
    fun assertOrderByZonedDateTimeAsc(arrayJsonPath: String, sortKey: String) {
        val response =
            JsonNode.of(DataStore.loadResponseBodyFromScenario().string).get<List<Map<String, Any>>>(arrayJsonPath)!!
        val sorted = JsonList(response).sortByZonedDateTime(sortKey, Order.Asc)
        assertEquals(sorted, response)
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、タイムゾーン付きの日付/時間<orderKey>の降順に並んでいる")
    fun assertOrderByZonedDateTimeDesc(arrayJsonPath: String, sortKey: String) {
        val response =
            JsonNode.of(DataStore.loadResponseBodyFromScenario().string).get<List<Map<String, Any>>>(arrayJsonPath)!!
        val sorted = JsonList(response).sortByZonedDateTime(sortKey, Order.Desc)
        assertEquals(sorted, response)
    }
}