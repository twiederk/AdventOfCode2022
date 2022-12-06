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

    @Test
    fun createWindows() {
        // arrange
        val dataStream = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"

        // act
        val windows = dataStream.windowed(size = 4, step = 1)

        // assert
        assertThat(windows).hasSize(27)
        assertThat(windows[0]).isEqualTo("mjqj")
        assertThat(windows[1]).isEqualTo("jqjp")
    }

}