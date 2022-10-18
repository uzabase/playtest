package com.uzabase.playtest.gauge.rest.http

import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test

class FieldNameTest {

    @Test
    fun `valueが同じものを比較した場合はtrue`() {
        (FieldName("Content-Type") == FieldName("Content-Type")) shouldBeEqualTo true
    }

    @Test
    fun `valueの比較は大文字小文字を区別しない`() {
        (FieldName("Content-Type") == FieldName("content-type")) shouldBeEqualTo true
    }

    @Test
    fun `valueが異なるものを比較した場合はfalse`() {
        (FieldName("Content-Type") == FieldName("Content-Length")) shouldBeEqualTo false
    }
}
