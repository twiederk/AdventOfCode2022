import kotlin.math.floor

class MonkeyInTheMiddle {
}

class Monkey(
    val items : MutableList<Int>,
    private val operation : (Int) -> Int,
    private val divisor : Int,
    private val throwTo: Pair<Int,Int>
) {
    var inspectCount = 0

    fun newWorryLevel(item: Int): Int = operation(item)

    fun normalizedWorryLevel(item: Int): Int {
        val newWorryLevel = newWorryLevel(item)
        return floor(newWorryLevel.toDouble() / 3.0f).toInt()
    }

    fun throwToMonkey(item: Int): Int {
        if (item / divisor == 0) return throwTo.first
        return throwTo.second
    }

    fun inspect(): Int {
        inspectCount++
        return items.removeFirst()
    }

}
