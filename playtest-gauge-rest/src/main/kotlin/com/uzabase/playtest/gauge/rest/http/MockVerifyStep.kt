package com.uzabase.playtest.gauge.rest.http

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder
import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.ConfigKeys
import com.uzabase.playtest.gauge.rest.GaugeRestConfig
import java.lang.RuntimeException

class MockVerifyStep {
    private val host = GaugeRestConfig.get(ConfigKeys.BASE_URL).split(":")[1].removePrefix("//")
    private val port = GaugeRestConfig.get(ConfigKeys.BASE_URL).split(":")[2].toInt()
    private val client = WireMock(host, port)

    @Step("URL<url>にGETリクエストされた")
    fun assertGetRequestExecutedWithBody(url: String) {
        client.verifyThat(1, getRequestedFor(urlEqualTo(url)))
    }

    @Step("URL<url>にヘッダー<header>で、GETリクエストされた")
    fun assertGetRequestExecuted(url: String, header: String){
        val requested = getRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested)
    }

    @Step("API<apiName>のURL<url>にPOSTリクエストされた")
    fun assertPostRequestExecutedWithBody(url: String) {
        TODO()
        //client.verifyThat(1, postRequestedFor(urlEqualTo(url)))
    }

    @Step("URL<url>にボディ<jsonFilePath>JSONファイルの内容でPOSTリクエストされた")
    fun assertPostRequestExecutedWithBody(url: String, jsonFilePath: String) {
        client.verifyThat(1, postRequestedFor(urlEqualTo(url)).withRequestBody(readJsonFileToValuePattern(jsonFilePath)))
    }

    @Step("URL<url>にボディ<jsonFilePath>JSONファイルの内容、ヘッダー<header>で、POSTリクエストされた")
    fun assertPostRequestExecuted(url: String, jsonFilePath: String, header: String) {
        val requested = postRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested.withRequestBody(readJsonFileToValuePattern(jsonFilePath)))
    }

    @Step("URL<url>にヘッダー<header>で、POSTリクエストされた")
    fun assertPostRequestExecuted(url: String, header: String){
        val requested = postRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested)
    }

    @Step("URL<url>にPUTリクエストされた")
    fun assertPutRequestExecutedWithBody(url: String) {
        client.verifyThat(1, putRequestedFor(urlEqualTo(url)))
    }

    @Step("URL<url>にボディ<jsonFilePath>JSONファイルの内容でPUTリクエストされた")
    fun assertPutRequestExecutedWithBody(url: String, jsonFilePath: String) {
        client.verifyThat(1, putRequestedFor(urlEqualTo(url)).withRequestBody(readJsonFileToValuePattern(jsonFilePath)))
    }

    @Step("URL<url>にボディ<jsonFilePath>JSONファイルの内容、ヘッダー<header>で、PUTリクエストされた")
    fun assertPutRequestExecuted(url: String, jsonFilePath: String, header: String){
        val requested = putRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested.withRequestBody(readJsonFileToValuePattern(jsonFilePath)))
    }

    @Step("URL<url>にヘッダー<header>で、PUTリクエストされた")
    fun assertPutRequestExecuted(url: String, header: String) {
        val requested = putRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested)
    }

    @Step("URL<url>にDELETEリクエストされた")
    fun assertDeleteRequestExecutedWithBody(url: String) {
        client.verifyThat(1, deleteRequestedFor(urlEqualTo(url)))
    }

    @Step("URL<url>にヘッダー<header>で、DELETEリクエストされた")
    fun assertDeleteRequestExecuted(url: String, header: String){
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
}