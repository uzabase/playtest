package com.uzabase.playtest.gauge.rest

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime

internal class JsonListTest {
    @Test
    fun Jsonの配列を指定された文字列のキーの昇順で並び替える() {
        val map1 = mapOf("id" to "id1")
        val map2 = mapOf("id" to "id2")
        val map3 = mapOf("id" to "id10")
        val target = listOf(map1, map2, map3).let(::JsonList)
        val expect = listOf(map1, map3, map2)
        target.sortByStringAsc("id") shouldBeEqualTo expect
    }

    @Test
    fun Jsonの配列を指定された文字列のキーの降順で並び替える() {
        val map1 = mapOf("id" to "id1")
        val map2 = mapOf("id" to "id2")
        val map3 = mapOf("id" to "id10")
        val target = listOf(map1, map2, map3).let(::JsonList)
        val expect = listOf(map2, map3, map1)
        target.sortByStringDesc("id") shouldBeEqualTo expect
    }

    @Test
    fun Jsonの配列を指定された数値のキーの昇順で並び替える() {
        val jsonList = JsonList(listOf(mapOf("id" to 1), mapOf("id" to 3), mapOf("id" to 2)))
        jsonList.sortByNumberAsc("id") shouldBeEqualTo listOf(
            mapOf("id" to 1),
            mapOf("id" to 2),
            mapOf("id" to 3)
        )
    }

    @Test
    fun Jsonの配列を指定された数値のキーの降順で並び替える() {
        val jsonList = JsonList(listOf(mapOf("id" to 1), mapOf("id" to 3), mapOf("id" to 2)))
        jsonList.sortByNumberDesc("id") shouldBeEqualTo listOf(
            mapOf("id" to 3),
            mapOf("id" to 2),
            mapOf("id" to 1)
        )
    }

    @Test
    fun Jsonの配列を指定された時刻のキーの昇順で並び替える() {
        val jsonList = JsonList(
            listOf(
                mapOf("createdAt" to "2007-12-03T12:15:30+01:00"),
                mapOf("createdAt" to "2007-12-04T10:15:30+01:00"),
                mapOf("createdAt" to "2007-12-03T10:15:30+01:00")
            )
        )
        val expected = listOf(
            mapOf("createdAt" to ZonedDateTime.parse("2007-12-03T10:15:30+01:00")),
            mapOf("createdAt" to ZonedDateTime.parse("2007-12-03T12:15:30+01:00")),
            mapOf("createdAt" to ZonedDateTime.parse("2007-12-04T10:15:30+01:00"))
        )
        val result = jsonList.sortByZonedDateTimeAsc("createdAt")
        result.toString() shouldBeEqualTo expected.toString() // こうしないとZonedDateTimeが比較できない
    }

    @Test
    fun Jsonの配列を指定された時刻のキーの降順で並び替える() {
        val jsonList = JsonList(
            listOf(
                mapOf("createdAt" to "2007-12-03T12:15:30+01:00"),
                mapOf("createdAt" to "2007-12-04T10:15:30+01:00"),
                mapOf("createdAt" to "2007-12-03T10:15:30+01:00")
            )
        )
        val expected = listOf(
            mapOf("createdAt" to ZonedDateTime.parse("2007-12-04T10:15:30+01:00")),
            mapOf("createdAt" to ZonedDateTime.parse("2007-12-03T12:15:30+01:00")),
            mapOf("createdAt" to ZonedDateTime.parse("2007-12-03T10:15:30+01:00"))
        )
        val result = jsonList.sortByZonedDateTimeDesc("createdAt")
        result.toString() shouldBeEqualTo expected.toString() // こうしないとZonedDateTimeが比較できない
    }
}
