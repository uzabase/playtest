package com.uzabase.playtest.gauge.rest.http

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.ConfigKeys
import com.uzabase.playtest.gauge.rest.DataStore
import com.uzabase.playtest.gauge.rest.GaugeRestConfig
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain

class HttpStep {
    @Step("URL<url>にGETリクエストを送る")
    fun executeGet(url: String) {
        val endpoint = getUrl() + url
        val (statusCode, headers, body) = HttpClient().executeGet(endpoint)
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にヘッダー<header>で、GETリクエストを送る")
    fun executeGet(url: String, header: String) {
        val endpoint = getUrl() + url
        val (statusCode, headers, body) = HttpClient().executeGet(endpoint, toHeaderMap(header))
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にPUTリクエストを送る")
    fun executePut(url: String) {
        val endpoint = getUrl() + url
        val (statusCode, headers, body) = HttpClient().executePut(endpoint)
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にヘッダー<header>で、PUTリクエストを送る")
    fun executePutWithHeader(url: String, header: String) {
        val endpoint = getUrl() + url
        val (statusCode, headers, body) = HttpClient().executePut(endpoint, toHeaderMap(header))
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にボディ<requestBody>、ヘッダー<header>で、PUTリクエストを送る")
    fun executePut(url: String, requestBody: String, header: String) {
        val endpoint = getUrl() + url
        val (statusCode, headers, body) = HttpClient().executePut(endpoint, requestBody, toHeaderMap(header))
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にボディ<requestBody>で、PUTリクエストを送る")
    fun executePut(url: String, requestBody: String) {
        val endpoint = getUrl() + url
        val (statusCode, headers, body) = HttpClient().executePut(
            endpoint,
            requestBody
        )
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にPOSTリクエストを送る")
    fun executePost(url: String) {
        val endpoint = getUrl() + url
        val (statusCode, headers, body) = HttpClient().executePost(endpoint)
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にヘッダー<header>で、POSTリクエストを送る")
    fun executePostWithHeader(url: String, header: String) {
        val endpoint = getUrl() + url
        val (statusCode, headers, body) = HttpClient().executePost(endpoint, toHeaderMap(header))
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にボディ<requestBody>、ヘッダー<header>で、POSTリクエストを送る")
    fun executePost(url: String, requestBody: String, header: String) {
        val endpoint = getUrl() + url
        val (statusCode, headers, body) = HttpClient().executePost(endpoint, requestBody, toHeaderMap(header))
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にボディ<requestBody>で、POSTリクエストを送る")
    fun executePost(url: String, requestBody: String) {
        val endpoint = getUrl() + url
        val (statusCode, headers, body) = HttpClient().executePost(
            endpoint,
            requestBody
        )
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にDELETEリクエストを送る")
    fun executeDelete(url: String) {
        val endpoint = getUrl() + url
        val (statusCode, headers, body) = HttpClient().executeDelete(endpoint)
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("URL<url>にヘッダー<header>で、DELETEリクエストを送る")
    fun executeDelete(url: String, header: String) {
        val endpoint = getUrl() + url
        val (statusCode, headers, body) = HttpClient().executeDelete(endpoint, toHeaderMap(header))
        DataStore.storeResponseData(statusCode, headers, body)
    }

    @Step("レスポンスステータスコードが<statusCode>である")
    fun assertStatusCodeEquals(statusCode: Int) {
        DataStore.loadStatusCodeFromScenario() shouldBeEqualTo statusCode
    }

    @Step("レスポンスヘッダーに<key>が存在し、その値が<value>である")
    fun assertResponseHeadersContain(key: String, value: String) {
        val headers = DataStore.loadResponseHeadersFromScenario()
        headers shouldContain Pair(key, value)
    }

    fun toHeaderMap(header: String): Map<String, String> {
        val list = header.split(":").map { it.trim() }
        return mapOf(list[0] to list[1])
    }

    private fun getUrl(): String {
        val protocol = GaugeRestConfig.get(ConfigKeys.URL_PROTOCOL)
        val domain = GaugeRestConfig.get(ConfigKeys.URL_DOMAIN)
        val port = GaugeRestConfig.get(ConfigKeys.URL_PORT)
        return "$protocol://$domain:$port"
    }
}
