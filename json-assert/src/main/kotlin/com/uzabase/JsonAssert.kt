package com.uzabase

import org.amshove.kluent.shouldBeEqualTo
import org.kodein.di.instance

interface JsonAssert {
    private companion object {
        private val reader: JsonReader by Container.kodein.instance()
    }

    fun String.assertByJsonPath(expected: String, jsonPath: String) {
        val value = reader.getByJsonPath(this, jsonPath)
        expected shouldBeEqualTo value
    }
}
