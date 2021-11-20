package com.uzabase.playtest.gauge.rest

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore.storeResponseBodyToScenario
import com.uzabase.playtest.gauge.rest.http.ResponseBody
import com.uzabase.playtest.json.JsonNode
import org.amshove.kluent.shouldBeEqualTo

class TestStep {
    @Step("レスポンスボディとしてシナリオデータストアに<json>を保存する")
    fun storeJson(json: String) {
        storeResponseBodyToScenario(ResponseBody(json, json.toByteArray()))
    }

    @Step("JSON形式のレスポンスボディが<jsonBody>と一致する")
    fun assertJsonResponseBodyEquals(jsonBody: String) {
        val expected = JsonNode.of(jsonBody).toJsonString()
        val actual = DataStore.loadResponseBodyFromScenario().string.let { JsonNode.of(it) }.toJsonString()
        actual shouldBeEqualTo expected
    }
}
