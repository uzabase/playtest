package com.uzabase.playtest.gauge.browser

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.thoughtworks.gauge.AfterSuite
import com.thoughtworks.gauge.BeforeSuite
import com.uzabase.playtest.gauge.browser.TestConfig.wiremockPort

class Wiremock {
    private val path: String = javaClass.getResource("/com/uzabase/playtest/gauge/browser")!!.path
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
}
