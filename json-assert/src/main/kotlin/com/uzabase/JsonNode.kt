package com.uzabase

import com.nfeld.jsonpathkt.JsonPath
import com.fasterxml.jackson.databind.JsonNode as JN
import com.nfeld.jsonpathkt.extension.read
import java.lang.RuntimeException

data class JsonNode(val json: JN) {
    companion object{
        fun of(json: String) = JsonPath.parse(json)?.let(::JsonNode) ?: throw RuntimeException()
    }

    inline fun <reified T :Any> get(path: String): T = json.read(path) ?: throw NotFoundJsonValueException(json.toString(), path)
}
