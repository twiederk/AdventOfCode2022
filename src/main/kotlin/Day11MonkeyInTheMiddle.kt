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

    fun playRounds(rounds: Int): Int {
        for (r in 1..rounds) playRound()
        return monkeyBusiness()
    }

    private fun monkeyBusiness(): Int {
        val sortedInspectCount = monkeys.map { it.inspectCount }.sorted().reversed()
        println(sortedInspectCount)
        return sortedInspectCount[0] * sortedInspectCount[1]
    }
}

class Monkey(
    val items: MutableList<Int>,
    private val operation: (Int) -> Int,
    private val divisor: Int,
    private val throwTo: Pair<Int, Int>
) {
    var inspectCount = 0

    fun newWorryLevel(item: Int): Int = operation(item)

    fun normalizedWorryLevel(item: Int): Int {
        val newWorryLevel = newWorryLevel(item)
        val normalizedWorryLevel = floor(newWorryLevel.toDouble() / 3.0f).toInt()
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

fun main() {

    val monkey0 = Monkey(
        items = mutableListOf(57, 18),
        operation = { it * 19 },
        divisor = 7,
        throwTo = Pair(2, 3)
    )

    val monkey1 = Monkey(
        items = mutableListOf(66, 52, 59, 79, 94, 73),
        operation = { it + 1 },
        divisor = 19,
        throwTo = Pair(4, 6)
    )

    val monkey2 = Monkey(
        items = mutableListOf(80),
        operation = { it +6 },
        divisor = 5,
        throwTo = Pair(7, 5)
    )

    val monkey3 = Monkey(
        items = mutableListOf(82, 81, 68, 66, 71, 83, 75, 97),
        operation = { it + 5 },
        divisor = 11,
        throwTo = Pair(5, 2)
    )

    val monkey4 = Monkey(
        items = mutableListOf(55, 52, 67, 70, 69, 94, 90),
        operation = { it * it },
        divisor = 17,
        throwTo = Pair(0, 3)
    )

    val monkey5 = Monkey(
        items = mutableListOf(69, 85, 89, 91),
        operation = { it + 7 },
        divisor = 13,
        throwTo = Pair(1, 7)
    )

    val monkey6 = Monkey(
        items = mutableListOf(75, 53, 73, 52, 75),
        operation = { it * 7 },
        divisor = 2,
        throwTo = Pair(0, 4)
    )

    val monkey7 = Monkey(
        items = mutableListOf(94, 60, 79),
        operation = { it + 2 },
        divisor = 3,
        throwTo = Pair(1, 6)
    )

    val monkeys = listOf(monkey0, monkey1, monkey2, monkey3, monkey4, monkey5, monkey6, monkey7)
    val monkeyBusiness = MonkeyInTheMiddle(monkeys).playRounds(20)

    println("monkeyBusiness = $monkeyBusiness")
}