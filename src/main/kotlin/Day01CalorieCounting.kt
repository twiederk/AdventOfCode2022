import java.nio.file.Path
import kotlin.io.path.readLines

class CalorieCounting {

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
}

class Elf {
    val backpack = mutableListOf<Int>()

    fun add(food: Int) {
        backpack.add(food)
    }

    fun sumUpCalories(): Int = backpack.sum()

}

fun main() {

}
