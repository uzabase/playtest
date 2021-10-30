import com.thoughtworks.gauge.Step

class TestStep {
    @Step("シナリオデータストアに<json>を保存する")
    fun storeJson(json: String) {
        DataStore.storeJsonToScenario(json)
    }
}