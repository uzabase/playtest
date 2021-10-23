package com.uzabase

import com.nfeld.jsonpathkt.JsonPath
import com.nfeld.jsonpathkt.extension.read


class JsonReaderImpl : JsonReader {
    override fun getStringByJsonPath(json: String, path: String): String {
        return getValueByJsonPath(json, path)
    }

    override fun getBooleanByJsonPath(json: String, path: String): Boolean {
        return getValueByJsonPath(json, path)
    }

    inline fun <reified T : Any> getValueByJsonPath(json: String, path: String): T {
        return JsonPath.parse(json)?.read(path) ?: throw NotFoundJsonValueException(json, path)
    }
}
