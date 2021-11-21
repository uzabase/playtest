package com.uzabase.playtest.gauge.rest.extension


import com.uzabase.playtest.gauge.rest.from
import com.uzabase.playtest.gauge.rest.merge
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.*

internal class PropertiesExtensionKtTest {
    @Test
    fun `受け取ったファイルパスのプロパティファイルから、プロパティを生成する`() {
        val properties = Properties()
        val path = "src/test/resources/com/uzabase/playtest/gauge/rest/properties/test.properties"
        properties.from(path) shouldBeEqualTo Properties().apply { this["test"] = "test" }
    }

    @Test
    fun `2つのプロパティをマージする`() {
        val p1 = Properties().apply {
            this["test1"] = "1"
            this["test2"] = "2"
        }
        val p2 = Properties().apply {
            this["test1"] = "a"
            this["test3"] = "3"
        }
        p1.merge(p2) shouldBeEqualTo Properties().apply {
            this["test1"] = "a"
            this["test2"] = "2"
            this["test3"] = "3"
        }
    }
}