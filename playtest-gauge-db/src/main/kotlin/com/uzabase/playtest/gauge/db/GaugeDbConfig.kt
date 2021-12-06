package com.uzabase.playtest.gauge.db

import from
import javassist.NotFoundException
import merge
import java.util.*

enum class ConfigKeys(val key: String) {
    DRIVER_CLASS("driverClass"),
    URL("url"),
    USER("user"),
    PASSWORD("password"),
    SCHEMA("schema"),
    RECORDS("records")
}

data class DatabaseInfo(
    val driverClass: String,
    val url: String,
    val user: String,
    val password: String,
    val schema: String
)

internal object GaugeDbConfig {
    // TODO: システムプロパティから渡せるようにする
    private val properties: Properties = javaClass.getResource("/playtest-gauge-db.default.properties")!!
        .let { Properties().from(it.path) }
        .let { properties ->
            kotlin.runCatching { System.getenv("GAUGE_DB_CONFIG") }
                .getOrNull()?.let { properties.merge(Properties().from(it)) } ?: properties
        }
//        .let { properties ->
//            kotlin.runCatching { System.getProperty("gauge.rest.config") }
//                .getOrNull()?.let { properties.mergePropertyFile(Properties().from(it)) } ?: properties
//        }

    fun get(databaseName: String, key: ConfigKeys): String {
        val propertyKey = "db.$databaseName.${key.key}"
        if (properties[propertyKey] == null) {
            throw NotFoundException("$propertyKey is not found in properties.")
        }
        return properties[propertyKey].toString()
    }

    fun get(databaseName: String): DatabaseInfo = DatabaseInfo(
        get(databaseName, ConfigKeys.DRIVER_CLASS),
        get(databaseName, ConfigKeys.URL),
        get(databaseName, ConfigKeys.USER),
        get(databaseName, ConfigKeys.PASSWORD),
        get(databaseName, ConfigKeys.SCHEMA)
    )

    fun getRecords(): List<String> {
        val propertyKey = "db.${ConfigKeys.RECORDS.key}"
        if (properties[propertyKey] == null) {
            return listOf()
        }
        return properties[propertyKey].toString().split(",").map { it.trim() }
    }
}
