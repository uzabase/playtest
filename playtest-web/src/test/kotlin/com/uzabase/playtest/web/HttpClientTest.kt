package com.uzabase.playtest.web

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.http.HttpHeader
import com.github.tomakehurst.wiremock.http.HttpHeaders
import com.github.tomakehurst.wiremock.http.HttpHeaders.noHeaders
import com.github.tomakehurst.wiremock.matching.BinaryEqualToPattern
import com.github.tomakehurst.wiremock.matching.EqualToPattern
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

internal class HttpClientTest {
    private val url = "http://localhost:9090"

    private val wiremockDefaultHeaderKeys = setOf("Transfer-Encoding", "Matched-Stub-Id", "Server")

    private lateinit var wiremock: WireMockServer

    @BeforeEach
    fun beforeEach() {
        wiremock = WireMockServer(9090)
        wiremock.start()
    }

    @AfterEach
    fun afterEach() {
        wiremock.resetAll()
        wiremock.stop()
    }

    @Nested
    inner class StatusCodeTest {
        @Test
        fun ステータスコード200を得られる() {
            wiremock.stubFor(get("/get").willReturn(ok()))

            val actual = HttpClient().executeGet("$url/get")

            assertEquals(200, actual.statusCode)

            wiremock.verify(getRequestedFor(urlPathEqualTo("/get")))
        }

        @Test
        fun ステータスコード301を得られる_自動リダイレクトOFFのとき() {
            val headers = HttpHeaders(HttpHeader("Location", "$url/get2"))
            wiremock.stubFor(get("/get").willReturn(permanentRedirect("/get2").withHeaders(headers)))
            wiremock.stubFor(get("/get2").willReturn(serverError()))

            val actual = HttpClient().executeGet("$url/get")

            assertEquals(301, actual.statusCode)
            assertEquals("$url/get2", actual.headers["Location"])

            wiremock.verify(1, getRequestedFor(urlPathEqualTo("/get")))
            wiremock.verify(0, getRequestedFor(urlPathEqualTo("/get2")))
        }

        @Test
        fun ステータスコード200を得られる_自動リダイレクトONのとき() {
            val headers = HttpHeaders(HttpHeader("Location", "$url/get2"))
            wiremock.stubFor(get("/get").willReturn(permanentRedirect("/get2").withHeaders(headers)))
            wiremock.stubFor(get("/get2").willReturn(ok()))

            val actual = HttpClient(autoRedirect = true).executeGet("$url/get")

            assertEquals(200, actual.statusCode)

            wiremock.verify(1, getRequestedFor(urlPathEqualTo("/get")))
            wiremock.verify(1, getRequestedFor(urlPathEqualTo("/get2")))
        }

        @Test
        fun ステータスコード400を得られる() {
            wiremock.stubFor(get("/get").willReturn(badRequest()))

            val actual = HttpClient().executeGet("$url/get")

            assertEquals(400, actual.statusCode)

            wiremock.verify(getRequestedFor(urlPathEqualTo("/get")))
        }

        @Test
        fun ステータスコード500を得られる() {
            wiremock.stubFor(get("/get").willReturn(serverError()))

            val actual = HttpClient().executeGet("$url/get")

            assertEquals(500, actual.statusCode)

            wiremock.verify(getRequestedFor(urlPathEqualTo("/get")))
        }
    }

