package com.uzabase.playtest.gauge.db

import com.natpryce.konfig.*
import com.natpryce.konfig.ConfigurationProperties.Companion.systemProperties
import com.uzabase.playtest.gauge.db.ConfigKeys.*
import java.io.File

enum class ConfigKeys(val key: String) {
    DRIVER_CLASS("driverClass"),
    URL("url"),
    USER("user"),
    PASSWORD("password"),
    SCHEMA("schema")
}

data class DatabaseInfo(
    val driverClass: String,
    val url: String,
    val user: String,
    val password: String,
    val schema: String
)

object GaugeDbConfig {

    private val conf = systemProperties() overriding
            envConfig() overriding
            ConfigurationProperties.fromResource("playtest-gauge-db.default.properties")

    private fun envConfig(): Configuration {
        return System.getenv("GAUGE_DB_CONFIG")?.let {
            ConfigurationProperties.fromFile(File(it))
        } ?: EmptyConfiguration
    }

    fun getRecords(): List<String> {
        return conf.list().reversed()
            .map { it.second }
            .flatMap { it.toList() }
            .associateBy { it.first }
            .filter { it.key.contains(Regex("^db.*url$")) }
            .map { it.key }
            .map { it.split(".")[1] }
    }

    fun get(databaseName: String): DatabaseInfo {
        val driverClass = conf[Key("db.${databaseName}.${DRIVER_CLASS.key}", stringType)]
        val url = conf[Key("db.${databaseName}.${URL.key}", stringType)]
        val user = conf[Key("db.${databaseName}.${USER.key}", stringType)]
        val password = conf[Key("db.${databaseName}.${PASSWORD.key}", stringType)]
        val schema = conf[Key("db.${databaseName}.${SCHEMA.key}", stringType)]
        return DatabaseInfo(driverClass, url, user, password, schema)
    }
}
