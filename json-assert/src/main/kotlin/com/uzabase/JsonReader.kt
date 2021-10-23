package com.uzabase

import com.nfeld.jsonpathkt.JsonPath
import com.nfeld.jsonpathkt.extension.read

interface JsonReader {
    fun getByJsonPath(json: String, path: String): String
}

class JsonReaderImpl : JsonReader {
    override fun getByJsonPath(json: String, path: String): String {
        return JsonPath.parse(json)?.read(path)!!
    }
}