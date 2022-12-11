import kotlin.math.floor

class MonkeyInTheMiddle {
}

class Monkey(
    val items : MutableList<Int>,
    private val operation : (Int) -> Int
) {
    fun newWorryLevel(item: Int): Int = operation(item)

    fun normalizedWorryLevel(item: Int): Int {
        val newWorryLevel = newWorryLevel(item)
        return floor(newWorryLevel.toDouble() / 3.0f).toInt()
    }

}
