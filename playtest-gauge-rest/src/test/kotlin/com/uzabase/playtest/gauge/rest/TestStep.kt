package com.uzabase.playtest.gauge.rest

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore.storeResponseBodyToScenario
import com.uzabase.playtest.gauge.rest.http.ResponseBody
import com.uzabase.playtest.json.JsonNode
import org.amshove.kluent.shouldBeEqualTo
import java.io.BufferedInputStream

class TestStep {
    @Step("レスポンスボディとしてシナリオデータストアに<value>を保存する")
    fun storeValue(value: String) {
        storeResponseBodyToScenario(ResponseBody(value, value.toByteArray()))
    }

    @Step("レスポンスボディとしてシナリオデータストアにファイル<path>を保存する")
    fun storeBinary(path: String) {
        val byteArray = javaClass
            .getResourceAsStream(path)
            .readAllBytes()
        storeResponseBodyToScenario(ResponseBody(String(byteArray), byteArray))
    }

    @Step("JSON形式のレスポンスボディが<jsonBody>と一致する")
    fun assertJsonResponseBodyEquals(jsonBody: String) {
        val expected = JsonNode.of(jsonBody).toJsonString()
        val actual = DataStore.loadResponseBodyFromScenario().string.let { JsonNode.of(it) }.toJsonString()
        actual shouldBeEqualTo expected
    }
}

