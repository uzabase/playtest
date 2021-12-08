package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.BeforeScenario
import net.jcip.annotations.NotThreadSafe


val dbChangesMap = GaugeDbConfig.getRecords().associateWith { DatabaseChanges(it) }

/**
 * tags: record-changes がついたシナリオの1Step目の前後の変化を記録する
 */
@NotThreadSafe
class DatabaseChangeRecordsSetup {

    @BeforeScenario(tags = ["record-changes"])
    fun startRecord() {
        println(dbChangesMap)
        dbChangesMap.forEach {
            println(it.key)
            it.value.setup()
        }
        dbChangesMap.forEach {
            it.value.startRecordOnce()
        }
    }
}