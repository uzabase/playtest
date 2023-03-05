package com.uzabase.playtest.gauge.rest.http

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder
import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.ConfigKeys
import com.uzabase.playtest.gauge.rest.GaugeRestConfig
import com.uzabase.playtest.json.JsonNode
import java.net.URL
import kotlin.test.assertEquals

class MockVerifyStep {
    @Step("API<apiName>のURL<url>に<callCount>回GETリクエストされた")
    fun assertCallCountOfGetRequest(apiName: String, url: String, callCount: Int) {
        val client = getWireMock(apiName)
        client.verifyThat(callCount, getRequestedFor(urlEqualTo(url)))
    }

    @Step("API<apiName>のURL<url>に<callCount>回POSTリクエストされた")
    fun assertCallCountOfPostRequest(apiName: String, url: String, callCount: Int) {
        val client = getWireMock(apiName)
        client.verifyThat(callCount, postRequestedFor(urlEqualTo(url)))
    }

    @Step("API<apiName>のURL<url>に<callCount>回PUTリクエストされた")
    fun assertCallCountOfPutRequest(apiName: String, url: String, callCount: Int) {
        val client = getWireMock(apiName)
        client.verifyThat(callCount, putRequestedFor(urlEqualTo(url)))
    }

    @Step("API<apiName>のURL<url>に<callCount>回DELETEリクエストされた")
    fun assertCallCountOfDeleteRequest(apiName: String, url: String, callCount: Int) {
        val client = getWireMock(apiName)
        client.verifyThat(callCount, deleteRequestedFor(urlEqualTo(url)))
    }

//    rewriting bellow

