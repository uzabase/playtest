package com.uzabase.playtest.gauge.db

import com.thoughtworks.gauge.AfterStep
import com.thoughtworks.gauge.BeforeScenario
import com.thoughtworks.gauge.BeforeStep
import net.jcip.annotations.NotThreadSafe


val dbChangesMap = GaugeDbConfig.getRecords().associateWith { DatabaseChanges(it) }

/**
 * tags: record-changes がついたシナリオの1Step目の前後の変化を記録する
 */
@NotThreadSafe
class DatabaseChangeRecordsSetup {

    @BeforeScenario()
    fun setup() {
        dbChangesMap.forEach {
            it.value.setup()
        }
    }

    @BeforeStep(tags = ["record-changes"])
    fun startRecord() {
        dbChangesMap.forEach {
            it.value.startRecordIfNot()
        }
    }

    @AfterStep(tags = ["record-changes"])
    fun endRecord() {
        dbChangesMap.forEach {
            it.value.endRecordIfNot()
        }
    }
}