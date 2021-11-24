package com.uzabase.playtest.gauge.rest.http

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
}