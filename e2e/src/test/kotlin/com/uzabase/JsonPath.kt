package com.uzabase

import com.thoughtworks.gauge.Step
import org.amshove.kluent.`should not throw`
import org.amshove.kluent.invoking

class JsonPath : JsonAssert {
    @Step("<jsonPath>の値を取得した結果が文字列の<value>である")
    fun getJsonPathAsString(jsonPath: String, value: String) {
        // JsonNodeから値を取得した結果、なんかしらのライブラリでアサーションする
        invoking { TODO() } `should not throw` AssertionError::class
    }

    @Step("<jsonPath>の値を取得した結果が真偽値の<value>である")
    fun getJsonPathAsBoolean(jsonPath: String, value: String) {
        // JsonNodeから値を取得した結果、なんかしらのライブラリでアサーションする
        invoking { TODO() } `should not throw` AssertionError::class
    }

    @Step("<jsonPath>の値を取得した結果が整数の<value>である")
    fun getJsonPathAsInteger(jsonPath: String, value: String) {
        invoking { TODO() } `should not throw` AssertionError::class
    }

    @Step("<jsonPath>の値を取得した結果が小数の<value>である")
    fun getJsonPathAsFloat(jsonPath: String, value: String) {
        invoking { TODO() } `should not throw` AssertionError::class
    }
}


