package com.uzabase

import com.thoughtworks.gauge.Step
import org.amshove.kluent.`should be equal to`

class ExampleStep {
    @Step("テスト")
    fun test() {
        "a" `should be equal to` "b"
    }
}