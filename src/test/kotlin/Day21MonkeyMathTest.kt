import MonkeyMath.Companion.UNRESOLVED_VALUE
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
        private lateinit var numberValuesAndEquations: Pair<List<NumberValue>, List<Equation>>

        @BeforeEach
        fun beforeEach() {
            val rawData = MonkeyMath.loadData("Day21_TestData.txt")
            numberValuesAndEquations = MonkeyMath.parseData(rawData)
            monkeyMath = MonkeyMath(numberValuesAndEquations.second.toMutableList())
        }


        @Test
        fun insertKnownNumbersInEquations() {

            // act
            monkeyMath.insertKnownNumbersInEquations(numberValuesAndEquations.first)

            // assert
            assertThat(monkeyMath.equations).containsExactly(
                Equation(
                    name = "root",
                    number1 = NumberValue(name = "pppw", value = UNRESOLVED_VALUE),
                    number2 = NumberValue(name = "sjmn", value = UNRESOLVED_VALUE),
                    operator = '+'
                ),
                Equation(
                    name = "cczh",
                    number1 = NumberValue(name = "sllz", value = 4),
                    number2 = NumberValue(name = "lgvd", value = UNRESOLVED_VALUE),
                    operator = '+'
                ),
                Equation(
                    name = "ptdq",
                    number1 = NumberValue(name = "humn", value = 5),
                    number2 = NumberValue(name = "dvpt", value = 3),
                    operator = '-'
                ),
                Equation(
                    name = "sjmn",
                    number1 = NumberValue(name = "drzm", value = UNRESOLVED_VALUE),
                    number2 = NumberValue(name = "dbpl", value = 5),
                    operator = '*'
                ),
                Equation(
                    name = "pppw",
                    number1 = NumberValue(name = "cczh", value = UNRESOLVED_VALUE),
                    number2 = NumberValue(name = "lfqf", value = 4),
                    operator = '/'
                ),
                Equation(
                    name = "lgvd",
                    number1 = NumberValue(name = "ljgn", value = 2),
                    number2 = NumberValue(name = "ptdq", value = UNRESOLVED_VALUE),
                    operator = '*'
                ),
                Equation(
                    name = "drzm",
                    number1 = NumberValue(name = "hmdt", value = 32),
                    number2 = NumberValue(name = "zczc", value = 2),
                    operator = '-'
                ),
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

        @Test
        fun eval_missingValue() {
            // arrange
            val equation = Equation(
                name = "ptdq",
                number1 = NumberValue(name = "humn", value = 5),
                number2 = NumberValue(
                    name = "dvpt", value = UNRESOLVED_VALUE
                ),
                operator = '-'
            )

            // act
            val numberValue = monkeyMath.eval(equation)

            // assert
            assertThat(numberValue).isNull()
        }

        @Test
        fun evalAll() {
            // arrange
            val monkeyMath = MonkeyMath(
                mutableListOf(
                    Equation(
                        name = "ptdq",
                        number1 = NumberValue(name = "humn", value = 5),
                        number2 = NumberValue(name = "dvpt", value = 3),
                        operator = '-'
                    ),
                    Equation(
                        name = "drzm",
                        number1 = NumberValue(
                            name = "hmdt", value = UNRESOLVED_VALUE
                        ),
                        number2 = NumberValue(name = "zczc", value = 2),
                        operator = '-'
                    )

                )
            )

            // act
            val numberValues = monkeyMath.evalAll()

            // assert
            assertThat(numberValues).containsExactly(
                NumberValue("ptdq", 2)
            )
        }

        @Test
        fun solve_1() {

            // assert
            val numberValues = numberValuesAndEquations.first

            // act
            val result = monkeyMath.solve(numberValues, 1)

            // assert
            assertThat(result).isEqualTo(-1)
            assertThat(monkeyMath.equations).containsExactly(
                Equation(
                    name = "root",
                    number1 = NumberValue(name = "pppw", value = UNRESOLVED_VALUE),
                    number2 = NumberValue(name = "sjmn", value = UNRESOLVED_VALUE),
                    operator = '+'
                ),
                Equation(
                    name = "cczh",
                    number1 = NumberValue(name = "sllz", value = 4),
                    number2 = NumberValue(name = "lgvd", value = UNRESOLVED_VALUE),
                    operator = '+'
                ),
                Equation(
                    name = "ptdq",
                    number1 = NumberValue(name = "humn", value = 5),
                    number2 = NumberValue(name = "dvpt", value = 3),
                    operator = '-'
                ),
                Equation(
                    name = "sjmn",
                    number1 = NumberValue(name = "drzm", value = UNRESOLVED_VALUE),
                    number2 = NumberValue(name = "dbpl", value = 5),
                    operator = '*'
                ),
                Equation(
                    name = "pppw",
                    number1 = NumberValue(name = "cczh", value = UNRESOLVED_VALUE),
                    number2 = NumberValue(name = "lfqf", value = 4),
                    operator = '/'
                ),
                Equation(
                    name = "lgvd",
                    number1 = NumberValue(name = "ljgn", value = 2),
                    number2 = NumberValue(name = "ptdq", value = UNRESOLVED_VALUE),
                    operator = '*'
                ),
                Equation(
                    name = "drzm",
                    number1 = NumberValue(name = "hmdt", value = 32),
                    number2 = NumberValue(name = "zczc", value = 2),
                    operator = '-'
                ),
            )

        }

        @Test
        fun solve_2() {

            // assert
            val numberValues = numberValuesAndEquations.first

            // act
            val result = monkeyMath.solve(numberValues, 2)

            // assert
            assertThat(result).isEqualTo(-1)
            assertThat(monkeyMath.equations).containsExactly(
                Equation(
                    name = "root",
                    number1 = NumberValue(name = "pppw", value = UNRESOLVED_VALUE),
                    number2 = NumberValue(name = "sjmn", value = UNRESOLVED_VALUE),
                    operator = '+'
                ),
                Equation(
                    name = "cczh",
                    number1 = NumberValue(name = "sllz", value = 4),
                    number2 = NumberValue(name = "lgvd", value = UNRESOLVED_VALUE),
                    operator = '+'
                ),
                Equation(
                    name = "ptdq",
                    number1 = NumberValue(name = "humn", value = 5),
                    number2 = NumberValue(name = "dvpt", value = 3),
                    operator = '-'
                ),
                Equation(
                    name = "sjmn",
                    number1 = NumberValue(name = "drzm", value = 30),
                    number2 = NumberValue(name = "dbpl", value = 5),
                    operator = '*'
                ),
                Equation(
                    name = "pppw",
                    number1 = NumberValue(name = "cczh", value = UNRESOLVED_VALUE),
                    number2 = NumberValue(name = "lfqf", value = 4),
                    operator = '/'
                ),
                Equation(
                    name = "lgvd",
                    number1 = NumberValue(name = "ljgn", value = 2),
                    number2 = NumberValue(name = "ptdq", value = 2),
                    operator = '*'
                ),
                Equation(
                    name = "drzm",
                    number1 = NumberValue(name = "hmdt", value = 32),
                    number2 = NumberValue(name = "zczc", value = 2),
                    operator = '-'
                ),
            )

        }

        @Test
        fun isRootFound_false() {
            // arrange
            val numberValues = listOf(NumberValue("x", 1), NumberValue("y", 2))

            // act
            val rootFound = monkeyMath.isRootFound(numberValues)

            // assert
            assertThat(rootFound).isFalse
        }

        @Test
        fun isRootFound_true() {
            // arrange
            val numberValues = listOf(NumberValue("x", 1), NumberValue("y", 2), NumberValue("root", 10))

            // act
            val rootFound = monkeyMath.isRootFound(numberValues)

            // assert
            assertThat(rootFound).isTrue
        }
    }

}