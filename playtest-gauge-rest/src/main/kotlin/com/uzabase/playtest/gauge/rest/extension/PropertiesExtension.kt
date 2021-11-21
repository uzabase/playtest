package com.uzabase.playtest.gauge.rest

import java.io.File
import java.util.*

fun Properties.from(path: String): Properties {
    return File(path).inputStream().let { Properties().apply { this.load(it) } }
}

fun Properties.merge(properties: Properties): Properties {
    properties.forEach {
        this[it.key] = it.value
    }
    return this
}
