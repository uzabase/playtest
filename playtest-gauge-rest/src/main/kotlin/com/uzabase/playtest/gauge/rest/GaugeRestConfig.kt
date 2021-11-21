package com.uzabase.playtest.gauge.rest

import java.util.*

enum class ConfigKeys(val key: String) {
    URL_PROTOCOL("rest.url.protocol"),
    URL_DOMAIN("rest.url.domain"),
    URL_PORT("rest.url.port"),
}

internal object GaugeRestConfig {
    // TODO: システムプロパティから渡せるようにする
    private val properties: Properties = javaClass.getResource("/playtest-gauge-rest.default.properties")!!
        .let { Properties().from(it.path) }
        .let { properties ->
            kotlin.runCatching { System.getenv("GAUGE_REST_CONFIG") }
                .getOrNull()?.let { properties.merge(Properties().from(it)) } ?: properties
        }
//        .let { properties ->
//            kotlin.runCatching { System.getProperty("gauge.rest.config") }
//                .getOrNull()?.let { properties.mergePropertyFile(Properties().from(it)) } ?: properties
//        }

    private fun Properties.mergePropertyFile(properties: Properties): Properties {
        properties.forEach {
            this[it.key] = it.value
        }
        return this
    }

    fun get(key: ConfigKeys): String {
        return properties[key.key].toString()
    }
}

