package com.uzabase

import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

internal class JsonReaderImplTest {
    @Test
    fun JSONPathで指定したKeyの値が存在しないとき例外をスローする() {
        invoking {
            val jsonString = """
                {"key1": "value1"}
            """.trimIndent()

            val jsonNode = JsonNode.of(jsonString)
            jsonNode.get("$.key999")
        } shouldThrow NotFoundJsonValueException::class
    }

    @Test
    fun JSONPathで指定したKeyの文字列を取得する() {
        val jsonString = """
            {"key1": "value1"}
        """.trimIndent()

        val jsonNode = JsonNode.of(jsonString)
        "value1" shouldBeEqualTo jsonNode.get<String>("$.key1")
    }

    @Test
    fun JSONPathで指定したKeyの真偽値を取得する() {
        val jsonString = """
            {"key1": true }
        """.trimIndent()

        val jsonNode = JsonNode.of(jsonString)
        true shouldBeEqualTo jsonNode.get<Boolean>("$.key1")
    }

    @Test
    fun JSONPathで指定したKeyの整数値を取得する() {
        val jsonString = """
            {"key1": 1}
        """.trimIndent()

        val jsonNode = JsonNode.of(jsonString)
        1 shouldBeEqualTo jsonNode.get<Int>("$.key1")
    }

    @Test
    fun JSONPathで指定したKeyの小数値を取得する() {
        val jsonString = """
            {"key1": 1.2}
        """.trimIndent()

        val jsonNode = JsonNode.of(jsonString)
        1.2 shouldBeEqualTo jsonNode.get<Double>("$.key1")
    }
}