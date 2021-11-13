package com.uzabase

import com.thoughtworks.gauge.Step
import com.uzabase.playtest.DataStore.storeJsonToScenario

class TestStep {
    @Step("シナリオデータストアに<json>を保存する")
    fun storeJson(json: String) {
        storeJsonToScenario(json)
    }
}
