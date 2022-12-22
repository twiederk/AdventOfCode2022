class MonkeyMath(val equations: MutableList<Equation>) {



    fun insertKnownNumbersInEquations(numberValuesList: List<NumberValue>) {
        val numberValuesMap = numberValuesList.associate { it.name to it.value }
        for (equation in equations) {
            equation.number1.value = numberValuesMap.getOrDefault(equation.number1.name, -1)
            equation.number2.value = numberValuesMap.getOrDefault(equation.number2.name, -1)
        }
    }

    companion object {
        fun loadData(fileName: String): List<String> = Resources.resourceAsListOfString(fileName)

        fun parseData(rawData: List<String>): Pair<List<NumberValue>, List<Equation>> {
            val first = rawData.filter { it[it.lastIndex].isDigit() }.map { parseNumberValue(it) }
            val second = rawData.filter { it[it.lastIndex].isLetter() }.map { parseEquation(it)  }
            return Pair(first, second)
        }

        fun parseNumberValue(rawData: String): NumberValue {
            val split = rawData.split(": ")
            return NumberValue(name = split[0], value = split[1].toInt())
        }

        fun parseEquation(rawData: String): Equation {
            val split = rawData.split(": ")
            return Equation(
                name = split[0],
                number1 = NumberValue(name = split[1].substring(0..3), value = -1),
                number2 = NumberValue(name = split[1].substring(7..10), value = -1),
                operator = split[1][5]
            )
        }
    }

}

data class NumberValue(
    val name: String,
    var value: Int,
)

data class Equation(
    val name: String,
    val number1: NumberValue,
    val number2: NumberValue,
    val operator: Char
)
