package com.uzabase.playtest.gauge.browser

import com.codeborne.selenide.WebDriverProvider
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class ChromeDriverProvider : WebDriverProvider {
    override fun createDriver(p0: Capabilities): WebDriver {
        val options = ChromeOptions()
        // for Chrome 111 issue
        options.addArguments("--remote-allow-origins=*")
        return ChromeDriver(options)
    }
}