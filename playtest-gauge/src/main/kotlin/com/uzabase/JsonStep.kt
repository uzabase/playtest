package com.uzabase

import com.thoughtworks.gauge.Step
import com.uzabase.DataStore.loadJsonFromScenario
import org.amshove.kluent.shouldBeEqualTo

class JsonStep {

    @Step("レスポンスのJSONの<jsonPath>が文字列の<expected>である")
    fun assertJson(jsonPath: String, expected: String) {
        JsonNode.of(loadJsonFromScenario()).get<String>(jsonPath) shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>が整数の<expected>である")
    fun assertJson(jsonPath: String, expected: Int) {
        JsonNode.of(loadJsonFromScenario()).get<Int>(jsonPath) shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>が小数の<expected>である")
    fun assertJson(jsonPath: String, expected: Double) {
        JsonNode.of(loadJsonFromScenario()).get<Double>(jsonPath) shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>が真偽値の<expected>である")
    fun assertJson(jsonPath: String, expected: Boolean) {
        JsonNode.of(loadJsonFromScenario()).get<Boolean>(jsonPath) shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の<filterKey>が<filterValue>である一意な要素の<key>が文字列の<expected>である")
    fun assertJson(jsonPath: String, filterKey: String, filterValue: String, key: String, expected: String) {
        val element = JsonNode.of(loadJsonFromScenario()).getUniqElementInArray(jsonPath, filterKey, filterValue)
        element[key] shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の<filterKey>が<filterValue>である一意な要素の<key>が整数値の<expected>である")
    fun assertJson(jsonPath: String, filterKey: String, filterValue: String, key: String, expected: Int) {
        val element = JsonNode.of(loadJsonFromScenario()).getUniqElementInArray(jsonPath, filterKey, filterValue)
        element[key] shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の<filterKey>が<filterValue>である一意な要素の<key>が小数値の<expected>である")
    fun assertJson(jsonPath: String, filterKey: String, filterValue: String, key: String, expected: Double) {
        val element = JsonNode.of(loadJsonFromScenario()).getUniqElementInArray(jsonPath, filterKey, filterValue)
        element[key] shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の<filterKey>が<filterValue>である一意な要素の<key>が真偽値の<expected>である")
    fun assertJson(jsonPath: String, filterKey: String, filterValue: String, key: String, expected: Boolean) {
        val element = JsonNode.of(loadJsonFromScenario()).getUniqElementInArray(jsonPath, filterKey, filterValue)
        element[key] shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>の配列の長さが<length>である")
    fun assertJsonLength(jsonPath: String, length: Int) {
        JsonNode.of(loadJsonFromScenario()).getArrayLength(jsonPath) shouldBeEqualTo length
    }

    @Step("レスポンスのJSONの<jsonPath>が存在しない")
    fun nonExist(jsonPath: String) {
        JsonNode.of(loadJsonFromScenario()).get<Any>(jsonPath) shouldBeEqualTo null
    }

    private fun JsonNode.getUniqElementInArray(
        arrayJsonPath: String,
        filterKey: String,
        filterValue: String
    ) = this.getFilteredList(arrayJsonPath, filterKey, filterValue)
        .takeIf { it.size == 1 }
        ?.first()
        ?: throw IllegalArgumentException("filter: $filterKey == $filterValue can not specify element in $arrayJsonPath")
}