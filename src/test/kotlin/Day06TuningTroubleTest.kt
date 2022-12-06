import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class TuningTroubleTest {

    @Test
    fun loadData() {
        // arrange

        // act
        val dataStream = TuningTrouble().loadData(Path("src", "test", "resources", "Day06_TestData.txt"))

        // assert
        assertThat(dataStream).isEqualTo("mjqjpqmgbljsphdztnvjfqwrcgsmlb")
    }

}