package com.uzabase.playtest.gauge.rest

import java.time.ZonedDateTime

data class JsonList(private val value: List<Map<String, Any>>) {
    fun sortByStringAsc(sortKey: String): List<Map<String, Any>> = this.value.sortedBy {
        it[sortKey] as String
    }

    fun sortByStringDesc(sortKey: String): List<Map<String, Any>> = this.value.sortedByDescending {
        it[sortKey] as String
    }

    // TODO: Double だと常に誤差の問題がつきまとうので BigDecimal とか String でいいかも
    fun sortByNumberAsc(sortKey: String) = this.value.sortedBy {
        (it[sortKey] as Number).toDouble()
    }

    fun sortByNumberDesc(sortKey: String) = this.value.sortedByDescending {
        (it[sortKey] as Number).toDouble()
    }

    fun sortByZonedDateTimeAsc(sortKey: String) = this.value.sortedBy {
        ZonedDateTime.parse(it[sortKey] as String)
    }

    fun sortByZonedDateTimeDesc(sortKey: String) = this.value.sortedByDescending {
        ZonedDateTime.parse(it[sortKey] as String)
    }
}
