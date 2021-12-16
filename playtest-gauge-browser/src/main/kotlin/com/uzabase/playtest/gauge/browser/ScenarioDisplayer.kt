package com.uzabase.playtest.gauge.browser

import com.codeborne.selenide.Selenide
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class ScenarioDisplayer {
    fun display(specification: String, scenario: String) {
        """
                <html>
                <head>
                <meta charset="utf-8"/>
                </head>
                <h2>$specification<h2>
                <h1>$scenario</h1>
                </html>
            """.trimIndent().replace("\n", "")
            .let { URLEncoder.encode(it, StandardCharsets.UTF_8.name()).replace("+", "%20") }
            .let { "data:text/html,$it" }
            .let { Selenide.open(it) }
    }
}