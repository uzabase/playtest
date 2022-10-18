package com.uzabase.playtest.gauge.rest.http

data class FieldName(val value: String) {

    private val normalizedValue = value.lowercase()

    override fun equals(other: Any?) =
        when (other) {
            is FieldName -> normalizedValue == other.normalizedValue
            else -> false
        }

    override fun hashCode() = normalizedValue.hashCode()
}
typealias FieldValue = String
typealias FieldValues = Collection<FieldValue>
typealias Headers = Map<FieldName, FieldValues>

fun headersOf(vararg fieldPairs: Pair<FieldName, FieldValue>): Headers =
    fieldPairs.groupBy({ it.first }) { it.second }

fun headersFrom(vararg fieldPairs: Pair<String, String>): Headers =
    fieldPairs.map { (k, v) -> FieldName(k) to v }.toTypedArray().let(::headersOf)
