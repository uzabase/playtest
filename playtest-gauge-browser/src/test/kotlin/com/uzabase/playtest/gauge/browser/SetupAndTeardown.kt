package com.uzabase.playtest.gauge.browser

import com.codeborne.selenide.Selenide
import com.thoughtworks.gauge.BeforeScenario
import com.thoughtworks.gauge.BeforeSuite
import io.github.bonigarcia.wdm.WebDriverManager

class SetupAndTeardown {
    @BeforeSuite()
    fun setup() {
        System.setProperty("selenide.baseUrl", "http://localhost:${TestConfig.getInt(TestConfig.wiremockPort)}")
        WebDriverManager.chromedriver().setup();
    }

    @BeforeScenario()
    fun setupScenario() {
        Selenide.open("/test")
    }
}