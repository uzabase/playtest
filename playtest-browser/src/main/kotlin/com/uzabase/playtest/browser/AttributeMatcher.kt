package com.uzabase.playtest.browser

import com.codeborne.selenide.CheckResult
import com.codeborne.selenide.Driver
import com.codeborne.selenide.WebElementCondition
import org.openqa.selenium.WebElement


fun attributeStartsWith(key: String, value: String): WebElementCondition {
    return object : WebElementCondition("attributeStartsWith") {
        override fun check(driver: Driver, element: WebElement): CheckResult =
            CheckResult(element.getAttribute(key).startsWith(value), element.getAttribute(key))
    }
}

fun attributeEndsWith(key: String, value: String): WebElementCondition {
    return object : WebElementCondition("attributeEndsWith") {
        override fun check(driver: Driver, element: WebElement): CheckResult =
            CheckResult(element.getAttribute(key).endsWith(value), element.getAttribute(key))
    }
}

fun attributeContains(key: String, value: String): WebElementCondition {
    return object : WebElementCondition("attributeContains") {
        override fun check(driver: Driver, element: WebElement): CheckResult =
            CheckResult(element.getAttribute(key).contains(value), element.getAttribute(key))
    }
}
