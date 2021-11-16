package com.uzabase.playtest.gauge.rest

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.rest.DataStore.storeJsonToScenario

class TestStep {
    @Step("シナリオデータストアに<json>を保存する")
    fun storeJson(json: String) {
        storeJsonToScenario(json)
    }
}
