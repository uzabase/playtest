import com.codeborne.selenide.Selenide.`$`
import com.thoughtworks.gauge.Step
import com.uzabase.playtest.gauge.browser.attributeContains
import com.uzabase.playtest.gauge.browser.attributeEndsWith
import com.uzabase.playtest.gauge.browser.attributeStartsWith

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
}