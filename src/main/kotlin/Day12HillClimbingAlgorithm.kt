import java.nio.file.Path
import java.util.*
import kotlin.io.path.readLines

class HillClimbingAlgorithm {

    fun loadData(path: Path): List<List<Char>> {
        val rawData = path.readLines()
        return rawData.map { line -> line.map { it } }
    }

    val openList = PriorityQueue<Node>()
    val closedList = mutableSetOf<Node>()


    fun aStar(grid: List<List<Char>>): Int {

        val start = findStartNode(grid)
        val end = findEndNode(grid)

        // Initialisierung der Open List, die Closed List ist noch leer
        // (die Priorität bzw. der f-Wert des Startknotens ist unerheblich)
        // openlist.enqueue(startknoten, 0)
        openList.add(start)
        // diese Schleife wird durchlaufen bis entweder
        // - die optimale Lösung gefunden wurde oder
        // - feststeht, dass keine Lösung existiert
        do {
            // Knoten mit dem geringsten f-Wert aus der Open List entfernen
            val currentNode = openList.remove()
            // currentNode : = openlist.removeMin()
            // Wurde das Ziel gefunden?

            /*
            if currentNode == zielknoten then
                    return PathFound
            // Der aktuelle Knoten soll durch nachfolgende Funktionen
            // nicht weiter untersucht werden, damit keine Zyklen entstehen
            closedlist.add(currentNode)
            // Wenn das Ziel noch nicht gefunden wurde: Nachfolgeknoten
            // des aktuellen Knotens auf die Open List setzen
            expandNode(currentNode)
            // until openlist.isEmpty()
            */
        } while (openList.isNotEmpty())
        // die Open List ist leer, es existiert kein Pfad zum Ziel

//                return NoPathFound
        return 0
    }

    fun findStartNode(grid: List<List<Char>>): Node {
        return findNode(grid, 'S')
    }

    fun findEndNode(grid: List<List<Char>>): Node {
        return findNode(grid, 'E')
    }

    private fun findNode(grid: List<List<Char>>, c: Char): Node {
        for (row in grid.indices) {
            for (col in grid[0].indices) {
                if (grid[row][col] == c) return Node(col, row, 0)
            }
        }
        throw IllegalArgumentException("Can't find start node")
    }


}

data class Node(
    val col: Int = 0,
    val row: Int = 0,
    val f: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int = f.compareTo(other.f)
}