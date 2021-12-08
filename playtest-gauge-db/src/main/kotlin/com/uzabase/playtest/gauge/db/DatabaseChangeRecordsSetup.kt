package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.BeforeScenario
import net.jcip.annotations.NotThreadSafe


val dbChangesMap = GaugeDbConfig.getRecords().associateWith { DatabaseChanges(it) }


@NotThreadSafe
class DatabaseChangeRecordsSetup {

    /**
     * tags: record-changes
     * シナリオが始まったタイミングから、1つ目の変化数のアサートのタイミングまでのDBの変化を記録する
     */
    @BeforeScenario(tags = ["record-changes"])
    fun startRecord() {
        dbChangesMap.forEach {
            println(it.key)
            it.value.setup()
        }
        dbChangesMap.forEach {
            it.value.startRecordOnce()
        }
    }
}