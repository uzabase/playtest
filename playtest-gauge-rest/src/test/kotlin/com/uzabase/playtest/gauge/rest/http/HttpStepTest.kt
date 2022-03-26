package com.uzabase.playtest.gauge.rest.http

import com.uzabase.playtest.gauge.rest.DataStore
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class HttpStepTest {
    @Test
    fun ヘッダー文字列からヘッダーのマップを生成する() {
        val httpStep = HttpStep()
        httpStep.toHeaderMap("content-type: application/json") shouldBeEqualTo mapOf("content-type" to "application/json")
    }

    @Test
    fun ヘッダーが複数指定された文字列からヘッダーのマップを生成する() {
        val httpStep = HttpStep()
        val expected = mapOf(
            "content-type" to "application/json",
            "options" to "111,222"
        )
        val headerString = "content-type: application/json r\n options: 111,222" // gaugeのステップは文字列で受け取ると、最初の\が消える
        httpStep.toHeaderMap(headerString) shouldBeEqualTo expected
    }

    @Test
    fun レスポンスボディの文字列が渡された文字列と一致する() {
        val httpStep = HttpStep()
        val responseString = "Hello world!"
        mockkObject(DataStore)
        every { DataStore.loadResponseBodyFromScenario() } returns ResponseBody(responseString, responseString.toByteArray())

        httpStep.assertResponseBodyStringEquals(responseString)

        verify { DataStore.loadResponseBodyFromScenario()}
    }
}