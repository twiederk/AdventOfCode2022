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
        val elves = mutableListOf(Elf())
        for (item in items) {
            if (item == 0) {
                elves.add(Elf())
            }
        }
        return elves.toList()
    }
}

class Elf {
    val backpack = mutableListOf<Int>()

    fun add(food: Int) {
        backpack.add(food)
    }

}


fun main() {

}
