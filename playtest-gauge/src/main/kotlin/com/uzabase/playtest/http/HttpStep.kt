package com.uzabase.playtest.http

import com.thoughtworks.gauge.Step
import com.thoughtworks.gauge.datastore.ScenarioDataStore
import com.uzabase.playtest.DataKey
import org.amshove.kluent.shouldBe

class HttpStep {
    @Step("ステータスコード<statusCode>が返る")
    fun assertStatusCodeEquals(statusCode: Int) {
        statusCode shouldBe ScenarioDataStore.get(DataKey.statusCode)
    }
}
