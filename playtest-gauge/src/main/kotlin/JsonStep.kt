import com.thoughtworks.gauge.Step
import com.uzabase.JsonNode
import org.amshove.kluent.shouldBeEqualTo

class JsonStep {

    @Step("レスポンスのJSONの<jsonPath>が文字列の<expected>となっていること")
    fun assertJson(jsonPath: String, expected: String) {
        JsonNode.of(DataStore.loadJsonFromScenario()).get<String>(jsonPath) shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>が整数値の<expected>となっていること")
    fun assertJson(jsonPath: String, expected: Int) {
        JsonNode.of(DataStore.loadJsonFromScenario()).get<Int>(jsonPath) shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>が小数値の<expected>となっていること")
    fun assertJson(jsonPath: String, expected: Double) {
        JsonNode.of(DataStore.loadJsonFromScenario()).get<Double>(jsonPath) shouldBeEqualTo expected
    }

    @Step("レスポンスのJSONの<jsonPath>が真偽値の<expected>となっていること")
    fun assertJson(jsonPath: String, expected: Boolean) {
        JsonNode.of(DataStore.loadJsonFromScenario()).get<Boolean>(jsonPath) shouldBeEqualTo expected
    }
}