package com.uzabase.playtest.gauge.rest.json

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore
import com.uzabase.playtest.json.JsonNode
import org.junit.jupiter.api.Assertions.assertNull

class JsonNullStep {
    @Step("レスポンスのJSONの<jsonPath>がnullである")
    fun assertEquals(jsonPath: String) {
        DataStore.loadResponseBodyFromScenario().string.let {
            JsonNode.of(it).get<Any>(jsonPath)
        }.run { assertNull(this) }
    }
}