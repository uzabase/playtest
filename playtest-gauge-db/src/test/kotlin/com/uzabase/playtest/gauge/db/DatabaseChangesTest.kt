package com.uzabase.playtest.gauge.db

import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

internal class DatabaseChangesTest {
    @Test
    fun DatabaseChangesでセットアップする() {
        val databaseChanges = DatabaseChanges("test")
        databaseChanges.setup()
        databaseChanges.isStarted() `should be equal to` false
        databaseChanges.isEnded() `should be equal to` false
    }
}