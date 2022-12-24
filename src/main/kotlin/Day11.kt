import kotlin.math.floor

typealias WorryLevel = Long

class MonkeyInTheMiddle(
    private val monkeys: List<Monkey>
) {

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

    fun playRounds(rounds: Int): Long {
        for (r in 1..rounds) playRound()
        return monkeyBusiness()
    }

    private fun monkeyBusiness(): Long {
        val sortedInspectCount = monkeys.map { it.inspectCount }.sortedDescending().take(2)
        return sortedInspectCount[0] * sortedInspectCount[1]
    }
}

data class Monkey(
    val items: MutableList<Long>,
    private val operation: (WorryLevel) -> WorryLevel,
    private val divisor: Long,
    private val throwTo: Pair<Int, Int>,
    // Lowest Common Multiplier (lcm) is needed for part 2 to keep the WorryLevel small
    // https://www.youtube.com/watch?v=1eBSyPe_9j0&t=3952s
    // https://www.calculatorsoup.com/calculators/math/lcm.php
    val normalizer: (WorryLevel) -> WorryLevel
) {
    var inspectCount = 0L

    fun newWorryLevel(item: WorryLevel): WorryLevel = operation(item)

    fun normalizedWorryLevel(item: WorryLevel): WorryLevel {
        val newWorryLevel = newWorryLevel(item)
        return normalizer(newWorryLevel)
    }

    fun throwToMonkey(worryLevel: Long): Int {
        if (worryLevel % divisor == 0L) return throwTo.first
        return throwTo.second
    }

    fun inspect(): Long {
        inspectCount++
        return items.removeFirst()
    }

    fun catchItem(item: Long) {
        items.add(item)
    }


}

fun main() {

    val monkey0 = Monkey(
        items = mutableListOf(57, 58),
        operation = { it * 19 },
        divisor = 7,
        throwTo = Pair(2, 3),
        normalizer = { floor(it.toDouble() % 9_699_690).toLong() }
    )

    val monkey1 = Monkey(
        items = mutableListOf(66, 52, 59, 79, 94, 73),
        operation = { it + 1 },
        divisor = 19,
        throwTo = Pair(4, 6),
        normalizer = { floor(it.toDouble() % 9_699_690).toLong() }
    )

    val monkey2 = Monkey(
        items = mutableListOf(80),
        operation = { it + 6 },
        divisor = 5,
        throwTo = Pair(7, 5),
        normalizer = { floor(it.toDouble() % 9_699_690).toLong() }
    )

    val monkey3 = Monkey(
        items = mutableListOf(82, 81, 68, 66, 71, 83, 75, 97),
        operation = { it + 5 },
        divisor = 11,
        throwTo = Pair(5, 2),
        normalizer = { floor(it.toDouble() % 9_699_690).toLong() }
    )

    val monkey4 = Monkey(
        items = mutableListOf(55, 52, 67, 70, 69, 94, 90),
        operation = { it * it },
        divisor = 17,
        throwTo = Pair(0, 3),
        normalizer = { floor(it.toDouble() % 9_699_690).toLong() }
    )

    val monkey5 = Monkey(
        items = mutableListOf(69, 85, 89, 91),
        operation = { it + 7 },
        divisor = 13,
        throwTo = Pair(1, 7),
        normalizer = { floor(it.toDouble() % 9_699_690).toLong() }
    )

    val monkey6 = Monkey(
        items = mutableListOf(75, 53, 73, 52, 75),
        operation = { it * 7 },
        divisor = 2,
        throwTo = Pair(0, 4),
        normalizer = { floor(it.toDouble() % 9_699_690).toLong() }
    )

    val monkey7 = Monkey(
        items = mutableListOf(94, 60, 79),
        operation = { it + 2 },
        divisor = 3,
        throwTo = Pair(1, 6),
        normalizer = { floor(it.toDouble() % 9_699_690).toLong() }
    )

    val monkeys = listOf(monkey0, monkey1, monkey2, monkey3, monkey4, monkey5, monkey6, monkey7)
    val monkeyInTheMiddlePart1 = MonkeyInTheMiddle(monkeys)
    val monkeyBusiness = monkeyInTheMiddlePart1.playRounds(10_000)

    println("monkeyBusiness = $monkeyBusiness")
}