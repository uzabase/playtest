package com.uzabase.playtest.gauge.browser

import com.codeborne.selenide.WebDriverProvider
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class ChromeDriverProvider: WebDriverProvider {
    override fun createDriver(p0: Capabilities): WebDriver {
        return ChromeDriver()
    }
}