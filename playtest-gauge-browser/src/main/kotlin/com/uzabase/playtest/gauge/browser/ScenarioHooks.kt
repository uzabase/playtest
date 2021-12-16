package com.uzabase.playtest.gauge.browser

import com.codeborne.selenide.Selenide
import com.thoughtworks.gauge.AfterScenario
import com.thoughtworks.gauge.BeforeScenario
import com.thoughtworks.gauge.ExecutionContext
import java.util.concurrent.TimeUnit

class ScenarioHooks {
    private val scenarioDisplayer = ScenarioDisplayer()
    private val reportWriter = GaugeReportWriter()

    @BeforeScenario
    fun beforeScenario(context: ExecutionContext) {
        if (GaugeBrowserConfig.isDisplayScenario()) {
            displayScenario(context.currentSpecification.name, context.currentScenario.name)
        }
    }

    @AfterScenario
    fun afterScenario(context: ExecutionContext) {
        reportWriter.write(context.currentSpecification.name, context.currentScenario.name)
    }

    private fun displayScenario(specification: String, scenario: String) {
        scenarioDisplayer.display(specification, scenario)
        TimeUnit.MILLISECONDS.sleep(500)
        Selenide.back()
    }
}