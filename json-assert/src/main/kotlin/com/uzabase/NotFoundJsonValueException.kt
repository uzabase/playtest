package com.uzabase

data class NotFoundJsonValueException(
    val json: String,
    private val jsonPath: String
): Exception("Not found value in $json by: $jsonPath")