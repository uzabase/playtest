package com.uzabase.playtest.gauge.rest.http

import com.thoughtworks.gauge.Step

class MockVerifyRephraseStep {
    private val step = MockVerifyStep()

    @Step("API<apiName>のURL<url>にGETリクエストされた")
    fun assertCallingOfGetRequest(apiName: String, url: String) {
        step.assertCallCountOfGetRequest(apiName, url, 1)
    }

    @Step("API<apiName>のURL<url>にGETリクエストされていない")
    fun assetNotCallingOfGetRequest(apiName: String, url: String) {
        step.assertCallCountOfGetRequest(apiName, url, 0)
    }

    @Step("API<apiName>のURL<url>にPOSTリクエストされた")
    fun assertCallingOfPostRequest(apiName: String, url: String) {
        step.assertCallCountOfPostRequest(apiName, url, 1)
    }

    @Step("API<apiName>のURL<url>にPOSTリクエストされていない")
    fun assertNotCallingOfPostRequest(apiName: String, url: String) {
        step.assertCallCountOfPostRequest(apiName, url, 0)
    }

    @Step("API<apiName>のURL<url>にPUTリクエストされた")
    fun assertCallingOfPutRequest(apiName: String, url: String) {
        step.assertCallCountOfPutRequest(apiName, url, 1)
    }

    @Step("API<apiName>のURL<url>にPUTリクエストされていない")
    fun assertNotCallingOfPutRequest(apiName: String, url: String) {
        step.assertCallCountOfPutRequest(apiName, url, 0)
    }

    @Step("API<apiName>のURL<url>にDELETEリクエストされた")
    fun assertCallingOfDeleteRequest(apiName: String, url: String) {
        step.assertCallCountOfDeleteRequest(apiName, url, 1)
    }

    @Step("API<apiName>のURL<url>にDELETEリクエストされていない")
    fun assertNotCallingOfDeleteRequest(apiName: String, url: String) {
        step.assertCallCountOfDeleteRequest(apiName, url, 0)
    }

}