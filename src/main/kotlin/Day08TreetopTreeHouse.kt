import java.nio.file.Path
import kotlin.io.path.Path
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

    fun isVisibleFromTop(row: Int, column: Int): Boolean {
        val height = grid[row][column]
        for (r in 0 until row) {
            if (grid[r][column] >= height) return false
        }
        return true
    }

    fun isVisibleFromBottom(row: Int, column: Int): Boolean {
        val height = grid[row][column]
        for (r in row+1 until grid.size) {
            if (grid[r][column] >= height) return false
        }
        return true
    }

    fun isVisible(row: Int, column: Int): Boolean {
        return isVisibleFromLeft(row, column)
                || isVisibleFromRight(row, column)
                || isVisibleFromTop(row, column)
                || isVisibleFromBottom(row, column)
    }

    fun countVisibleTrees(): Int {
        var countVisibleTrees = 0
        for (row in grid.indices) {
            for (col in 0 until grid[0].size) {
                if (isVisible(row, col)) countVisibleTrees++
            }
        }
        return countVisibleTrees
    }

}

fun main() {
    val rawData = TreetopTreeHouse().loadData(Path("src", "main", "resources", "Day08_Part1_InputData.txt"))
    val grid = TreetopTreeHouse().convertToInts(rawData)

    // act
    val count = TreetopTreeHouse(grid).countVisibleTrees()
    println("count = $count")

}