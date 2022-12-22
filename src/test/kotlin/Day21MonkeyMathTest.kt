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
        // act
        val numberValue = MonkeyMath.parseNumberValue("hmdt: 32")

        // assert
        assertThat(numberValue.name).isEqualTo("hmdt")
        assertThat(numberValue.value).isEqualTo(32)
    }

    @Test
    fun parseEquation() {

        // act
        val equation = MonkeyMath.parseEquation("cczh: sllz + lgvd")

        // assert
        assertThat(equation.name).isEqualTo("cczh")
        assertThat(equation.number1.name).isEqualTo("sllz")
        assertThat(equation.number1.value).isEqualTo(-1)
        assertThat(equation.number2.name).isEqualTo("lgvd")
        assertThat(equation.number2.value).isEqualTo(-1)
        assertThat(equation.name).isEqualTo("cczh")

    }
}