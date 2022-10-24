package com.uzabase.playtest.json

import org.amshove.kluent.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class JsonNodeTest {
    @Nested
    @DisplayName("of()のテスト")
    inner class OfTest {
        @Test
        fun 正常にパースできる() {
            val jsonString = """ {"key1": "value1"} """.trimIndent()
            invoking {
                JsonNode.of(jsonString)
            } shouldNotThrow AnyException
        }

        @Test
        fun パースできない時例外をスローする() {
            val jsonString = """ {"key1": "value1" """.trimIndent()
            invoking {
                JsonNode.of(jsonString)
            } shouldThrow IllegalArgumentException::class
        }
    }

    @Nested
    @DisplayName("get()でのテスト")
    inner class GetTest {
        @Test
        fun JSONPathで指定したKeyの値が存在しないときnullを返す() {
            val jsonString = """ {"key1": "value1"} """.trimIndent()
            val jsonNode = JsonNode.of(jsonString)
            jsonNode.get<String>("$.key999") shouldBeEqualTo null
        }

        @Test
        fun `JSONPathで指定したKeyの値がnullだった場合をnullを返す`() {
            val jsonString = """ {"key1": null} """.trimIndent()
            val jsonNode = JsonNode.of(jsonString)
            jsonNode.get<String>("$.key1") shouldBeEqualTo null
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
        fun `コンストラクタから受け取ったJSON文字列を、余分な空白を含まない文字列に変換できる`() {
            val jsonString = """    {"key1" : 10, "key2": "value2"  }  """
            val expected = """{"key1":10,"key2":"value2"}"""

            JsonNode.of(jsonString).toJsonString() shouldBeEqualTo expected
        }
    }

    @Nested
    @DisplayName("配列のテスト")
    inner class ArrayTest {
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

    }

    @Nested
    @DisplayName("getValue()でのテスト")
    inner class GetValueTest {
        @Test
        fun `JSONPathで指定したKeyの値がnullだった場合、nullを返す`() {
            val jsonString = """ {"key1": null} """.trimIndent()
            val jsonNode = JsonNode.of(jsonString)
            jsonNode.getValue<Int>("$.key1") shouldBeEqualTo null
        }

        @Test
        fun `JSONPathで指定したKeyの値がnullだった場合、trueを返す`() {
            val jsonString = """ {"key1": null} """.trimIndent()
            val jsonNode = JsonNode.of(jsonString)
            jsonNode.isNull("$.key1") shouldBeEqualTo true
        }

        @Test
        fun `JSONPathで指定したKeyの値がnullではない場合、falseを返す`() {
            val jsonString = """ {"key1": "value1"} """.trimIndent()
            val jsonNode = JsonNode.of(jsonString)
            jsonNode.isNull("$.key1") shouldBeEqualTo false
        }

        @Test
        fun JSONPathで指定したKeyの文字列を取得する() {
            val jsonString = """
            {"key1": "value1"}
        """.trimIndent()

            val jsonNode = JsonNode.of(jsonString)
            jsonNode.getValue<String>("$.key1") shouldBeEqualTo "value1"
        }

        @Test
        fun JSONPathで指定したKeyの真偽値を取得する() {
            val jsonString = """
            {"key1": true }
        """.trimIndent()

            val jsonNode = JsonNode.of(jsonString)
            true shouldBeEqualTo jsonNode.getValue<Boolean>("$.key1")
        }

        @Test
        fun JSONPathで指定したKeyの整数値を取得する() {
            val jsonString = """
            {"key1": 1}
        """.trimIndent()

            val jsonNode = JsonNode.of(jsonString)
            jsonNode.getValue<Int>("$.key1") shouldBeEqualTo 1
        }

        @Test
        fun JSONPathで指定したKeyの小数値を取得する() {
            val jsonString = """
            {"key1": 1.2}
        """.trimIndent()

            val jsonNode = JsonNode.of(jsonString)
            jsonNode.getValue<Double>("$.key1") shouldBeEqualTo 1.2
        }

        @Test
        fun `JSONPathで指定したKeyの値が存在しないときExceptionを返す`() {
            val jsonString = """
                {"key1": null,
                "key2": "value2",
                "key3": {"key3" : "value3"} }
            """.trimIndent()
            val jsonNode = JsonNode.of(jsonString)

            invoking { jsonNode.getValue<String>("$.key3.key4") } shouldThrow AnyException
        }

        @Test
        fun JSONPathで指定したKeyで取得する値の型変換が失敗したときExceptionを返す() {
            val jsonString = """
                {"key1": "value1"}
            """.trimIndent()

            val jsonNode = JsonNode.of(jsonString)
            invoking { jsonNode.getValue<Int>("$.key1") } shouldThrow AnyException
        }
    }
}
