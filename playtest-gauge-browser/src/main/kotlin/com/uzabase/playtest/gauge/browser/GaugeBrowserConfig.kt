package com.uzabase.playtest.gauge.browser

import com.uzabase.playtest.gauge.browser.extension.from
import com.uzabase.playtest.gauge.browser.extension.merge
import java.util.*

enum class ConfigKeys(val key: String) {
    IS_DISPLAY_SCENARIO("browser.displayScenario")
}

object GaugeBrowserConfig {
    // TODO: システムプロパティから渡せるようにする
    private val properties: Properties = javaClass.getResource("/playtest-gauge-browser.default.properties")!!
        .let { Properties().from(it.path) }
        .let { properties ->
            kotlin.runCatching { System.getenv("GAUGE_BROWSER_CONFIG") }
                .getOrNull()?.let { properties.merge(Properties().from(it)) } ?: properties
        }
//        .let { properties ->
//            kotlin.runCatching { System.getProperty("gauge.rest.config") }
//                .getOrNull()?.let { properties.mergePropertyFile(Properties().from(it)) } ?: properties
//        }

    fun get(key: ConfigKeys): String {
        return properties[key.key].toString()
    }

    fun isDisplayScenario() = this.get(ConfigKeys.IS_DISPLAY_SCENARIO).toString() == "true"
}
