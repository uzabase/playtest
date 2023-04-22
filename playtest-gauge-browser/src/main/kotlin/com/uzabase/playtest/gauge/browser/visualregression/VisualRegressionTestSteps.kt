package com.uzabase.playtest.gauge.browser.visualregression

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.*
import com.github.romankh3.image.comparison.ImageComparison
import com.github.romankh3.image.comparison.ImageComparisonUtil
import com.thoughtworks.gauge.Step
import org.openqa.selenium.OutputType
import java.io.ByteArrayInputStream
import java.io.File
import java.lang.RuntimeException
import javax.imageio.ImageIO

class VisualRegressionTestSteps {
    private val ErrorThreshold = 0.04

    @Step("[not-provide] ページ<url>を開く")
    fun open(path: String) {
        Selenide.open(path)
    }

    @Step("ページの見た目が<imagePath>と一致する")
    fun verifyScreenShotEqualTo(imagePath: String) {
        val actualShot = screenshot(OutputType.BYTES)
            .let(::ByteArrayInputStream)
            .let(ImageIO::read)
        val expectedShot = runCatching { ImageIO.read(File(imagePath)) }.getOrNull()

        // save image if image not exist.
        if (expectedShot == null) {
            ImageComparisonUtil.saveImage(File(imagePath), actualShot)
            return
        }
        val comparisonResult = ImageComparison(expectedShot, actualShot).compareImages()
        ImageComparisonUtil.saveImage(File("logs/hoge.png"), comparisonResult.result)
        if (comparisonResult.differencePercent > ErrorThreshold) {
            if (isUpdateMode()) {
                ImageComparisonUtil.saveImage(File(imagePath), comparisonResult.actual)
            } else {
                val logFile = File("logs/vrresults/$imagePath")
                ImageComparisonUtil.saveImage(logFile, comparisonResult.result)
                throw RuntimeException("""
                    Comparison failure! There was a ${comparisonResult.differencePercent * 100.0}% difference in the compared images.
                    Result: ${logFile.absolutePath}
                    """.trimIndent())
            }
        }
    }

    @Step("[not-provide] ページの見た目が<imagePath>と一致しない")
    fun verifyScreenShotNotEqualTo(imagePath: String) {
        val actualShot = screenshot(OutputType.BYTES)
            .let(::ByteArrayInputStream)
            .let(ImageIO::read)
        val expectedShot = runCatching { ImageIO.read(File(imagePath)) }.getOrNull()

        val comparisonResult = ImageComparison(expectedShot, actualShot).compareImages()
        if (comparisonResult.differencePercent <= ErrorThreshold) {
            throw RuntimeException("""
                Comparison failure. Image is same.
            """.trimIndent())
        }
    }

    @Step("[not-provide] ページの見た目が<imagePath>と一致する（更新せず、差異があればエラーとする）")
    fun verifyScreenShotStrictEqualTo(imagePath: String) {
        val actualShot = screenshot(OutputType.BYTES)
            .let(::ByteArrayInputStream)
            .let(ImageIO::read)
        val expectedShot = runCatching { ImageIO.read(File(imagePath)) }.getOrNull()

        val comparisonResult = ImageComparison(expectedShot, actualShot).compareImages()
        if (comparisonResult.differencePercent > ErrorThreshold) {
            throw RuntimeException("""
                Comparison failure. Image is not same.
                Difference is ${comparisonResult.differencePercent}
                """.trimIndent())
        }
    }

    @Step("[not-provide] 画像<filePath>が存在しない")
    fun verifyNotExistFile(filePath: String) {
        File(filePath).takeIf { it.exists() }?.run { delete() }
        if (File(filePath).exists()) {
            throw RuntimeException("Image found at $filePath")
        }
    }

    @Step("[not-provide] 画像<filePath>が存在する")
    fun verifyExistFile(filePath: String) {
        if (!File(filePath).exists()) {
            throw RuntimeException("Image not found at $filePath")
        }
    }

    @Step("[not-provide] 「ページの見た目が<imagePath>と一致する」を実行するとエラーになる")
    fun verifyFailure(imagePath: String) {
        try {
            verifyScreenShotEqualTo(imagePath)
        } catch (e: Throwable) {
            return
        }
        throw RuntimeException("Success to execute 'verifyScreenShotEqualTo'. But we expected that step throws exception.")
    }

    var override: String? = null
    @Step("[not-provide] 環境変数<key>に値<value>を入れる")
    fun setEnv(key: String, value: String) {
        override = value
    }

    @Step("[not-provide] 環境変数<key>を削除する")
    fun clearEnv(key: String) {
        override = null
    }

    fun isUpdateMode(): Boolean {
        return (override ?: System.getenv("UPDATE_IMAGE")) == "true"
    }

    @Step("[not-provide] ファイル<src>を<dst>にコピーする")
    fun copyFile(src: String, dst: String) {
        File(src).copyTo(File(dst), true)
    }
}