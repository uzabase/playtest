package com.uzabase.playtest.gauge.rest

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class JsonListTest {
    @Test
    fun Jsonの配列を指定されたキーの昇順で並び替える() {
        val jsonList = JsonList(listOf(mapOf("id" to 1), mapOf("id" to 3), mapOf("id" to 2)))
        jsonList.sortByNumber("id", Order.Asc) shouldBeEqualTo listOf(
            mapOf("id" to 1),
            mapOf("id" to 2),
            mapOf("id" to 3)
        )
    }
    @Test
    fun Jsonの配列を指定されたキーの降順で並び替える() {
        val jsonList = JsonList(listOf(mapOf("id" to 1), mapOf("id" to 3), mapOf("id" to 2)))
        jsonList.sortByNumber("id", Order.Desc) shouldBeEqualTo listOf(
            mapOf("id" to 3),
            mapOf("id" to 2),
            mapOf("id" to 1)
        )
    }
}