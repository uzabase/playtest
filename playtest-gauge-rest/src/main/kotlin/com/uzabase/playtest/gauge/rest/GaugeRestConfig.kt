package com.uzabase.playtest.gauge.rest

import com.uzabase.playtest.gauge.rest.extension.from
import com.uzabase.playtest.gauge.rest.extension.merge
import javassist.NotFoundException
import java.util.*

enum class ConfigKeys(val key: String) {
    BASE_URL("baseUrl")
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
        return properties["rest.${key.key}"].toString()
    }

    fun get(apiName: String, key: ConfigKeys): String {
        val propertyKey = "rest.${apiName}.${key.key}"
        if (properties[propertyKey] == null){
            throw NotFoundException("$propertyKey is not found in properties.")
        }
        return properties[propertyKey].toString()
    }
}

