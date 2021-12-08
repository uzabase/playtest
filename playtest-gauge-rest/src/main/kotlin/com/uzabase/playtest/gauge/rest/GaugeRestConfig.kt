package com.uzabase.playtest.gauge.rest

import java.util.*

enum class ConfigKeys(val key: String) {
    BASE_URL("rest.baseUrl")
}

object GaugeRestConfig {
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

    fun get(key: ConfigKeys): String {
        return properties[key.key].toString()
    }
}

