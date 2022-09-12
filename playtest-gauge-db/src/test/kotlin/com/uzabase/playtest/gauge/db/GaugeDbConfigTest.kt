package com.uzabase.playtest.gauge.db

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class GaugeDbConfigTest {
    @Test
    fun ConfigからDB名をリストで取得する() {
        System.setProperty("db.z_test.url", "jdbc:postgresql://localhost:5433/z_test")
        val actual = GaugeDbConfig.getRecords()

        actual shouldBeEqualTo listOf("test_db", "z_test")
    }
}