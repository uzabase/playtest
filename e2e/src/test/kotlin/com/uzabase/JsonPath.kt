package com.uzabase

import com.thoughtworks.gauge.Step
import org.amshove.kluent.`should not throw`
import org.amshove.kluent.fail
import org.amshove.kluent.invoking

class JsonPath {
    @Step("<jsonPath>が文字列の<value>であることをアサー卜できる")
    fun assertJsonPathString(jsonPath: String, value: String) {
        val json = """"
            {"key1": {"key2": "test"}}
            """".trimMargin()
        invoking { fail("not implemented") } `should not throw` AssertionError::class
    }
}
