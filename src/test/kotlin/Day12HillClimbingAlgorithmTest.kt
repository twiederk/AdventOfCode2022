import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class HillClimbingAlgorithmTest {

    @Test
    fun loadData() {
        // arrange

        // act
        val grid = HillClimbingAlgorithm().loadData(Path("src", "test", "resources", "Day12_TestData.txt"))

        // assert
        assertThat(grid[0]).hasSize(8)
        assertThat(grid).hasSize(5)
    }

}