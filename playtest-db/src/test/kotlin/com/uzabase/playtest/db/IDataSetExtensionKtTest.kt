package com.uzabase.playtest.db

import io.mockk.*
import org.amshove.kluent.`should be instance of`
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.ReplacementDataSet
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

internal class IDataSetExtensionKtTest {

    @AfterEach
    fun after() {
        unmockkAll()
    }

    @Test
    fun 空文字をnullに変換する() {
        mockkConstructor(ReplacementDataSet::class)
        val dataset = mockk<IDataSet>(relaxed = true, relaxUnitFun = true)
        every {
            constructedWith<ReplacementDataSet>(OfTypeMatcher<IDataSet>(IDataSet::class))
                .addReplacementObject(any(), any())
        } just runs

        dataset.emptyToNull() `should be instance of` ReplacementDataSet::class

        verify {
            constructedWith<ReplacementDataSet>(OfTypeMatcher<IDataSet>(IDataSet::class))
                .addReplacementObject("", null)
        }
    }

    @Test
    fun 特定の文字列を指定した文字列に変換したデータセットを生成できる() {
        mockkConstructor(ReplacementDataSet::class)

        val dataset = mockk<IDataSet>(relaxed = true, relaxUnitFun = true)
        every {
            constructedWith<ReplacementDataSet>(OfTypeMatcher<IDataSet>(IDataSet::class))
                .addReplacementObject(any(), any())
        } just runs
        val replaceWith = mapOf("test1" to "aftertest1", "test2" to "aftertest2")

        dataset.replace(replaceWith) `should be instance of` ReplacementDataSet::class

        verify {
            constructedWith<ReplacementDataSet>(OfTypeMatcher<IDataSet>(IDataSet::class))
                .addReplacementObject("test1", "aftertest1")
        }
        verify {
            constructedWith<ReplacementDataSet>(OfTypeMatcher<IDataSet>(IDataSet::class))
                .addReplacementObject("test2", "aftertest2")
        }
    }
}