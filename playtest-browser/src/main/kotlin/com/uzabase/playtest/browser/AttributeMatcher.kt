package com.uzabase.playtest.browser

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
