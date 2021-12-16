package com.uzabase.playtest.gauge.browser.extension

import java.io.File
import java.util.*

internal fun Properties.from(path: String): Properties {
    return File(path).inputStream().let { Properties().apply { this.load(it) } }
}

internal fun Properties.merge(properties: Properties): Properties {
    properties.forEach {
        this[it.key] = it.value
    }
    return this
}
