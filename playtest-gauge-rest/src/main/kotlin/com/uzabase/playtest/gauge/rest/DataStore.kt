package com.uzabase.playtest.gauge.rest

import com.thoughtworks.gauge.datastore.ScenarioDataStore
import com.uzabase.playtest.gauge.rest.http.Headers
import com.uzabase.playtest.gauge.rest.http.ResponseBody
import com.uzabase.playtest.gauge.rest.http.StatusCode

object DataStore {
    private const val statusCodeKey = "statusCode"
    private const val responseHeadersKey = "responseHeaders"
    private const val responseBodyKey = "responseBody"

    fun storeToScenario(key: String, value: Any) {
        ScenarioDataStore.put(key, value)
    }

    fun <T> loadFromScenario(key: String): T = ScenarioDataStore.get(key) as T

    fun storeResponseData(
        statusCode: StatusCode,
        headers: Headers,
        body: ResponseBody
    ) {
        storeStatusCodeToScenario(statusCode)
        storeResponseHeadersToScenario(headers)
        storeResponseBodyToScenario(body)
    }

    fun storeStatusCodeToScenario(statusCode: Int) {
        storeToScenario(statusCodeKey, statusCode)
    }

    fun loadStatusCodeFromScenario(): Int = loadFromScenario(statusCodeKey)

    fun storeResponseHeadersToScenario(headers: Headers) {
        storeToScenario(responseHeadersKey, headers)
    }

    fun loadResponseHeadersFromScenario(): Headers = loadFromScenario(responseHeadersKey)

    fun storeResponseBodyToScenario(body: ResponseBody) {
        storeToScenario(responseBodyKey, body)
    }

    fun loadResponseBodyFromScenario(): ResponseBody = loadFromScenario(responseBodyKey)
}
