package com.uzabase.playtest.gauge.rest.http

import com.uzabase.playtest.gauge.rest.DataStore
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test

internal class HttpStepTest {
    @Test
    fun ヘッダー文字列からヘッダーのマップを生成する() {
        HttpStep().toHeaderMap("content-type: application/json") shouldBeEqualTo
                headersFrom("content-type" to "application/json")
    }

    @Test
    fun `ヘッダーが複数指定された文字列からヘッダーのマップを生成する`() {
        HttpStep().toHeaderMap("content-type: application/json \n options: 111,222") shouldBeEqualTo
                headersFrom(
                    "content-type" to "application/json",
                    "options" to "111,222"
                )
    }

    @Test
    fun フィールド値が複数のヘッダー文字列からヘッダーのマップを生成する() {
        HttpStep().toHeaderMap("x-header: 1 \n x-header: 2") shouldBeEqualTo
                mapOf(FieldName("x-header") to listOf("1", "2"))
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
