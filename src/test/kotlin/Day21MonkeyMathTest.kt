import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
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

    @Nested
    inner class SolutionTest {

//        create list of new know numbers
//        place in known numbers
//        solve equation

        private lateinit var monkeyMath: MonkeyMath

        @BeforeEach
        fun beforeEach() {
            val rawData = MonkeyMath.loadData("Day21_TestData.txt")
            val numbersAndEquations = MonkeyMath.parseData(rawData)
            monkeyMath = MonkeyMath(numbersAndEquations.second.toMutableList())
        }


        @Test
        fun insertKnownNumbersInEquations() {
            // arrange
            val rawData = MonkeyMath.loadData("Day21_TestData.txt")
            val numbersAndEquations = MonkeyMath.parseData(rawData)
            monkeyMath = MonkeyMath(numbersAndEquations.second.toMutableList())

            // act
            monkeyMath.insertKnownNumbersInEquations(numbersAndEquations.first)

            // assert
            assertThat(monkeyMath.equations).filteredOn { it.number1.value != -1 && it.number2.value != -1 }
                .contains(
                    Equation(
                        name = "ptdq",
                        number1 = NumberValue(name = "humn", value = 5),
                        number2 = NumberValue(name = "dvpt", value = 3),
                        operator = '-'
                    ),

                    Equation(
                        name = "drzm",
                        number1 = NumberValue(name = "hmdt", value = 32),
                        number2 = NumberValue(name = "zczc", value = 2),
                        operator = '-'
                    )
                )
        }

        @Test
        fun eval() {
            // arrange
            val equation = Equation(
                name = "ptdq",
                number1 = NumberValue(name = "humn", value = 5),
                number2 = NumberValue(name = "dvpt", value = 3),
                operator = '-'
            )


            // act
            val numberValue = monkeyMath.eval(equation)

            // assert
            assertThat(numberValue?.name).isEqualTo("ptdq")
            assertThat(numberValue?.value).isEqualTo(2)
        }
    }
}