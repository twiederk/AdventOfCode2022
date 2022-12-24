import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.readLines

class Day01 {

    fun loadData(path: Path): List<Int> {
        return path.readLines().map { parseData(it) }
    }

    private fun parseData(data: String): Int {
        if (data.isBlank()) return 0
        return data.toInt()
    }

    fun createElves(items: List<Int>): List<Elf> {
        var currentElf = Elf()
        val elves = mutableListOf(currentElf)
        for (item in items) {
            if (item == 0) {
                currentElf = Elf()
                elves.add(currentElf)
            } else {
                currentElf.add(item)
            }
        }
        return elves.toList()
    }

    fun maxCalories(elves: List<Elf>): Int = elves.maxOf { it.sumUpCalories() }

    fun top3Calories(elves: List<Elf>): Int = elves.sortedBy { it.sumUpCalories() }.takeLast(3).sumOf { it.sumUpCalories() }
}

class Elf {
    val backpack = mutableListOf<Int>()

    fun add(food: Int) {
        backpack.add(food)
    }

    fun sumUpCalories(): Int = backpack.sum()

    override fun toString(): String = sumUpCalories().toString()

}

fun main() {

    val path = Path("src", "main", "resources", "Day01_InputData.txt")
    val items = Day01().loadData(path)
    val elves = Day01().createElves(items)
    part1(elves)
    part2(elves)
}

private fun part1(elves: List<Elf>) {

    val maxCalories = Day01().maxCalories(elves)
    println("part 1 maxCalories = $maxCalories")
}

private fun part2(elves: List<Elf>) {
    val top3Calories = Day01().top3Calories(elves)
    println("part 2 top3Calories = $top3Calories")
}
