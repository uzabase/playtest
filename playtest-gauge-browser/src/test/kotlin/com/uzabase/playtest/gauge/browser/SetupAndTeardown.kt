package com.uzabase.playtest.gauge.browser

import com.codeborne.selenide.Browsers.CHROME
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.thoughtworks.gauge.BeforeScenario
import com.thoughtworks.gauge.BeforeSuite

class SetupAndTeardown {
    @BeforeSuite()
    fun setup() {
        System.setProperty("selenide.baseUrl", "http://localhost:${TestConfig.getInt(TestConfig.wiremockPort)}")
        Configuration.browser = ChromeDriverProvider::class.java.name
        Configuration.headless = true
    }
}