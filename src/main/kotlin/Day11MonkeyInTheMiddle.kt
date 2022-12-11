import kotlin.math.floor

class MonkeyInTheMiddle(val monkeys: List<Monkey>) {

    fun playRound() {
        for (monkey in monkeys) {
            while (monkey.items.isNotEmpty()) {
                val item = monkey.inspect()
                val worryLevel = monkey.normalizedWorryLevel(item)
                val throwTo = monkey.throwToMonkey(worryLevel)
                monkeys[throwTo].catchItem(worryLevel)
            }
        }
    }
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
        val normalizedWorryLevel =floor(newWorryLevel.toDouble() / 3.0f).toInt()
        return normalizedWorryLevel

    }

    fun throwToMonkey(worryLevel: Int): Int {
        if (worryLevel % divisor == 0) return throwTo.first
        return throwTo.second
    }

    fun inspect(): Int {
        inspectCount++
        return items.removeFirst()
    }

    fun catchItem(item: Int) {
        items.add(item)
    }



}
