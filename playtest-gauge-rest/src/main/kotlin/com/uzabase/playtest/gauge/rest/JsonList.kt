package com.uzabase.playtest.gauge.rest

sealed class Order {
    object Desc : Order();
    object Asc : Order()
}

data class JsonList(private val value: List<Map<String, Any>>) {
    fun sortByNumber(sortKey: String, order: Order): List<Map<String, Any>> {
        return when (order) {
            is Order.Asc -> {
                this.value.sortedBy { (it[sortKey] as Number).toDouble() }
            }
            is Order.Desc -> {
                this.value.sortedByDescending { (it[sortKey] as Number).toDouble() }
            }
        }
    }
}