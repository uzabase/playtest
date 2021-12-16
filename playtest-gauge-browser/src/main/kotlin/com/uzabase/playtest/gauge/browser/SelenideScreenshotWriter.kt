package com.uzabase.playtest.gauge.browser

import com.codeborne.selenide.Selenide
import com.thoughtworks.gauge.screenshot.CustomScreenshotWriter
import org.openqa.selenium.OutputType
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class SelenideScreenshotWriter : CustomScreenshotWriter {
    override fun takeScreenshot(): String {
        val fileName = "screenshot-${UUID.randomUUID()}.png"
        Paths.get(System.getenv("gauge_screenshots_dir"), fileName)
            .let(Files::newOutputStream).use { Selenide.screenshot(OutputType.BYTES)?.let(it::write) }
        return fileName
    }
}