package com.uzabase.playtest.db

import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.ReplacementDataSet

fun IDataSet.emptyToNull() = ReplacementDataSet(this).apply {
    this.addReplacementObject("", null)
}

fun IDataSet.replace(replaceWith: Map<String, String>) = ReplacementDataSet(this).apply {
    replaceWith.entries.map {
        this.addReplacementObject(it.key, it.value)
    }
}