package com.uzabase

import org.amshove.kluent.AnyException
import org.amshove.kluent.internal.ComparisonFailedException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class JsonAssertTest : JsonAssert {
    @Test
    fun 指定したJSONPathの値が期待した文字列の場合アサートの例外をスローしない() {
        val json = """{ "test": "test" }"""
        invoking { json.assertByJsonPath("test", "$.test") } shouldNotThrow AnyException
    }

    @Test
    fun 指定したJSONPathの値が期待した文字列の場合アサートの例外をスローする() {
        val json = """{ "test": "test" }"""
        assertThrows<ComparisonFailedException> { json.assertByJsonPath("xxxx", "$.test") }
    }

    @Test
    fun 指定したJSONPathの値が期待した真偽値の場合アサートの例外をスローしない() {
        val json = """{ "test": true }"""
        invoking { json.assertByJsonPath(true, "$.test") } shouldNotThrow AnyException
    }

    @Test
    fun 指定したJSONPathの値が期待していない真偽値の場合アサートの例外をスローする() {
        val json = """{ "test": false }"""
        assertThrows<ComparisonFailedException> { json.assertByJsonPath(true, "$.test") }
    }
}