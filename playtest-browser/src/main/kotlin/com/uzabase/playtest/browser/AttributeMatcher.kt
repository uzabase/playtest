package com.uzabase.playtest.browser

import com.codeborne.selenide.CheckResult
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Driver
import org.openqa.selenium.WebElement

fun attributeStartsWith(key: String, value: String): Condition {
    return object : Condition("attributeStartsWith") {
        override fun apply(driver: Driver, element: WebElement): Boolean =
            element.getAttribute(key).startsWith(value)
    }
}

fun attributeEndsWith(key: String, value: String): Condition {
    return object : Condition("attributeEndsWith") {
        override fun apply(driver: Driver, element: WebElement): Boolean =
            element.getAttribute(key).endsWith(value)
    }
}

fun attributeContains(key: String, value: String): Condition {
    return object : Condition("attributeContains") {
        override fun apply(driver: Driver, element: WebElement): Boolean = element.getAttribute(key).contains(value)
    }
}

fun css(propName: String, propValue: String): Condition {
    return object : Condition("css") {
        override fun apply(driver: Driver, element: WebElement): Boolean =
            propValue.equals(element.getCssValue(propName), ignoreCase = true)

        override fun actualValue(driver: Driver, element: WebElement): String? = element.getCssValue(propName)
    }
}
