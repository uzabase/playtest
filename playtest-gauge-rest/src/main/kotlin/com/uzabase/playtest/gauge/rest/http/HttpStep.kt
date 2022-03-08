package com.uzabase.playtest.gauge.rest.http

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.ConfigKeys
import com.uzabase.playtest.gauge.rest.DataStore
import com.uzabase.playtest.gauge.rest.GaugeRestConfig
import org.junit.jupiter.api.Assertions
import java.net.URL

class HttpStep {
    @Step("URL<url>にGETリクエストを送る")
    fun executeGet(url: String) {
        val endpoint = generateEndpoint(url)
        val (statusCode, headers, body) = HttpClient().executeGet(endpoint.toString())
        DataStore.storeResponseData(statusCode, headers, body)
    }


    @Step("URL<url>にヘッダー<header>で、GETリクエストを送る")
    fun executeGet(url: String, header: String) {
        val endpoint = generateEndpoint(url)
        val (statusCode, headers, body) = HttpClient().executeGet(endpoint.toString(), toHeaderMap(header))
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にPUTリクエストを送る")
    fun executePut(url: String) {
        val endpoint = generateEndpoint(url)
        val (statusCode, headers, body) = HttpClient().executePut(endpoint.toString())
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にヘッダー<header>で、PUTリクエストを送る")
    fun executePutWithHeader(url: String, header: String) {
        val endpoint = generateEndpoint(url)
        val (statusCode, headers, body) = HttpClient().executePut(endpoint.toString(), toHeaderMap(header))
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にボディ<requestBody>、ヘッダー<header>で、PUTリクエストを送る")
    fun executePut(url: String, requestBody: String, header: String) {
        val endpoint = generateEndpoint(url)
        val (statusCode, headers, body) = HttpClient().executePut(endpoint.toString(), requestBody, toHeaderMap(header))
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にボディ<requestBody>で、PUTリクエストを送る")
    fun executePut(url: String, requestBody: String) {
        val endpoint = generateEndpoint(url)
        val (statusCode, headers, body) = HttpClient().executePut(
                endpoint.toString(),
                requestBody
        )
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にPOSTリクエストを送る")
    fun executePost(url: String) {
        val endpoint = generateEndpoint(url)
        val (statusCode, headers, body) = HttpClient().executePost(endpoint.toString())
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にヘッダー<header>で、POSTリクエストを送る")
    fun executePostWithHeader(url: String, header: String) {
        val endpoint = generateEndpoint(url)
        val (statusCode, headers, body) = HttpClient().executePost(endpoint.toString(), toHeaderMap(header))
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にボディ<requestBody>、ヘッダー<header>で、POSTリクエストを送る")
    fun executePost(url: String, requestBody: String, header: String) {
        val endpoint = generateEndpoint(url)
        val (statusCode, headers, body) = HttpClient().executePost(endpoint.toString(), requestBody, toHeaderMap(header))
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にボディ<requestBody>で、POSTリクエストを送る")
    fun executePost(url: String, requestBody: String) {
        val endpoint = generateEndpoint(url)
        val (statusCode, headers, body) = HttpClient().executePost(
                endpoint.toString(),
                requestBody
        )
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にDELETEリクエストを送る")
    fun executeDelete(url: String) {
        val endpoint = generateEndpoint(url)
        val (statusCode, headers, body) = HttpClient().executeDelete(endpoint.toString())
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にヘッダー<header>で、DELETEリクエストを送る")
    fun executeDelete(url: String, header: String) {
        val endpoint = generateEndpoint(url)
        val (statusCode, headers, body) = HttpClient().executeDelete(endpoint.toString(), toHeaderMap(header))
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("レスポンスステータスコードが<statusCode>である")
    fun assertStatusCodeEquals(statusCode: Int) {
        Assertions.assertEquals(statusCode, DataStore.loadStatusCodeFromScenario())
    }

    @Step("レスポンスヘッダーに<key>が存在し、その値が<value>である")
    fun assertResponseHeadersContain(key: String, value: String) {
        val headers = DataStore.loadResponseHeadersFromScenario()
        //headers shouldContain Pair(key, value)
        Assertions.assertTrue(headers.getValue(key) == value)
    }

    @Step("レスポンスボディが文字列<stringValue>である")
    fun assertResponseBodyStringEquals(stringValue: String) {
        Assertions.assertEquals(DataStore.loadResponseBodyFromScenario().string, stringValue)
    }

    fun toHeaderMap(header: String): Map<String, String> {
        return header
                .split("r\n")
                .associate {
                    it.split(":")
                            .map { v -> v.trim() }
                            .let { h -> h[0] to h[1] }
                }
    }

    private fun generateEndpoint(url: String) = URL(URL(getBaseUrl()), url)

    private fun getBaseUrl(): String {
        return GaugeRestConfig.get(ConfigKeys.BASE_URL)
    }
}
