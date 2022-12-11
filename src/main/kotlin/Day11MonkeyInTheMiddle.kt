class MonkeyInTheMiddle {
}

class Monkey(
    val items : MutableList<Int>,
    private val operation : (Int) -> Int
) {
    fun calculateWorryLevel(item: Int): Int = operation(item)

}
