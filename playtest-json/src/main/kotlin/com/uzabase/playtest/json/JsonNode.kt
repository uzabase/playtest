package com.uzabase.playtest.json

import com.fasterxml.jackson.core.type.TypeReference
import com.nfeld.jsonpathkt.JsonPath
import com.nfeld.jsonpathkt.Token
import com.nfeld.jsonpathkt.extension.read
import com.nfeld.jsonpathkt.util.JacksonUtil
import com.fasterxml.jackson.databind.JsonNode as JN

data class JsonNode(val json: JN) {
    companion object {
        fun of(json: String) = JsonPath.parse(json)?.let(::JsonNode) ?: throw RuntimeException()
    }

    inline fun <reified T : Any> get(path: String): T? = json.read(path)

    inline fun <reified T> getValue(path: String): T? {
        if (json.isMissingNode || json.isNull) {
            return null
        }

        if (notExistKey(path)) {
            throw Exception()
        }

        val lastValue = JsonPath(path).tokens.fold(initial = json) { valueAtPath: JN?, nextToken: Token ->
            valueAtPath?.let { nextToken.read(it) }
        }

        return when {
            lastValue == null -> null
            lastValue.isNull || lastValue.isMissingNode -> throw Exception()
            else -> {
                try {
                    JacksonUtil.mapper.convertValue(lastValue, object : TypeReference<T>() {})
                } catch (e: Exception) {
                    throw(e)
                }
            }
        }
    }

    fun isNull(path: String): Boolean {
        if (json.isMissingNode || json.isNull) {
            throw Exception()
        }

        if (notExistKey(path)) {
            throw Exception()
        }

        val lastValue = JsonPath(path).tokens.fold(initial = json) { valueAtPath: JN?, nextToken: Token ->
            valueAtPath?.let { nextToken.read(it) }
        }

        return lastValue == null
    }

    fun notExistKey(path: String): Boolean {
        val atPath = path.replace("$.", "/").replace(".", "/")
        return json.at(atPath).isMissingNode
    }


    fun getArrayLength(arrayJsonPath: String): Int? = json.read<List<Any>>(arrayJsonPath)?.size

    fun getFilteredList(arrayJsonPath: String, filterKey: String, filterValue: String): List<Map<Any, Any>>? =
        json.read<List<Map<Any, Any>>>(arrayJsonPath)?.filter { it[filterKey] == filterValue }

    fun toJsonString() = json.toString()
}
