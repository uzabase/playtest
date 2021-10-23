package com.uzabase

interface JsonReader {
    fun getStringByJsonPath(json: String, path: String): String
    fun getBooleanByJsonPath(json: String, path: String): Boolean
}
