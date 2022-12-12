import java.nio.file.Path
import kotlin.io.path.readLines

class HillClimbingAlgorithm {

    fun loadData(path: Path): List<List<Char>> {
        val rawData = path.readLines()
        return rawData.map { line -> line.map { it } }
    }


}

data class Node(
    val row: Int = 0,
    val col: Int = 0,
    val f: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int = f.compareTo(other.f)
}