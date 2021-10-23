package com.uzabase

import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

internal class JsonReaderImplTest {

    @Test
    fun JSONPathで指定したKeyの値が存在しないとき例外をスローする() {
        invoking { JsonReaderImpl().getValueByJsonPath<String>("""{"test": "test"}""", "$.xxx") } shouldThrow NotFoundJsonValueException::class
    }

    @Test
    fun JSONPathで指定したKeyの文字列を取得する() {
        val actual = JsonReaderImpl().getStringByJsonPath("""{"test": "test"}""", "$.test")
        "test" shouldBeEqualTo actual
    }

    @Test
    fun JSONPathで指定したKeyの真偽値を取得する() {
        val actual = JsonReaderImpl().getBooleanByJsonPath("""{"test": true}""", "$.test")
        true shouldBeEqualTo actual
    }
}