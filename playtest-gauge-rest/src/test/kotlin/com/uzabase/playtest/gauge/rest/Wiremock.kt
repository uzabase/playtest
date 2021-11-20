package com.uzabase.playtest.gauge.rest

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.thoughtworks.gauge.*
import com.uzabase.playtest.gauge.rest.TestConfig.wiremockPort

class Wiremock {
    private val path: String = javaClass.getResource("/com/uzabase/playtest/gauge/rest")!!.path
    private val options = options()
        .port(TestConfig.getInt(wiremockPort))
        .usingFilesUnderDirectory(path)
    private val wireMockServer: WireMockServer = WireMockServer(options)
    private val client: WireMock = WireMock("localhost", TestConfig.getInt(wiremockPort))

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

    @Step("URL<url>にPUTリクエストが送信された")
    fun assertPutRequestExecuted(url: String) {
        client.verifyThat(putRequestedFor(urlEqualTo(url)))
    }

    @Step("URL<url>にPOSTリクエストが送信された")
    fun assertPostRequestExecuted(url: String) {
        client.verifyThat(postRequestedFor(urlEqualTo(url)))
    }

    @Step("URL<url>にDELETEリクエストが送信された")
    fun assertDeleteRequestExecuted(url: String) {
        client.verifyThat(deleteRequestedFor(urlEqualTo(url)))
    }

    @Step("URL<url>にリクエストボディ<requestBody>で、PUTリクエストが送信された")
    fun assertPutRequestExecutedWithBody(url: String, body: String) {
        client.verifyThat(putRequestedFor(urlEqualTo(url)).withRequestBody(equalTo(body)))
    }

    @Step("URL<url>にリクエストボディ<requestBody>で、POSTリクエストが送信された")
    fun assertPostRequestExecutedWithBody(url: String, body: String) {
        client.verifyThat(postRequestedFor(urlEqualTo(url)).withRequestBody(equalTo(body)))
    }
}
