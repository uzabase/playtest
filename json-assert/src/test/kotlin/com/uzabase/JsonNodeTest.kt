package com.uzabase

import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

internal class JsonNodeTest {
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
    fun JSONPathで指定したKeyで取得する値の型変換が失敗したら例外をスローする() {
        invoking {
            val jsonString = """
                {"key1": "value1"}
            """.trimIndent()

            val jsonNode = JsonNode.of(jsonString)
            jsonNode.get<Int>("$.key1")
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

    @Test
    fun JSONPathで指定したKeyの配列を特定のkeyと値でフィルターしその配列を返す() {
        val jsonString = """
            {
              "key1": [
                { "key2": "a", "key3": "1" },
                { "key2": "b", "key3": "2" },
                { "key2": "b", "key3": "3" },
                { "key2": "d", "key3": "4" }
              ]
            }
        """.trimIndent()

        val jsonNode = JsonNode.of(jsonString)
        val expected = listOf(
            mapOf("key2" to "b", "key3" to "2"),
            mapOf("key2" to "b", "key3" to "3")
        )
        jsonNode.getFilteredList("$.key1", "key2", "b") shouldBeEqualTo expected
    }

    @Test
    fun JSONPathで指定したKeyの配列を存在しないkeyでフィルターすると空の配列を返す() {
        val jsonString = """
            {
              "key1": [
                { "key2": "a", "key3": "1" },
                { "key2": "b", "key3": "2" },
                { "key2": "b", "key3": "3" },
                { "key2": "d", "key3": "4" }
              ]
            }
        """.trimIndent()

        val jsonNode = JsonNode.of(jsonString)
        jsonNode.getFilteredList("$.key1", "xxx", "b") shouldBeEqualTo listOf()
    }
}