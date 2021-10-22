package com.uzabase

interface JsonReader {
    fun getByJsonPath(path: String): String
}

class JsonReaderImpl : JsonReader {
    override fun getByJsonPath(path: String): String {
        TODO()
    }
}