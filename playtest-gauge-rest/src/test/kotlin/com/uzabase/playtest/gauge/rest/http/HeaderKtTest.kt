package com.uzabase.playtest.gauge.rest.http

import org.amshove.kluent.shouldBeEqualTo
import kotlin.test.Test

class HeaderKtTest {

    @Test
    fun `headersOf_フィールド名と値のペアからHeadersを作成する`() {
        val fieldName1 = "fieldName1"
        val fieldValue1 = "fieldValue1"
        headersOf(FieldName(fieldName1) to fieldValue1) shouldBeEqualTo
                mapOf(FieldName(fieldName1) to listOf(fieldValue1))
    }

    @Test
    fun `headersOf_複数のフィールド名と値のペアからHeadersを作成する`() {
        val fieldName1 = "fieldName1"
        val fieldName2 = "fieldName2"
        val fieldValue11 = "fieldValue11"
        val fieldValue21 = "fieldValue21"
        headersOf(
            FieldName(fieldName1) to fieldValue11,
            FieldName(fieldName2) to fieldValue21
        ) shouldBeEqualTo mapOf(
            FieldName(fieldName1) to listOf(fieldValue11),
            FieldName(fieldName2) to listOf(fieldValue21)
        )
    }

    @Test
    fun `headersOf_ひとつのフィールド名に対し複数の値を設定した場合は複数の値を保持する`() {
        val fieldName1 = "fieldName1"
        val fieldValue11 = "fieldValue11"
        val fieldValue12 = "fieldValue12"
        headersOf(
            FieldName(fieldName1) to fieldValue11,
            FieldName(fieldName1) to fieldValue12,
        ) shouldBeEqualTo mapOf(
            FieldName(fieldName1) to listOf(fieldValue11, fieldValue12)
        )
    }

    @Test
    fun `headersFrom_フィールド名と値のペアからHeadersを作成する`() {
        val fieldName1 = "fieldName1"
        val fieldValue1 = "fieldValue1"
        headersFrom(fieldName1 to fieldValue1) shouldBeEqualTo
                mapOf(FieldName(fieldName1) to listOf(fieldValue1))
    }

    @Test
    fun `headersFrom_複数のフィールド名と値のペアからHeadersを作成する`() {
        val fieldName1 = "fieldName1"
        val fieldName2 = "fieldName2"
        val fieldValue11 = "fieldValue11"
        val fieldValue21 = "fieldValue21"
        headersFrom(
            fieldName1 to fieldValue11,
            fieldName2 to fieldValue21
        ) shouldBeEqualTo mapOf(
            FieldName(fieldName1) to listOf(fieldValue11),
            FieldName(fieldName2) to listOf(fieldValue21)
        )
    }

    @Test
    fun `headersFrom_ひとつのフィールド名に対し複数の値を設定した場合は複数の値を保持する`() {
        val fieldName1 = "fieldName1"
        val fieldValue11 = "fieldValue11"
        val fieldValue12 = "fieldValue12"
        headersFrom(
            fieldName1 to fieldValue11,
            fieldName1 to fieldValue12,
        ) shouldBeEqualTo mapOf(
            FieldName(fieldName1) to listOf(fieldValue11, fieldValue12)
        )
    }
}