    @Nested
    inner class RequestHeaderTest {
        @Test
        fun リクエストヘッダー無指定の場合はデフォルトのヘッダーが含まれる() {
            val key = "Content-Type"
            val value = "application/json; charset=UTF-8"
            wiremock.stubFor(get("/get").willReturn(ok()))

            val actual = HttpClient().executeGet("$url/get")

            wiremock.verify(getRequestedFor(urlPathEqualTo("/get")).withHeader(key, EqualToPattern(value)))
        }

        @Test
        fun リクエストヘッダーをリクエスト実行直前に追加できる_デフォルトのヘッダーは残る() {
            val key1 = "x-example-header"
            val value1 = "with-header"
            val defaultKey = "Content-Type"
            val defaultValue = "application/json; charset=UTF-8"
            wiremock.stubFor(get("/get").willReturn(ok()))

            val actual = HttpClient().executeGet("$url/get", mapOf(key1 to value1))

            wiremock.verify(
                getRequestedFor(urlPathEqualTo("/get"))
                    .withHeader(key1, EqualToPattern(value1))
                    .withHeader(defaultKey, EqualToPattern(defaultValue))
            )
        }

        @Test
        fun コンストラクタを使うとデフォルトのヘッダーをすべて上書きできる() {
            val key1 = "x-example-header"
            val value1 = "with-header"
            val defaultHeaders = mapOf(key1 to value1)
            wiremock.stubFor(get("/get").willReturn(ok()))

            val actual = HttpClient(defaultHeaders = defaultHeaders).executeGet("$url/get")

            wiremock.verify(
                getRequestedFor(urlPathEqualTo("/get"))
                    .withHeader(key1, EqualToPattern(value1))
                    .withoutHeader("Content-Type")
            )
        }
    }

    @Nested
    inner class ResponseHeaderTest {
        @Test
        fun レスポンスヘッダーなし() {
            wiremock.stubFor(get("/get").willReturn(ok().withHeaders(noHeaders())))

            val actual = HttpClient().executeGet("$url/get")

            // ok().withHeaders(noHeaders()) としてもデフォで WireMock が返すヘッダーがあるので、それのみであることを確認している
            assertEquals(wiremockDefaultHeaderKeys, actual.headers.keys)

            wiremock.verify(getRequestedFor(urlPathEqualTo("/get")))
        }

        @Test
        fun レスポンスヘッダーあり() {
            val key = "x-example-header"
            val value = "example-value"
            val headers = HttpHeaders(HttpHeader(key, value))
            wiremock.stubFor(get("/get").willReturn(ok().withHeaders(headers)))

            val actual = HttpClient().executeGet("$url/get")

            assertContains(actual.headers, key)
            assertEquals(value, actual.headers[key])

            wiremock.verify(getRequestedFor(urlPathEqualTo("/get")))
        }

        @Test
        fun レスポンスヘッダーあり_同じキーが複数存在する場合は出現順にカンマで結合する() {
            val headers = HttpHeaders(
                HttpHeader("x-example-header", "v1"),
                HttpHeader("x-example-header", "v2")
            )
            wiremock.stubFor(get("/get").willReturn(ok().withHeaders(headers)))

            val actual = HttpClient().executeGet("$url/get")

            assertContains(actual.headers, key = "x-example-header")
            assertEquals("v1, v2", actual.headers["x-example-header"])

            wiremock.verify(getRequestedFor(urlPathEqualTo("/get")))
        }
    }

    @Nested
    inner class ResponseBodyTest {
        @Test
        fun レスポンスボディなし() {
            wiremock.stubFor(get("/get").willReturn(ok()))

            val actual = HttpClient().executeGet("$url/get")

            assertEquals("", actual.body.string())
            assertContentEquals("".toByteArray(), actual.body.byteArray())

            wiremock.verify(getRequestedFor(urlPathEqualTo("/get")))
        }

        @Test
        fun レスポンスボディがJSON文字列() {
            val body = """{ "greeting": "Hello!" }"""
            wiremock.stubFor(get("/get").willReturn(ok().withBody(body)))

            val actual = HttpClient().executeGet("$url/get")

            assertEquals(body, actual.body.string())
            assertContentEquals(body.toByteArray(), actual.body.byteArray())

            wiremock.verify(getRequestedFor(urlPathEqualTo("/get")))
        }

        @Test
        fun レスポンスボディがpng画像() {
            val body = javaClass.getResourceAsStream("/kotlin_icon.png")!!.readBytes()
            wiremock.stubFor(get("/get").willReturn(ok().withBody(body)))

            val actual = HttpClient().executeGet("$url/get")

            assertContentEquals(body, actual.body.byteArray())

            wiremock.verify(getRequestedFor(urlPathEqualTo("/get")))
        }
    }

