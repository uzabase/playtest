package com.uzabase.playtest.gauge.browser

import com.codeborne.selenide.Browsers.CHROME
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.thoughtworks.gauge.BeforeScenario
import com.thoughtworks.gauge.BeforeSuite

class SetupAndTeardown {
    @BeforeSuite()
    fun setup() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("selenide.baseUrl", "http://localhost:${TestConfig.getInt(TestConfig.wiremockPort)}")
        Configuration.browser = "com.uzabase.playtest.gauge.browser.ChromeDriverProvider"
        Configuration.headless = true
    }

    @BeforeScenario()
    fun setupScenario() {
        Selenide.open("/test")
    }
}