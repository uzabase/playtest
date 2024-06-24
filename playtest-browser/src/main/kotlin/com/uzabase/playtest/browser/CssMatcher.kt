package com.uzabase.playtest.browser

import com.codeborne.selenide.CheckResult
import com.codeborne.selenide.Driver
import com.codeborne.selenide.WebElementCondition
import org.openqa.selenium.WebElement

fun css(propName: String, propValue: String): WebElementCondition {
    return object : WebElementCondition("css") {
        override fun check(driver: Driver, element: WebElement): CheckResult =
            CheckResult(propValue.equals(element.getCssValue(propName), ignoreCase = true), element.getCssValue(propName))
    }
}