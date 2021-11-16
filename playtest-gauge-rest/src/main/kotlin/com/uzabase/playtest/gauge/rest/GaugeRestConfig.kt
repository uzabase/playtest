package com.uzabase.playtest.gauge.rest

import java.io.BufferedInputStream
import java.io.File
import java.util.*

enum class ConfigKeys(val key: String) {
    URL_PROTOCOL("rest.url.protocol"),
    URL_DOMAIN("rest.url.domain"),
    URL_PORT("rest.url.port"),
}

internal object GaugeRestConfig {
    // TODO: .properties の絶対パスを受け取って、それを代わりに読み込めるようにする
    // TODO: playtest-gauge-rest 外のリソースも取れるようにする
    private val properties: Properties = File(getPropertiesFilePath())
        .inputStream()
        .use { it.let(::BufferedInputStream).let { Properties().apply { this.load(it) } } }

    private fun getPropertiesFilePath(): String {
        val first = "hoge"
        val second = "env/playtest/rest.properties"
        val third = javaClass.getResource("/playtest-gauge-rest.default.properties")!!.path
        if (File(first).exists()) return first
        if (File(second).exists()) return second
        return third
    }

    fun get(key: ConfigKeys): String {
        return properties[key.key].toString()
    }
}
