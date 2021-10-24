package com.uzabase

import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

internal class JsonReaderImplTest {
    val jsonReader = JsonReaderImpl()

    @Test
    fun JSONPathで指定したKeyの値が存在しないとき例外をスローする() {
        invoking { jsonReader.getValueByJsonPath<String>("""{"test": "test"}""", "$.xxx") } shouldThrow NotFoundJsonValueException::class
    }

    @Test
    fun JSONPathで指定したKeyの文字列を取得する() {
        val actual = jsonReader.getStringByJsonPath("""{"test": "test"}""", "$.test")
        "test" shouldBeEqualTo actual
    }

    @Test
    fun JSONPathで指定したKeyの真偽値を取得する() {
        val actual = jsonReader.getBooleanByJsonPath("""{"test": true}""", "$.test")
        true shouldBeEqualTo actual
    }

    @Test
    fun JSONPathで指定したKeyの整数値を取得する() {
        val actual = jsonReader.getIntByJsonPath("""{"test": 1}""", "$.test")
        1 shouldBeEqualTo actual
    }

    @Test
    fun JSONPathで指定したKeyの小数値を取得する() {
        val actual = jsonReader.getDoubleByJsonPath("""{"test": 1.0}""", "$.test")
        1.0 shouldBeEqualTo actual
    }
}