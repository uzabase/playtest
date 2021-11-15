package com.uzabase

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.thoughtworks.gauge.AfterScenario
import com.thoughtworks.gauge.BeforeScenario
import com.thoughtworks.gauge.Step
import com.uzabase.TestConfig.wiremockPort

class Wiremock {
    lateinit var wireMockServer: WireMockServer

    @BeforeScenario(tags = ["http-request-test"])
    fun beforeHttpRequestTestScenario() {
        val path = javaClass.getResource("/com/uzabase/playtest/http")!!.path
        val options = options()
            .port(TestConfig.getInt(wiremockPort))
            .usingFilesUnderDirectory(path)
        wireMockServer = WireMockServer(options)
        wireMockServer.start()
    }

    @AfterScenario(tags = ["http-request-test"])
    fun afterHttpRequestTestScenario() {
        wireMockServer.stop()
    }

    @Step("URL<url>にGETリクエストが送信された")
    fun assertGetRequestExecuted(url: String) {
        wireMockServer.verify(getRequestedFor(urlEqualTo(url)))
    }
}
