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


class JsonReaderImpl : JsonReader {
    override fun getStringByJsonPath(json: String, path: String): String = getValueByJsonPath(json, path)
    override fun getBooleanByJsonPath(json: String, path: String): Boolean = getValueByJsonPath(json, path)
    override fun getIntByJsonPath(json: String, path: String): Int = getValueByJsonPath(json, path)
    override fun getDoubleByJsonPath(json: String, path: String): Double = getValueByJsonPath(json, path)

    inline fun <reified T : Any> getValueByJsonPath(json: String, path: String): T =
        JsonPath.parse(json)?.read(path) ?: throw NotFoundJsonValueException(json, path)
}
