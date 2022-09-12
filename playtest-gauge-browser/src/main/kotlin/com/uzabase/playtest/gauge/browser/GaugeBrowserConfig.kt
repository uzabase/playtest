package com.uzabase.playtest.gauge.browser

import com.natpryce.konfig.*
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties
import java.io.File

interface ConfigKey<T> {
    val key: String
    val type: PropertyType<T>
}


object IS_DISPLAY_SCENARIO : ConfigKey<Boolean> {
    override val key = "browser.displayScenario"
    override val type: PropertyType<Boolean> = booleanType
}

object GaugeBrowserConfig {
    private val conf = systemProperties() overriding
            envConfig() overriding
            ConfigurationProperties.fromResource("playtest-gauge-browser.default.properties")

    private fun envConfig(): Configuration {
        return System.getenv("GAUGE_BROWSER_CONFIG")?.let {
            ConfigurationProperties.fromFile(File(it))
        } ?: EmptyConfiguration
    }

    fun <T> getOrNull(key: ConfigKey<T>) = conf.getOrNull(Key(key.key, key.type))

    fun isDisplayScenario(): Boolean = this.getOrNull(IS_DISPLAY_SCENARIO) == true
}
