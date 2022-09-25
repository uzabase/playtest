package com.uzabase.playtest.gauge.db

import io.mockk.*
import org.amshove.kluent.`should be equal to`
import org.assertj.db.type.Changes
import org.junit.jupiter.api.Test

internal class DatabaseChangesTest {
    internal class SetupTest {
        @Test
        fun DatabaseChangesでセットアップする() {
            val databaseChanges = DatabaseChanges("test")
            databaseChanges.setup()
            databaseChanges.isStarted() `should be equal to` false
            databaseChanges.isEnded() `should be equal to` false
        }
    }

    internal class StartRecordTest {
        val databaseChangesKeys = DatabaseChangesKeys("test")
        val target = spyk(DatabaseChanges("test"))

        @Test
        fun 記録を開始する() = mockkObject(DataStore) {
            val changes = mockk<Changes>()
            every { target.isStarted() } returns false
            every { target.initialChanges() } returns changes
            every { changes.setStartPointNow() } returns mockk()
            justRun { DataStore.storeToScenario(databaseChangesKeys.changesKey, any()) }
            justRun { DataStore.storeToScenario(databaseChangesKeys.isStartKey, any()) }

            target.startRecordOnce()
            verify { DataStore.storeToScenario(any(), changes) }
            verify { DataStore.storeToScenario(any(), true) }
        }

        @Test
        fun `既に記録が開始されている時、記録を開始しない`() = mockkObject(DataStore) {
            every { target.isStarted() } returns true

            target.startRecordOnce()
            verify(exactly = 0) { DataStore.storeToScenario(any(), any<Changes>()) }
            verify(exactly = 0) { DataStore.storeToScenario(any(), any<Boolean>()) }
        }
    }
}