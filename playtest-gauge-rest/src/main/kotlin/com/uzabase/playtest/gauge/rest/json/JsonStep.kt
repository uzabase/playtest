package com.uzabase.playtest.gauge.rest.json

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore.loadResponseBodyFromScenario
import com.uzabase.playtest.json.JsonNode
import org.junit.jupiter.api.Assertions

class JsonStep {
    @Step("レスポンスのJSONの<jsonPath>が存在しない")
    fun assertNotExists(jsonPath: String) {
        Assertions.assertEquals(null, JsonNode.of(loadResponseBodyFromScenario().string).get<Any>(jsonPath))
    }
}

internal fun JsonNode.getUniqElementInArray(
    arrayJsonPath: String,
    uniqueKey: String,
    filterValue: String
) = this.getFilteredList(arrayJsonPath, uniqueKey, filterValue)
    .takeIf { it?.size == 1 }
    ?.first()
    ?: throw IllegalArgumentException("filter: $uniqueKey == $filterValue can not specify element in $arrayJsonPath")
