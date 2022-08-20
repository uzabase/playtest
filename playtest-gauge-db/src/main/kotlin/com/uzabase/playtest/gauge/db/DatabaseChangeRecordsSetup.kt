package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.BeforeScenario
import net.jcip.annotations.NotThreadSafe

@NotThreadSafe
class DatabaseChangeRecordsSetup {

    /**
     * tags: record-changes
     * シナリオが始まったタイミングから、1つ目の変化数のアサートのタイミングまでのDBの変化を記録する
     */
    @BeforeScenario(tags = ["record-changes"])
    fun startRecord() {
        DatabasesChanges.startRecord()
    }
}