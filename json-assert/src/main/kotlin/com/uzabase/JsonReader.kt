package com.uzabase

interface JsonReader {
    fun getStringByJsonPath(json: String, path: String): String
    fun getBooleanByJsonPath(json: String, path: String): Boolean
    fun getIntByJsonPath(json: String, path: String): Int
    fun getDoubleByJsonPath(json: String, path: String): Double
}
