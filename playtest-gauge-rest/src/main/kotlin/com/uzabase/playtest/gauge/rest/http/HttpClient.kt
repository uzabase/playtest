package com.uzabase.playtest.gauge.rest.http

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import java.io.File

typealias Url = String
typealias StatusCode = Int

typealias MilliSecond = Int

class HttpClient(
    private val autoRedirect: Boolean = false,
    private val connectTimeout: MilliSecond = 30000,
    private val readTimeout: MilliSecond = 30000,
    private val defaultHeaders: Headers = headersFrom(
        "Content-Type" to "application/json; charset=UTF-8"
    )
) {
    init {
        FuelManager.instance.timeoutInMillisecond = connectTimeout
        FuelManager.instance.timeoutReadInMillisecond = readTimeout
    }

    fun executeGet(url: Url, headers: Headers = emptyMap()) =
        url.httpGet().exec(headers)

    fun executePost(url: Url, headers: Headers = emptyMap()) =
        url.httpPost().exec(headers)

    fun executePost(url: Url, requestBody: String, headers: Headers = emptyMap()) =
        url.httpPost().exec(requestBody, headers)

    fun executePost(url: Url, requestBody: ByteArray, headers: Headers = emptyMap()) =
        url.httpPost().exec(requestBody, headers)

    fun executePut(url: Url, headers: Headers = emptyMap()) =
        url.httpPut().exec(headers)

    fun executePut(url: Url, requestBody: String, headers: Headers = emptyMap()) =
        url.httpPut().exec(requestBody, headers)

    fun executePut(url: Url, requestBody: ByteArray, headers: Headers = emptyMap()) =
        url.httpPut().exec(requestBody, headers)

    fun executeDelete(url: Url, headers: Headers = emptyMap()) =
        url.httpDelete().exec(headers)

    fun executeDelete(url: Url, requestBody: String, headers: Headers = emptyMap()) =
        url.httpDelete().exec(requestBody, headers)

    fun executeDelete(url: Url, requestBody: ByteArray, headers: Headers = emptyMap()) =
        url.httpDelete().exec(requestBody, headers)

    private fun Headers.toRequestHeader() = mapKeys { (k, _) -> k.value }

    private fun Request.exec(headers: Headers): HttpResponse {
        val (_, response, result) = this
            .header((defaultHeaders + headers).toRequestHeader())
            .authentication()
            .allowRedirects(autoRedirect)
            .response()

        return createResponse(response, result)
    }

    private fun Request.exec(requestBody: String, headers: Headers): HttpResponse {
        val (_, response, result) = this
            .body(requestBody)
            .header((defaultHeaders + headers).toRequestHeader())
            .authentication()
            .allowRedirects(autoRedirect)
            .response()

        return createResponse(response, result)
    }

    private fun Request.exec(requestBody: ByteArray, headers: Headers): HttpResponse {
        val (_, response, result) = this
            .body(requestBody)
            .header((defaultHeaders + headers).toRequestHeader())
            .authentication()
            .allowRedirects(autoRedirect)
            .response()

        return createResponse(response, result)
    }

    private fun createResponse(response: Response, result: Result<ByteArray, FuelError>): HttpResponse {
        val statusCode = response.statusCode
        val hs = response.headers.mapKeys { (k, _) -> FieldName(k) }
        val body = when (result) {
            is Result.Success -> ResponseBody(String(result.value), result.value)
            is Result.Failure -> ResponseBody(String(result.error.errorData), result.error.errorData)
        }

        return HttpResponse(statusCode, hs, body)
    }
}

data class HttpResponse(
    val statusCode: StatusCode,
    val headers: Headers,
    val body: ResponseBody
)

data class ResponseBody(val string: String, val byteArray: ByteArray) {
    fun saveStringToFile(path: String) {
        File(path).writeText(string)
    }

    fun saveByteArrayToFile(path: String) {
        File(path).writeBytes(byteArray)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResponseBody

        if (string != other.string) return false
        if (!byteArray.contentEquals(other.byteArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = string.hashCode()
        result = 31 * result + byteArray.contentHashCode()
        return result
    }
}
