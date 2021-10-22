package com.uzabase

import org.kodein.di.DI
import org.kodein.di.bindSingleton

object Container {
    val kodein = DI {
        bindSingleton<JsonReader> { JsonReaderImpl() }
    }
}