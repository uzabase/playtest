package com.uzabase

import com.thoughtworks.gauge.Step
import org.amshove.kluent.`should not throw`
import org.amshove.kluent.invoking

class JsonPath : JsonAssert {
    @Step("<jsonPath>が文字列の<value>であることをアサー卜できる")
    fun assertJsonPathString(jsonPath: String, value: String) {
        val json = """"
            {"key1": {"key2": "test"}}
            """".trimMargin()
        invoking { json.assertByJsonPath(value, jsonPath) } `should not throw` AssertionError::class
    }

    @Step("<jsonPath>が真偽値の<value>であることをアサー卜できる")
    fun assertJsonPathBoolean(jsonPath: String, value: Boolean) {
        val json = """"
            {"key1": {"key2": $value}}
            """".trimMargin()
        invoking { json.assertByJsonPath(value, jsonPath) } `should not throw` AssertionError::class
    }

    @Step("<jsonPath>が整数値の<value>であることをアサー卜できる")
    fun assertJsonPathInt(jsonPath: String, value: Boolean) {
        val json = """"
            {"key1": {"key2": 1}}
            """".trimMargin()
        invoking { json.assertByJsonPath(value, jsonPath) } `should not throw` AssertionError::class
    }
}
