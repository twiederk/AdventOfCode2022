import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProboscideaVolcaniumTest {

    @Test
    fun loadData() {

        // act
        val rawData = ProboscideaVolcanium().loadData("Day16_TestData.txt")

        // assert
        assertThat(rawData).hasSize(10)
    }

    @Test
    fun createPipes() {
        // arrange
        val rawData = ProboscideaVolcanium().loadData("Day16_TestData.txt")

        // act
        val pipes = ProboscideaVolcanium().createPipes(rawData)

        // assert
        assertThat(pipes).hasSize(10)

    }
}