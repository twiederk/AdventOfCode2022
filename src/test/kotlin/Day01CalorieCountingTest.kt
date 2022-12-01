import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class Day01CalorieCountingTest {

    @Test
    fun loadData() {
        // arrange
        val path = Path("src", "test", "resources", "Day01_TestData.txt")
        println(path.toAbsolutePath())

        // act
        val items = CalorieCounting().loadData(path)

        // assert
        assertThat(items).hasSize(14)
    }

}