package com.uzabase.playtest.web

import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL

typealias Url = String
typealias StatusCode = Int
typealias Headers = Map<HeaderKey, HeaderValue>
typealias HeaderKey = String
typealias HeaderValue = String

typealias MilliSecond = Int

class HttpClient(
    private val autoRedirect: Boolean = false,
    private val connectTimeout: MilliSecond = 30000,
    private val readTimeout: MilliSecond = 30000,
    private val defaultHeaders: Headers = mapOf(
        "Content-Type" to "application/json; charset=UTF-8"
    )
) {
    fun executeGet(url: Url, headers: Headers = emptyMap()) = execute("GET", url, headers)

    fun executePost(url: Url, headers: Headers = emptyMap(), requestBody: String?) =
        execute("POST", url, headers, requestBody?.toByteArray())

    fun executePost(url: Url, headers: Headers = emptyMap(), requestBody: ByteArray?) =
        execute("POST", url, headers, requestBody)

    fun executePut(url: Url, headers: Headers = emptyMap(), requestBody: String?) =
        execute("PUT", url, headers, requestBody?.toByteArray())

    fun executePut(url: Url, headers: Headers = emptyMap(), requestBody: ByteArray?) =
        execute("PUT", url, headers, requestBody)

    fun executeDelete(url: Url, headers: Headers = emptyMap(), requestBody: String?) =
        execute("DELETE", url, headers, requestBody?.toByteArray())

    fun executeDelete(url: Url, headers: Headers = emptyMap(), requestBody: ByteArray?) =
        execute("DELETE", url, headers, requestBody)

    private fun execute(method: String, url: Url, headers: Headers, body: ByteArray? = null): HttpResponse {
        var c: HttpURLConnection? = null
        try {
            c = URL(url).openConnection() as HttpURLConnection
            setValues(c, method, headers, body)

            c.connect()

            val statusCode = c.responseCode

            val responseHeaders = c.headerFields
                // "HTTP/1.1 200 OK" のようなステータスコードの値に対するキーが null であり取り回しづらいので除外している
                .filterKeys { it != null }
                // HTTP レスポンスヘッダは1つのキーを複数回含むこともあるらしい
                // ", " で区切った1つの文字列にしても、キーを複数回含んでも、意味合いは同じらしいので join しておく
                // https://stackoverflow.com/questions/40328504/what-is-the-point-of-urlconnection-getheaderfields-returning-a-mapstring-list
                // また、it.value (List<String>) は想定の逆順になっているので reversed() が要る
                .mapValues { it.value.reversed().joinToString(", ") }
            val responseBody = getResponseBody(statusCode, c)
            return HttpResponse(statusCode, responseHeaders, responseBody)
        } catch (t: Throwable) {
            throw t
        } finally {
            c?.disconnect()
        }
    }

    private fun setValues(c: HttpURLConnection, method: String, headers: Headers, body: ByteArray?) {
        c.requestMethod = method
        c.instanceFollowRedirects = this.autoRedirect
        c.connectTimeout = this.connectTimeout
        c.readTimeout = this.readTimeout

        if (body != null) c.doOutput = true

        (headers + defaultHeaders).forEach { c.addRequestProperty(it.key, it.value) }
        body?.let {
            val out = c.outputStream
            out.write(it)
            out.flush()
            out.close()
        }
    }

    private fun getResponseBody(statusCode: StatusCode, c: HttpURLConnection): ResponseBody {
        val stream = ByteArrayOutputStream()

        if (statusCode < 400) c.inputStream.transferTo(stream)
        else c.errorStream.transferTo(stream)

        return ResponseBody(stream.toString(), stream.toByteArray())
    }
}

data class HttpResponse(
    val statusCode: StatusCode,
    val headers: Headers,
    val body: ResponseBody
)

class ResponseBody(private val string: String, private val byteArray: ByteArray) {
    fun string() = string
    fun byteArray() = byteArray
}
