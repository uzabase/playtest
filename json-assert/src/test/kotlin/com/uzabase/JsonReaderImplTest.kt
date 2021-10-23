package com.uzabase

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class JsonReaderImplTest {

    @Test
    fun JSONPathで指定したKeyの文字列を取得する() {
        val actual = JsonReaderImpl().getByJsonPath("""{"test": "test"}""", "$.test")
        "test" shouldBeEqualTo actual
    }
}