package com.uzabase

import com.thoughtworks.gauge.Step
import org.amshove.kluent.shouldBeEqualTo

class JsonPath {
    @Step("<jsonPath>の値を取得した結果が文字列の<value>である")
    fun getJsonPathAsString(jsonPath: String, value: String) {
        val jsonString = """
                {"key1": {"key2": "${value}"}}
            """.trimIndent()
        val jsonNode = JsonNode.of(jsonString)
        value shouldBeEqualTo jsonNode.get<String>(jsonPath)
    }

    @Step("<jsonPath>の値を取得した結果が真偽値の<value>である")
    fun getJsonPathAsBoolean(jsonPath: String, value: String) {
        val jsonString = """
            {"key1": {"key2": "${value}"}}
        """.trimIndent()
        val jsonNode = JsonNode.of(jsonString)
        value.toBoolean() shouldBeEqualTo jsonNode.get<Boolean>(jsonPath)
    }

    @Step("<jsonPath>の値を取得した結果が整数の<value>である")
    fun getJsonPathAsInteger(jsonPath: String, value: String) {
        val jsonString = """
            {"key1": {"key2": "${value}"}}
        """.trimIndent()
        val jsonNode = JsonNode.of(jsonString)
        value.toInt() shouldBeEqualTo jsonNode.get<Int>(jsonPath)

    }

    @Step("<jsonPath>の値を取得した結果が小数の<value>である")
    fun getJsonPathAsFloat(jsonPath: String, value: String) {
        val jsonString = """
            {"key1": {"key2": "${value}"}}
        """.trimIndent()
        val jsonNode = JsonNode.of(jsonString)
        value.toDouble() shouldBeEqualTo jsonNode.get<Double>(jsonPath)
    }

    @Step("<jsonPath>の配列の長さを取得した結果が<length>である")
    fun getArrayLength(jsonPath: String, length: Int) {
        val jsonString = """
                { "arrayKey": [ { "key2": "a" }, { "key2": "b" }, { "key2": "c" } ] }
        """.trimIndent()
        val jsonNode = JsonNode.of(jsonString)
        jsonNode.getArrayLength(jsonPath) shouldBeEqualTo length
    }

    @Step("<jsonPath>の配列をキー<filterKey>の値が<filterValue>でフィルターした結果が<value>である")
    fun getJsonPath(jsonPath: String, filterKey: String, filterValue: String, value: String) {
        val jsonString = """
            {
              "arrayKey": [
                { "filterKey": "b", "key3": "1" },
                { "filterKey": "b", "key3": "2" },
                { "filterKey": "c", "key3": "3" }
              ]
            }
        """.trimIndent()
        val list = JsonNode.of(jsonString).getFilteredList(jsonPath, filterKey, filterValue)
        list shouldBeEqualTo JsonNode.of(value).get("$.*")
    }
}


