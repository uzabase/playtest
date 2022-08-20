package com.uzabase.playtest.gauge.db

import io.mockk.*
import org.amshove.kluent.`should be equal to`
import org.assertj.db.type.Changes
import org.assertj.db.type.Source
import org.junit.jupiter.api.Test

internal class DatabaseChangesTest {
    @Test
    fun DatabseChangesでセットアップする() {
        val databaseChanges = DatabaseChanges("test")
        databaseChanges.setup()
        databaseChanges.isStarted() `should be equal to` false
        databaseChanges.isEnded() `should be equal to` false
    }
}