package com.uzabase.playtest.json

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class JsonNodeTest {
    @Test
    fun JSONPathで指定したKeyの値が存在しないときnullを返す() {
        val jsonString = """
                {"key1": "value1"}
            """.trimIndent()
        val jsonNode = JsonNode.of(jsonString)
        jsonNode.get<String>("$.key999") shouldBeEqualTo null
    }

    @Test
    fun JSONPathで指定したKeyで取得する値の型変換が失敗したときnullを返す() {
        val jsonString = """
                {"key1": "value1"}
            """.trimIndent()

        val jsonNode = JsonNode.of(jsonString)
        jsonNode.get<Int>("$.key1") shouldBeEqualTo null
    }

    @Test
    fun JSONPathで指定したKeyの文字列を取得する() {
        val jsonString = """
            {"key1": "value1"}
        """.trimIndent()

        val jsonNode = JsonNode.of(jsonString)
        jsonNode.get<String>("$.key1") shouldBeEqualTo "value1"
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
        jsonNode.get<Int>("$.key1") shouldBeEqualTo 1
    }

    @Test
    fun JSONPathで指定したKeyの小数値を取得する() {
        val jsonString = """
            {"key1": 1.2}
        """.trimIndent()

        val jsonNode = JsonNode.of(jsonString)
        jsonNode.get<Double>("$.key1") shouldBeEqualTo 1.2
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

    @Test
    fun JSONPathで指定したKeyの配列が存在しないときnullを返す() {
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
        jsonNode.getFilteredList("$.key3", "xxx", "b") shouldBeEqualTo null
    }

    @Test
    fun JSONPathで指定したKeyの配列の長さを返す() {
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
        jsonNode.getArrayLength("$.key1") shouldBeEqualTo 4
    }

    @Test
    fun `配列の長さを取得しようとしたとき、JSONPathで指定したKeyの配列が存在しないときnullを返す`() {
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
        jsonNode.getArrayLength("$.key3") shouldBeEqualTo null
    }

    @Test
    fun `コンストラクタから受け取ったJSON文字列を、余分な空白を含まない文字列に変換できる`() {
        val jsonString = """    {"key1" : 10, "key2": "value2"  }  """
        val expected = """{"key1":10,"key2":"value2"}"""

        JsonNode.of(jsonString).toJsonString() shouldBeEqualTo expected
    }
}
