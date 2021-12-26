package com.uzabase.playtest.gauge.rest.json

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore
import com.uzabase.playtest.gauge.rest.JsonList
import com.uzabase.playtest.json.JsonNode
import org.junit.jupiter.api.Assertions

class JsonArrayOrderStep {
    @Step("レスポンスのJSONの配列<arrayJsonPath>が、文字列<orderKey>の昇順に並んでいる")
    fun assertOrderedByStringAsc(arrayJsonPath: String, sortKey: String) = assertOrderedBy(arrayJsonPath) {
        it.sortByStringAsc(sortKey)
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、文字列<orderKey>の降順に並んでいる")
    fun assertOrderedByStringDesc(arrayJsonPath: String, sortKey: String) = assertOrderedBy(arrayJsonPath) {
        it.sortByStringDesc(sortKey)
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、数値<orderKey>の昇順に並んでいる")
    fun assertOrderedByNumberAsc(arrayJsonPath: String, sortKey: String) = assertOrderedBy(arrayJsonPath) {
        it.sortByNumberAsc(sortKey)
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、数値<orderKey>の降順に並んでいる")
    fun assertOrderedByNumberDesc(arrayJsonPath: String, sortKey: String) = assertOrderedBy(arrayJsonPath) {
        it.sortByNumberDesc(sortKey)
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、タイムゾーン付きの日付/時間<orderKey>の昇順に並んでいる")
    fun assertOrderedByZonedDateTimeAsc(arrayJsonPath: String, sortKey: String) = assertOrderedBy(arrayJsonPath) {
        it.sortByZonedDateTimeAsc(sortKey)
    }

    @Step("レスポンスのJSONの配列<arrayJsonPath>が、タイムゾーン付きの日付/時間<orderKey>の降順に並んでいる")
    fun assertOrderedByZonedDateTimeDesc(arrayJsonPath: String, sortKey: String) = assertOrderedBy(arrayJsonPath) {
        it.sortByZonedDateTimeDesc(sortKey)
    }
}

internal fun assertOrderedBy(arrayJsonPath: String, f: (JsonList) -> List<Map<String, Any>>) {
    val json = DataStore.loadResponseBodyFromScenario().string
    val response = JsonNode.of(json).get<List<Map<String, Any>>>(arrayJsonPath)!!
    val sorted = JsonList(response).let(f)
    Assertions.assertEquals(sorted, response)
}
