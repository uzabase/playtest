import com.thoughtworks.gauge.datastore.ScenarioDataStore

object DataStore {
    private const val jsonKey = "JSON"

    fun storeToScenario(key: String, value: Any) {
        ScenarioDataStore.put(key, value)
    }

    fun <T> loadFromScenario(key: String): T = ScenarioDataStore.get(key) as T

    fun storeJsonToScenario(json: String) {
        storeToScenario(jsonKey, json)
    }

    fun loadJsonFromScenario(): String = loadFromScenario(jsonKey)
}
