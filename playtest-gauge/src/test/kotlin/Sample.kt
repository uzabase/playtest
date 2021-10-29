import com.thoughtworks.gauge.Step
import org.amshove.kluent.fail

class Sample {

    @Step("サンプル")
    fun sample() {
        fail("落とす")
    }
}