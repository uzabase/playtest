package com.uzabase.playtest.gauge.browser

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import com.google.common.html.HtmlEscapers
import com.thoughtworks.gauge.Gauge
import org.openqa.selenium.logging.LogType

class GaugeReportWriter {
    fun write(specification: String, scenario: String) {
        "WebDriver session id: ${WebDriverRunner.driver().sessionId} ".let {
            Gauge.writeMessage(it)
        }
        Gauge.writeMessage("Page url: ${WebDriverRunner.url()}")
        Gauge.writeMessage("Page source: \n${HtmlEscapers.htmlEscaper().escape(WebDriverRunner.source())}")
        Gauge.writeMessage("Browser logs: \n${Selenide.getWebDriverLogs(LogType.BROWSER).joinToString("\n")}")
    }
}
