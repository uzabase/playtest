package com.uzabase.playtest.browser

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Driver
import org.openqa.selenium.WebElement

fun css(propName: String, propValue: String): Condition {
    return object : Condition("css") {
        override fun apply(driver: Driver, element: WebElement): Boolean =
            propValue.equals(element.getCssValue(propName), ignoreCase = true)

        override fun actualValue(driver: Driver, element: WebElement): String? = element.getCssValue(propName)
    }
}