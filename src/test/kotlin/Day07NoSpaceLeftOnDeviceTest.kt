import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class NoSpaceLeftOnDeviceTest {

    @Test
    fun loadData() {

        // act
        val terminalOutput = NoSpaceLeftOnDevice().loadData(Path("src", "test", "resources", "Day07_TestData.txt"))

        // assert
        assertThat(terminalOutput).hasSize(23)
    }

}