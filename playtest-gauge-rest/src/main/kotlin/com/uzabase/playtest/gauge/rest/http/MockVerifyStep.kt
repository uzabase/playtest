package com.uzabase.playtest.gauge.rest.http

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.ConfigKeys
import com.uzabase.playtest.gauge.rest.GaugeRestConfig
import java.lang.RuntimeException

class MockVerifyStep {
    private val host = GaugeRestConfig.get(ConfigKeys.BASE_URL).split(":")[1].removePrefix("//")
    private val port = GaugeRestConfig.get(ConfigKeys.BASE_URL).split(":")[2].toInt()
    private val client = WireMock(host, port)

    @Step("URL<url>にボディ<jsonFilePath>JSONファイルの内容でPOSTリクエストされた")
    fun assertPostRequestExecutedWithBody(url: String, jsonFilePath: String) {

        client.verifyThat(1, postRequestedFor(urlEqualTo(url)).withRequestBody(readJsonFileToValuePattern(jsonFilePath)))
    }

    @Step("URL<url>にボディ<jsonFilePath>JSONファイルの内容、ヘッダー<header>で、POSTリクエストされた")
    fun assertPostRequestExecuted(url: String, jsonFilePath: String, header: String) {
        val headerEntity = HttpStep().toHeaderMap(header).entries.first()
        client.verifyThat(
            postRequestedFor(urlEqualTo(url)).withRequestBody(readJsonFileToValuePattern(jsonFilePath))
                .withHeader(headerEntity.key, equalTo(headerEntity.value))
        )
    }

    @Step("URL<url>にヘッダー<header>で、POSTリクエストされた")
    fun assertPostRequestExecuted(url: String, header: String){
        val headerEntity = HttpStep().toHeaderMap(header).entries.first()
        client.verifyThat(postRequestedFor(urlEqualTo(url)).withHeader(headerEntity.key, equalTo(headerEntity.value)))

    }

    private fun readJsonFileToValuePattern(jsonFilePath: String) = equalToJson(
        try {
            javaClass.getResourceAsStream(jsonFilePath).bufferedReader().readLines().joinToString("\n")
        } catch (e: Throwable) {
            throw RuntimeException("Failed to read $jsonFilePath", e)
        }
    )

}