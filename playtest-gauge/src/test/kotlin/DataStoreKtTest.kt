import DataStore.loadFromScenario
import DataStore.loadJsonFromScenario
import DataStore.storeJsonToScenario
import DataStore.storeToScenario
import com.thoughtworks.gauge.datastore.ScenarioDataStore
import io.mockk.*
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

internal class DataStoreKtTest {
    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun シナリオデータストアに値を保存する() {
        val key = "test"
        val value = "value"
        mockkStatic(ScenarioDataStore::class)
        every { ScenarioDataStore.put(any(), any()) } just runs
        storeToScenario(key, "value")
        verify { ScenarioDataStore.put(key, value) }
    }

    @Test
    fun シナリオデータストアから値を取得する() {
        val expected = "value"
        val key = "test"
        storeToScenario(key, expected)
        loadFromScenario<String>(key) `should be equal to` ScenarioDataStore.get(key)
    }

    @Test
    fun シナリオデータストアにkeyがJsonでJSONの文字列を保存する() {
        val json = """{"test": "test"}""""
        val target = spyk(DataStore)
        every { target.storeToScenario(any(), any()) } just runs
        target.storeJsonToScenario(json)
        verify { target.storeToScenario("JSON", json) }
    }

    @Test
    fun シナリオデータストアからJSONの文字列を取得する() {
        val json = """{"test": "test"}""""
        storeJsonToScenario(json)
        loadJsonFromScenario() `should be equal to` json
    }
}