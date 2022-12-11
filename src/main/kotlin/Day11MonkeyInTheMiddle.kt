import kotlin.math.floor

class MonkeyInTheMiddle(val monkeys: List<Monkey>) {

    fun playRound() {
        for (monkey in monkeys) {
            println("####################################################")
            while (monkey.items.isNotEmpty()) {
                val item = monkey.inspect()
                println("inspects an item with a worry level of $item")
                val worryLevel = monkey.normalizedWorryLevel(item)
                val throwTo = monkey.throwToMonkey(worryLevel)
                println("  Item with worry level $worryLevel is thrown to monkey $throwTo")
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
        println("  Worry level is multiplied $newWorryLevel.")
        val normalizedWorryLevel =floor(newWorryLevel.toDouble() / 3.0f).toInt()
        println("  Worry level is divided by 3 to $normalizedWorryLevel")
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
