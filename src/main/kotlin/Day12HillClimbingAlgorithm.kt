import java.lang.IllegalArgumentException
import java.nio.file.Path
import kotlin.io.path.readLines

class HillClimbingAlgorithm {

    fun loadData(path: Path): List<List<Char>> {
        val rawData = path.readLines()
        return rawData.map { line -> line.map { it } }
    }

    val openList = mutableListOf<Node>()
    val closedList = mutableSetOf<Node>()

    fun aStar(grid: List<List<Char>>): Int {

        val start = findStartNode(grid)

        // Initialisierung der Open List, die Closed List ist noch leer
        // (die Priorität bzw. der f-Wert des Startknotens ist unerheblich)
//                openlist.enqueue(startknoten, 0)
        openList.add(start)
        /*
                // diese Schleife wird durchlaufen bis entweder
                // - die optimale Lösung gefunden wurde oder
                // - feststeht, dass keine Lösung existiert
                repeat
                // Knoten mit dem geringsten f-Wert aus der Open List entfernen
                currentNode := openlist.removeMin()
                // Wurde das Ziel gefunden?
                if currentNode == zielknoten then
                        return PathFound
                // Der aktuelle Knoten soll durch nachfolgende Funktionen
                // nicht weiter untersucht werden, damit keine Zyklen entstehen
                closedlist.add(currentNode)
                // Wenn das Ziel noch nicht gefunden wurde: Nachfolgeknoten
                // des aktuellen Knotens auf die Open List setzen
                expandNode(currentNode)
                until openlist.isEmpty()
                // die Open List ist leer, es existiert kein Pfad zum Ziel
                return NoPathFound
        */
        return 0
    }

    fun findStartNode(grid: List<List<Char>>): Node {
        for (col in grid[0].indices) {
            for (row in grid.indices) {
                if (grid[col][row] == 'S') return Node(col, row, 0)
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