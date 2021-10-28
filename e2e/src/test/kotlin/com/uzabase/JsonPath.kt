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
}


