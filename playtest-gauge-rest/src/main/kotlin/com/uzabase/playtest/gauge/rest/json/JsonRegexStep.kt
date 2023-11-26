package com.uzabase.playtest.gauge.rest.json

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore
import com.uzabase.playtest.json.JsonNode
import org.junit.jupiter.api.Assertions.assertFalse
import kotlin.test.assertTrue


class JsonRegexStep {
    @Step("レスポンスのJSONの<jsonPath>が<pattern>である")
    fun assertRegexMatch(jsonPath: String, pattern: String) {
        val regex = Regex(pattern)
        DataStore.loadResponseBodyFromScenario().string.let {
            JsonNode.of(it).getValue<String>(jsonPath)
        }.run {
            assertTrue(regex.matches(this!!))
        }
    }

    @Step("レスポンスのJSONの<jsonPath>が<pattern>でない")
    fun assertRegexNotMatch(jsonPath: String, pattern: String) {
        val regex = Regex(pattern)
        DataStore.loadResponseBodyFromScenario().string.let {
            JsonNode.of(it).getValue<String>(jsonPath)
        }.run {
            assertFalse(regex.matches(this!!))
        }
    }
}