package com.uzabase.playtest.gauge.browser

import java.io.BufferedInputStream
import java.util.*

object TestConfig {
    const val wiremockPort = "wiremock.port"

    private val properties: Properties = javaClass
        .getResourceAsStream("/e2e.properties")!!
        .use { it.let(::BufferedInputStream).let { Properties().apply { this.load(it) } } }

    fun getString(key: String): String {
        return properties[key].toString()
    }

    fun getInt(key: String): Int {
        return properties[key].toString().toInt()
    }
}
