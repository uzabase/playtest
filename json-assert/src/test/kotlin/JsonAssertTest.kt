import com.uzabase.JsonAssert
import org.amshove.kluent.AnyException
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class JsonAssertTest : JsonAssert {
    @Test
    fun JSONPathを指定して文字列をアサーションできる() {
        val json = """"{ "test": "test" }""""
        invoking { json.assertByJsonPath("test", "$.test") } shouldNotThrow AnyException
    }
}