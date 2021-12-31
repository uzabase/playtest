package com.uzabase.playtest.gauge.rest.http

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder
import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.ConfigKeys
import com.uzabase.playtest.gauge.rest.GaugeRestConfig
import com.uzabase.playtest.json.JsonNode
import java.lang.RuntimeException
import kotlin.test.assertEquals

class MockVerifyStep {
    private val host = GaugeRestConfig.get(ConfigKeys.BASE_URL).split(":")[1].removePrefix("//")
    private val port = GaugeRestConfig.get(ConfigKeys.BASE_URL).split(":")[2].toInt()
    private val client = WireMock(host, port)

    @Step("API<apiName>のURL<url>にGETリクエストされた")
    fun assertGetRequestExecutedWithBody(apiName: String, url: String) {
        val client = getWireMock(apiName)
        client.verifyThat(1, getRequestedFor(urlEqualTo(url)))
    }

    @Step("API<apiName>のURL<url>にヘッダー<header>で、GETリクエストされた")
    fun assertGetRequestExecuted(apiName: String, url: String, header: String){
        val client = getWireMock(apiName)
        val requested = getRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested)
    }

    @Step("API<apiName>のURL<url>にPOSTリクエストされた")
    fun assertPostRequestExecutedWithBody(apiName: String, url: String) {
        val client = getWireMock(apiName)
        client.verifyThat(1, postRequestedFor(urlEqualTo(url)))
    }

    @Step("API<apiName>のURL<url>にボディ<jsonFilePath>JSONファイルの内容でPOSTリクエストされた")
    fun assertPostRequestExecutedWithBody(apiName: String, url: String, jsonFilePath: String) {
        val client = getWireMock(apiName)
        client.verifyThat(1, postRequestedFor(urlEqualTo(url)).withRequestBody(readJsonFileToValuePattern(jsonFilePath)))
    }

    @Step("API<apiName>のURL<url>にボディ<jsonFilePath>JSONファイルの内容、ヘッダー<header>で、POSTリクエストされた")
    fun assertPostRequestExecuted(apiName: String, url: String, jsonFilePath: String, header: String) {
        val client = getWireMock(apiName)
        val requested = postRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested.withRequestBody(readJsonFileToValuePattern(jsonFilePath)))
    }

    @Step("API<apiName>のURL<url>にヘッダー<header>で、POSTリクエストされた")
    fun assertPostRequestExecuted(apiName: String, url: String, header: String){
        val client = getWireMock(apiName)
        val requested = postRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested)
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に文字列<value>を持つJSONでPOSTリクエストされた")
    fun assertPostRequestExecutedWithJsonPath(apiName: String, url: String, jsonPath: String, value: String){
        val client = getWireMock(apiName)
        client.verifyPostRequestWithJsonPath(url, jsonPath, value)
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に整数値<value>を持つJSONでPOSTリクエストされた")
    fun assertPostRequestExecutedWithJsonPath(apiName: String, url: String, jsonPath: String, value: Int){
        val client = getWireMock(apiName)
        client.verifyPostRequestWithJsonInteger(url, jsonPath, value)
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に小数値<value>を持つJSONでPOSTリクエストされた")
    fun assertPostRequestExecutedWithJsonPath(apiName: String, url: String, jsonPath: String, value: Double){
        val client = getWireMock(apiName)
        client.verifyPostRequestWithJsonDouble(url, jsonPath, value)
    }


    @Step("API<apiName>のURL<url>にパス<jsonPath>に真偽値<value>を持つJSONでPOSTリクエストされた")
    fun assertPostRequestExecutedWithJsonPath(apiName: String, url: String, jsonPath: String, value: Boolean){
        val client = getWireMock(apiName)
        client.verifyPostRequestWithJsonBoolean(url, jsonPath, value)
    }

    @Step("API<apiName>のURL<url>にPUTリクエストされた")
    fun assertPutRequestExecutedWithBody(apiName: String, url: String) {
        val client = getWireMock(apiName)
        client.verifyThat(1, putRequestedFor(urlEqualTo(url)))
    }

    @Step("API<apiName>のURL<url>にボディ<jsonFilePath>JSONファイルの内容でPUTリクエストされた")
    fun assertPutRequestExecutedWithBody(apiName: String, url: String, jsonFilePath: String) {
        val client = getWireMock(apiName)
        client.verifyThat(1, putRequestedFor(urlEqualTo(url)).withRequestBody(readJsonFileToValuePattern(jsonFilePath)))
    }

    @Step("API<apiName>のURL<url>にボディ<jsonFilePath>JSONファイルの内容、ヘッダー<header>で、PUTリクエストされた")
    fun assertPutRequestExecuted(apiName: String, url: String, jsonFilePath: String, header: String){
        val client = getWireMock(apiName)
        val requested = putRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested.withRequestBody(readJsonFileToValuePattern(jsonFilePath)))
    }

    @Step("API<apiName>のURL<url>にヘッダー<header>で、PUTリクエストされた")
    fun assertPutRequestExecuted(apiName: String, url: String, header: String) {
        val client = getWireMock(apiName)
        val requested = putRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested)
    }

    @Step("API<apiName>のURL<url>にDELETEリクエストされた")
    fun assertDeleteRequestExecutedWithBody(apiName: String, url: String) {
        val client = getWireMock(apiName)
        client.verifyThat(1, deleteRequestedFor(urlEqualTo(url)))
    }

    @Step("API<apiName>のURL<url>にヘッダー<header>で、DELETEリクエストされた")
    fun assertDeleteRequestExecuted(apiName: String, url: String, header: String){
        val client = getWireMock(apiName)
        val requested = deleteRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested)
    }

    private fun headerBuilder(
        header: String,
        requested: RequestPatternBuilder
    ) {
        HttpStep().toHeaderMap(header).entries.forEach {
            requested.withHeader(it.key, equalTo(it.value))
        }
    }

    private fun readJsonFileToValuePattern(jsonFilePath: String) = equalToJson(
        try {
            javaClass.getResourceAsStream(jsonFilePath).bufferedReader().readLines().joinToString("\n")
        } catch (e: Throwable) {
            throw RuntimeException("Failed to read $jsonFilePath", e)
        }
    )

    private fun getWireMock(apiName: String): WireMock {
        val config = GaugeRestConfig.get(apiName, ConfigKeys.BASE_URL)
        val host = config.split(":")[1].removePrefix("//")
        val port = config.split(":")[2].toInt()
        return WireMock(host, port)
    }

    private fun WireMock.verifyPostRequestWithJsonPath(path: String, jsonPath: String, value: String){
        val pattern = matchingJsonPath(jsonPath, equalTo(value))
        this.verifyThat(
            postRequestedFor(urlEqualTo(path))
                .withRequestBody(pattern)
                .withHeader("content-type", containing("application/json"))
        )
    }

    private fun WireMock.verifyPostRequestWithJsonInteger(endpoint: String, jsonPath: String, value: Int) {
        val test = this.find(RequestPatternBuilder.newRequestPattern().withUrl(endpoint)).first().bodyAsString
        val json = JsonNode.of(test)
        assertEquals(json.get<Int>(jsonPath), value)
    }

    private fun WireMock.verifyPostRequestWithJsonDouble(endpoint: String, jsonPath: String, value: Double) {
        val test = this.find(RequestPatternBuilder.newRequestPattern().withUrl(endpoint)).first().bodyAsString
        val json = JsonNode.of(test)
        assertEquals(json.get<Double>(jsonPath), value)
    }

    private fun WireMock.verifyPostRequestWithJsonBoolean(endpoint: String, jsonPath: String, value: Boolean) {
        val test = this.find(RequestPatternBuilder.newRequestPattern().withUrl(endpoint)).first().bodyAsString
        val json = JsonNode.of(test)
        assertEquals(json.get<Boolean>(jsonPath), value)
    }
}