    @Step("API<apiName>のURL<url>にヘッダー<header>で、GETリクエストされた")
    fun assertGetRequestExecuted(apiName: String, url: String, header: String) {
        val client = getWireMock(apiName)
        val requested = getRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested)
    }

    @Step("API<apiName>のURLパス<urlPath>にクエリパラメータ<queryParameterName>が<queryParameterValue>でGETリクエストされた")
    fun assertGetRequestExecutedWithQueryParameter(
        apiName: String,
        urlPath: String,
        queryParameterName: String,
        queryParameterValue: String
    ) {
        getWireMock(apiName).run {
            verifyThat(
                getRequestedFor(urlPathEqualTo(urlPath)).withQueryParam(
                    queryParameterName,
                    equalTo(queryParameterValue)
                )
            )
        }
    }

    @Step("API<apiName>のURL<url>にボディ<jsonFilePath>JSONファイルの内容でPOSTリクエストされた")
    fun assertPostRequestExecutedWithJsonFile(apiName: String, url: String, jsonFilePath: String) {
        val client = getWireMock(apiName)
        client.verifyThat(
            1,
            postRequestedFor(urlEqualTo(url)).withRequestBody(readJsonFileToValuePattern(jsonFilePath))
        )
    }

    @Step("API<apiName>のURL<url>にボディ<json>でPOSTリクエストされた")
    fun assertPostRequestExecutedWithJson(apiName: String, url: String, json: String) {
        val client = getWireMock(apiName)
        client.verifyThat(1, postRequestedFor(urlEqualTo(url)).withRequestBody(equalToJson(json)))
    }

    @Step("API<apiName>のURL<url>にボディ<jsonFilePath>JSONファイルの内容、ヘッダー<header>で、POSTリクエストされた")
    fun assertPostRequestExecutedWithJsonFile(apiName: String, url: String, jsonFilePath: String, header: String) {
        val client = getWireMock(apiName)
        val requested = postRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested.withRequestBody(readJsonFileToValuePattern(jsonFilePath)))
    }

    @Step("API<apiName>のURL<url>にボディ<json>、ヘッダー<header>で、POSTリクエストされた")
    fun assertPostRequestExecutedWithJson(apiName: String, url: String, json: String, header: String) {
        val client = getWireMock(apiName)
        val requested = postRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested.withRequestBody(equalToJson(json)))
    }

    @Step("API<apiName>のURL<url>にヘッダー<header>で、POSTリクエストされた")
    fun assertPostRequestExecuted(apiName: String, url: String, header: String) {
        val client = getWireMock(apiName)
        val requested = postRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested)
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に文字列<value>を持つJSONでPOSTリクエストされた")
    fun assertPostRequestExecutedWithJsonPath(apiName: String, url: String, jsonPath: String, value: String) {
        val client = getWireMock(apiName)
        client.verifyRequestWithJson(url, jsonPath, value)
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に整数値<value>を持つJSONでPOSTリクエストされた")
    fun assertPostRequestExecutedWithJsonPath(apiName: String, url: String, jsonPath: String, value: Int) {
        val client = getWireMock(apiName)
        client.verifyRequestWithJson(url, jsonPath, value)
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に小数値<value>を持つJSONでPOSTリクエストされた")
    fun assertPostRequestExecutedWithJsonPath(apiName: String, url: String, jsonPath: String, value: Double) {
        val client = getWireMock(apiName)
        client.verifyRequestWithJson(url, jsonPath, value)
    }


    @Step("API<apiName>のURL<url>にパス<jsonPath>に真偽値<value>を持つJSONでPOSTリクエストされた")
    fun assertPostRequestExecutedWithJsonPath(apiName: String, url: String, jsonPath: String, value: Boolean) {
        val client = getWireMock(apiName)
        client.verifyRequestWithJson(url, jsonPath, value)
    }

    @Step("API<apiName>のURL<url>にボディ<jsonFilePath>JSONファイルの内容でPUTリクエストされた")
    fun assertPutRequestExecutedWithJsonFile(apiName: String, url: String, jsonFilePath: String) {
        val client = getWireMock(apiName)
        client.verifyThat(1, putRequestedFor(urlEqualTo(url)).withRequestBody(readJsonFileToValuePattern(jsonFilePath)))
    }

    @Step("API<apiName>のURL<url>にボディ<json>でPUTリクエストされた")
    fun assertPutRequestExecutedWithJson(apiName: String, url: String, json: String) {
        val client = getWireMock(apiName)
        client.verifyThat(1, putRequestedFor(urlEqualTo(url)).withRequestBody(equalToJson(json)))
    }

    @Step("API<apiName>のURL<url>にボディ<jsonFilePath>JSONファイルの内容、ヘッダー<header>で、PUTリクエストされた")
    fun assertPutRequestExecutedWithJsonFile(apiName: String, url: String, jsonFilePath: String, header: String) {
        val client = getWireMock(apiName)
        val requested = putRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested.withRequestBody(readJsonFileToValuePattern(jsonFilePath)))
    }

    @Step("API<apiName>のURL<url>にボディ<json>、ヘッダー<header>で、PUTリクエストされた")
    fun assertPutRequestExecutedWithJson(apiName: String, url: String, json: String, header: String) {
        val client = getWireMock(apiName)
        val requested = putRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested.withRequestBody(equalToJson(json)))
    }

    @Step("API<apiName>のURL<url>にヘッダー<header>で、PUTリクエストされた")
    fun assertPutRequestExecuted(apiName: String, url: String, header: String) {
        val client = getWireMock(apiName)
        val requested = putRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested)
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に文字列<value>を持つJSONでPUTリクエストされた")
    fun assertPutRequestExecutedWithJsonPath(apiName: String, url: String, jsonPath: String, value: String) {
        val client = getWireMock(apiName)
        client.verifyRequestWithJson(url, jsonPath, value)
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に整数値<value>を持つJSONでPUTリクエストされた")
    fun assertPutRequestExecutedWithJsonPath(apiName: String, url: String, jsonPath: String, value: Int) {
        val client = getWireMock(apiName)
        client.verifyRequestWithJson(url, jsonPath, value)
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に小数値<value>を持つJSONでPUTリクエストされた")
    fun assertPutRequestExecutedWithJsonPath(apiName: String, url: String, jsonPath: String, value: Double) {
        val client = getWireMock(apiName)
        client.verifyRequestWithJson(url, jsonPath, value)
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に真偽値<value>を持つJSONでPUTリクエストされた")
    fun assertPutRequestExecutedWithJsonPath(apiName: String, url: String, jsonPath: String, value: Boolean) {
        val client = getWireMock(apiName)
        client.verifyRequestWithJson(url, jsonPath, value)
    }

    @Step("API<apiName>のURL<url>にヘッダー<header>で、DELETEリクエストされた")
    fun assertDeleteRequestExecuted(apiName: String, url: String, header: String) {
        val client = getWireMock(apiName)
        val requested = deleteRequestedFor(urlEqualTo(url))
        headerBuilder(header, requested)
        client.verifyThat(requested)
    }

    @Step("API<apiName>のURL<url>にヘッダー<header>:<value>を含むリクエストをされた")
    fun assertHeader(apiName: String, url: String, headerKey: String, value: String) {
        val client = getWireMock(apiName)
        client.verifyThat(
            RequestPatternBuilder
                .newRequestPattern()
                .withUrl(url)
                .withHeader(headerKey, containing(value))
        )
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に文字列<value>を持つJSONをリクエストされた")
    fun assertBodyString(apiName: String, url: String, jsonPath: String, value: String) {
        val client = getWireMock(apiName)
        client.verifyRequestWithJson(url, jsonPath, value)
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に整数値<value>を持つJSONをリクエストされた")
    fun assertBodyInt(apiName: String, url: String, jsonPath: String, value: Int) {
        val client = getWireMock(apiName)
        client.verifyRequestWithJson(url, jsonPath, value)
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に小数値<value>を持つJSONをリクエストされた")
    fun assertBodyDouble(apiName: String, url: String, jsonPath: String, value: Double) {
        val client = getWireMock(apiName)
        client.verifyRequestWithJson(url, jsonPath, value)
    }

    @Step("API<apiName>のURL<url>にパス<jsonPath>に真偽値<value>を持つJSONをリクエストされた")
    fun assertBodyDouble(apiName: String, url: String, jsonPath: String, value: Boolean) {
        val client = getWireMock(apiName)
        client.verifyRequestWithJson(url, jsonPath, value)
    }

    private fun headerBuilder(
        header: String,
        requested: RequestPatternBuilder
    ) {
        HttpStep().toHeaderMap(header).entries.forEach {
            requested.withHeader(it.key.value, equalTo(it.value.joinToString(", ")))
        }
    }

    @Deprecated("Should use special parameters")
    private fun readJsonFileToValuePattern(jsonFilePath: String) = equalToJson(
        try {
            javaClass.getResourceAsStream(jsonFilePath).bufferedReader().readLines().joinToString("\n")
        } catch (e: Throwable) {
            throw RuntimeException("Failed to read $jsonFilePath", e)
        }
    )

    private fun getWireMock(apiName: String): WireMock =
        GaugeRestConfig.get(apiName, ConfigKeys.BASE_URL)
            .let(::URL)
            .let { WireMock(it.host, it.port) }

    private inline fun <reified T> WireMock.verifyRequestWithJson(endpoint: String, jsonPath: String, value: T) {
        val test = this.find(RequestPatternBuilder.newRequestPattern().withUrl(endpoint)).first().bodyAsString
        val json = JsonNode.of(test)
        assertEquals(value, json.get(jsonPath))
    }
}
