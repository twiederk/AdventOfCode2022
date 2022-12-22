import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MonkeyMathTest {

    @Test
    fun loadData() {
        // act
        val rawData = MonkeyMath.loadData("Day21_TestData.txt")

        // assert
        assertThat(rawData).hasSize(15)
    }

    @Test
    fun parseData() {
        // arrange
        val rawData = MonkeyMath.loadData("Day21_TestData.txt")

        // act
        val numbersAndEquations = MonkeyMath.parseData(rawData)

        // assert
        assertThat(numbersAndEquations.first).hasSize(8)
        assertThat(numbersAndEquations.second).hasSize(7)
    }

    @Test
    fun parseNumberValue() {
        // arrange

        // act
        val numberValue = MonkeyMath.parseNumberValue("hmdt: 32")

        // assert

    }
}