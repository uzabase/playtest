package com.uzabase

import com.nfeld.jsonpathkt.JsonPath
import com.nfeld.jsonpathkt.extension.read


class JsonReaderImpl : JsonReader {
    override fun getStringByJsonPath(json: String, path: String): String = getValueByJsonPath(json, path)

    override fun getBooleanByJsonPath(json: String, path: String): Boolean = getValueByJsonPath(json, path)

    override fun getIntByJsonPath(json: String, path: String): Int = getValueByJsonPath(json, path)

    inline fun <reified T : Any> getValueByJsonPath(json: String, path: String): T {
        return JsonPath.parse(json)?.read(path) ?: throw NotFoundJsonValueException(json, path)
    }
}