    @Nested
    inner class PostTest {
        @Test
        fun executePost_リクエストボディが空文字() {
            wiremock.stubFor(post("/post").willReturn(ok()))

            val actual = HttpClient().executePost("$url/post", requestBody = "")

            wiremock.verify(
                postRequestedFor(urlPathEqualTo("/post"))
                    .withRequestBody(absent())
            )
        }

        @Test
        fun executePost_リクエストボディがJSON文字列() {
            wiremock.stubFor(post("/post").willReturn(ok()))
            val requestBody = """{ "greeting": "Hello!" }"""

            val actual = HttpClient().executePost("$url/post", requestBody = requestBody)

            wiremock.verify(
                postRequestedFor(urlPathEqualTo("/post"))
                    .withRequestBody(EqualToPattern(requestBody))
            )
        }

        @Test
        fun executePost_リクエストボディがpng画像() {
            wiremock.stubFor(post("/post").willReturn(ok()))
            val requestBody = javaClass.getResourceAsStream("/kotlin_icon.png")!!.readBytes()

            val actual = HttpClient().executePost("$url/post", requestBody = requestBody)

            wiremock.verify(
                postRequestedFor(urlPathEqualTo("/post"))
                    .withRequestBody(BinaryEqualToPattern(requestBody))
            )
        }
    }

    @Nested
    inner class PutTest {
        @Test
        fun executePut_リクエストボディが空文字() {
            wiremock.stubFor(put("/put").willReturn(ok()))

            val actual = HttpClient().executePut("$url/put", requestBody = "")

            wiremock.verify(
                putRequestedFor(urlPathEqualTo("/put"))
                    .withRequestBody(absent())
            )
        }

        @Test
        fun executePut_リクエストボディがJSON文字列() {
            wiremock.stubFor(put("/put").willReturn(ok()))
            val requestBody = """{ "greeting": "Hello!" }"""

            val actual = HttpClient().executePut("$url/put", requestBody = requestBody)

            wiremock.verify(
                putRequestedFor(urlPathEqualTo("/put"))
                    .withRequestBody(EqualToPattern(requestBody))
            )
        }

        @Test
        fun executePut_リクエストボディがpng画像() {
            wiremock.stubFor(put("/put").willReturn(ok()))
            val requestBody = javaClass.getResourceAsStream("/kotlin_icon.png")!!.readBytes()

            val actual = HttpClient().executePut("$url/put", requestBody = requestBody)

            wiremock.verify(
                putRequestedFor(urlPathEqualTo("/put"))
                    .withRequestBody(BinaryEqualToPattern(requestBody))
            )
        }
    }

    @Nested
    inner class DeleteTest {
        @Test
        fun executeDelete_リクエストボディが空文字() {
            wiremock.stubFor(delete("/delete").willReturn(ok()))

            val actual = HttpClient().executeDelete("$url/delete", requestBody = "")

            wiremock.verify(
                deleteRequestedFor(urlPathEqualTo("/delete"))
                    .withRequestBody(absent())
            )
        }

        @Test
        fun executeDelete_リクエストボディがJSON文字列() {
            wiremock.stubFor(delete("/delete").willReturn(ok()))
            val requestBody = """{ "greeting": "Hello!" }"""

            val actual = HttpClient().executeDelete("$url/delete", requestBody = requestBody)

            wiremock.verify(
                deleteRequestedFor(urlPathEqualTo("/delete"))
                    .withRequestBody(EqualToPattern(requestBody))
            )
        }

        @Test
        fun executeDelete_リクエストボディがpng画像() {
            wiremock.stubFor(delete("/delete").willReturn(ok()))
            val requestBody = javaClass.getResourceAsStream("/kotlin_icon.png")!!.readBytes()

            val actual = HttpClient().executeDelete("$url/delete", requestBody = requestBody)

            wiremock.verify(
                deleteRequestedFor(urlPathEqualTo("/delete"))
                    .withRequestBody(BinaryEqualToPattern(requestBody))
            )
        }
    }
}
