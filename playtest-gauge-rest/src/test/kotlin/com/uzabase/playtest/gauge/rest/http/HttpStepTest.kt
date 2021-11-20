package com.uzabase.playtest.gauge.rest.http

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class HttpStepTest {
    @Test
    fun ヘッダー文字列からヘッダーのマップを生成する() {
        val httpStep = HttpStep()
        httpStep.toHeaderMap("content-type: application/json") shouldBeEqualTo mapOf("content-type" to "application/json")
    }
}