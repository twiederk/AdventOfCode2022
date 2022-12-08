import java.nio.file.Path
import kotlin.io.path.readLines

class TreetopTreeHouse(private val grid: List<List<Int>> = listOf()) {

    fun loadData(path: Path): List<String> = path.readLines()

    fun convertToInts(input: List<String>): List<List<Int>> {
        return input.map { s -> s.map { it.digitToInt() } }
    }

    fun isVisibleFromLeft(row: Int, column: Int): Boolean {
        val height = grid[row][column]
        for (c in 0 until column) {
            if (grid[row][c] >= height) return false
        }
        return true
    }

    fun isVisibleFromRight(row: Int, column: Int): Boolean {
        val height = grid[row][column]
        for (c in column + 1 until grid[0].size) {
            if (grid[row][c] >= height) return false
        }
        return true
    }

}