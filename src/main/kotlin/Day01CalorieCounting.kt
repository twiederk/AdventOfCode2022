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
}


fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

}
