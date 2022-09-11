package com.uzabase.playtest.gauge.db

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class GaugeDbConfigTest {
    @Test
    fun ConfigからDB名をリストで取得する() {
        val actual = GaugeDbConfig.getRecords()

        actual shouldBeEqualTo listOf("test_db", "z_test")
    }
}