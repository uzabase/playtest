package com.uzabase
import org.amshove.kluent.AnyException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldNotThrow
import org.junit.jupiter.api.Test

internal class JsonAssertTest : JsonAssert {
    @Test
    fun JSONPathを指定して文字列をアサーションできる() {
        val json = """{ "test": "test" }"""
        invoking { json.assertByJsonPath("test", "$.test") } shouldNotThrow AnyException
    }
}