package com.uzabase.playtest.gauge.rest

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.thoughtworks.gauge.*
import com.uzabase.playtest.gauge.rest.TestConfig.wiremockPort
import com.uzabase.playtest.gauge.rest.http.HttpStep

class Wiremock {
    private val path: String = javaClass.getResource("/com/uzabase/playtest/gauge/rest")!!.path
    private val options = options()
        .port(TestConfig.getInt(wiremockPort))
        .usingFilesUnderDirectory(path)
    private val wireMockServer: WireMockServer = WireMockServer(options)
    private val client: WireMock = WireMock("localhost", TestConfig.getInt(wiremockPort))
    private val httpStep = HttpStep()

    @BeforeSuite()
    fun before() {
        wireMockServer.start()
    }

    @AfterSuite()
    fun after() {
        wireMockServer.stop()
    }

    @BeforeScenario(tags = ["http-request-test"])
    fun afterHttpRequestTestScenario(context: ExecutionContext) {
        client.resetRequests()
    }

    @Step("URL<url>にGETリクエストが送信された")
    fun assertGetRequestExecuted(url: String) {
        client.verifyThat(getRequestedFor(urlEqualTo(url)))
    }

    @Step("URL<url>にヘッダー<header>で、GETリクエストが送信された")
    fun assertGetRequestExecuted(url: String, header: String) {
        val headerEntry = httpStep.toHeaderMap(header).entries.first()
        client.verifyThat(getRequestedFor(urlEqualTo(url)).withHeader(headerEntry.key.value, equalTo(headerEntry.value.joinToString(", "))))
    }

    @Step("URL<url>にPUTリクエストが送信された")
    fun assertPutRequestExecuted(url: String) {
        client.verifyThat(putRequestedFor(urlEqualTo(url)))
    }

    @Step("URL<url>にボディ<requestBody>で、PUTリクエストが送信された")
    fun assertPutRequestExecutedWithBody(url: String, body: String) {
        client.verifyThat(putRequestedFor(urlEqualTo(url)).withRequestBody(equalTo(body)))
    }

    @Step("URL<url>にヘッダー<header>で、PUTリクエストが送信された")
    fun assertPutRequestExecuted(url: String, header: String) {
        val headerEntry = httpStep.toHeaderMap(header).entries.first()
        client.verifyThat(putRequestedFor(urlEqualTo(url)).withHeader(headerEntry.key.value, equalTo(headerEntry.value.joinToString(", "))))
    }

    @Step("URL<url>にボディ<requestBody>、ヘッダー<header>で、PUTリクエストが送信された")
    fun assertPutRequestExecuted(url: String, body: String, header: String) {
        val headerEntry = httpStep.toHeaderMap(header).entries.first()
        client.verifyThat(
            putRequestedFor(urlEqualTo(url)).withRequestBody(equalTo(body))
                .withHeader(headerEntry.key.value, equalTo(headerEntry.value.joinToString(", ")))
        )
    }

    @Step("URL<url>にPOSTリクエストが送信された")
    fun assertPostRequestExecuted(url: String) {
        client.verifyThat(postRequestedFor(urlEqualTo(url)))
    }

    @Step("URL<url>にボディ<requestBody>で、POSTリクエストが送信された")
    fun assertPostRequestExecutedWithBody(url: String, body: String) {
        client.verifyThat(postRequestedFor(urlEqualTo(url)).withRequestBody(equalTo(body)))
    }

    @Step("URL<url>にヘッダー<header>で、POSTリクエストが送信された")
    fun assertPostRequestExecuted(url: String, header: String) {
        val headerEntry = httpStep.toHeaderMap(header).entries.first()
        client.verifyThat(postRequestedFor(urlEqualTo(url)).withHeader(headerEntry.key.value, equalTo(headerEntry.value.joinToString(", "))))
    }

    @Step("URL<url>にボディ<requestBody>、ヘッダー<header>で、POSTリクエストが送信された")
    fun assertPostRequestExecuted(url: String, body: String, header: String) {
        val headerEntry = httpStep.toHeaderMap(header).entries.first()
        client.verifyThat(
            postRequestedFor(urlEqualTo(url)).withRequestBody(equalTo(body))
                .withHeader(headerEntry.key.value, equalTo(headerEntry.value.joinToString(", ")))
        )
    }

    @Step("URL<url>にDELETEリクエストが送信された")
    fun assertDeleteRequestExecuted(url: String) {
        client.verifyThat(deleteRequestedFor(urlEqualTo(url)))
    }

    @Step("URL<url>にヘッダー<header>で、DELETEリクエストが送信された")
    fun assertDeleteRequestExecuted(url: String, header: String) {
        val headerEntry = httpStep.toHeaderMap(header).entries.first()
        client.verifyThat(deleteRequestedFor(urlEqualTo(url)).withHeader(headerEntry.key.value, equalTo(headerEntry.value.joinToString(", "))))
    }
}
