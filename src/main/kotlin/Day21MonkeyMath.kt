class MonkeyMath {
    companion object {
        fun loadData(fileName: String): List<String> = Resources.resourceAsListOfString(fileName)

        fun parseData(rawData: List<String>): Pair<List<NumberValue>, List<Equation>> {
            val first = rawData.filter { it[it.lastIndex].isDigit() }.map { NumberValue("a", 1) }
            val second = rawData.filter { it[it.lastIndex].isLetter() }.map { Equation(NumberValue("a", 1), NumberValue("a", 1), OperatorSign.PLUS)  }
            return Pair(first, second)
        }
    }

}

data class NumberValue(
    val name: String,
    val value: Int,
)

data class Equation(
    val number1: NumberValue,
    val number2: NumberValue,
    val operator: OperatorSign
)

enum class OperatorSign {
    PLUS, MINUS, MULITPY, DIVIDE
}

