import com.codeborne.selenide.Condition.exactText
import com.codeborne.selenide.Selenide.`$`
import com.thoughtworks.gauge.Step
import com.uzabase.playtest.browser.attributeContains
import com.uzabase.playtest.browser.attributeEndsWith
import com.uzabase.playtest.browser.attributeStartsWith
import com.uzabase.playtest.browser.css

class TestStep {
    @Step("[not-prived] <selector>の属性<attribute>の値に<value>が含まれる")
    fun assertContains(selector: String, attribute: String, value: String) {
        `$`(selector).shouldHave(attributeContains(attribute, value))
    }

    @Step("[not-prived] <selector>の属性<attribute>の値が<value>で始まる")
    fun assertStartsWith(selector: String, attribute: String, value: String) {
        `$`(selector).shouldHave(attributeStartsWith(attribute, value))
    }

    @Step("[not-prived] <selector>の属性<attribute>の値が<value>で終わる")
    fun assertEndsWith(selector: String, attribute: String, value: String) {
        `$`(selector).shouldHave(attributeEndsWith(attribute, value))
    }

    @Step("[not-prived] <selector>のcss<c>の値が<value>である")
    fun assertCss(selector: String, css: String, value: String) {
        `$`(selector).shouldHave(css(css, value))
    }
    @Step("[not-provide] Hello Worldが表示されている")
    fun shouldShowHelloWorld() {
        `$`("h1").shouldHave(exactText("Hello world"))
    }


}