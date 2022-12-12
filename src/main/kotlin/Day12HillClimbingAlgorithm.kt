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
        openList.add(start)
        // diese Schleife wird durchlaufen bis entweder
        // - die optimale Lösung gefunden wurde oder
        // - feststeht, dass keine Lösung existiert
        do {
            // Knoten mit dem geringsten f-Wert aus der Open List entfernen
            val currentNode = openList.remove()

            // Wurde das Ziel gefunden?
            if (currentNode == end) {
                return closedList.size
            }

            // Der aktuelle Knoten soll durch nachfolgende Funktionen
            // nicht weiter untersucht werden, damit keine Zyklen entstehen
            closedList.add(currentNode)

            // Wenn das Ziel noch nicht gefunden wurde: Nachfolgeknoten
            // des aktuellen Knotens auf die Open List setzen
            expandNode(grid, currentNode)
        } while (openList.isNotEmpty())

        // die Open List ist leer, es existiert kein Pfad zum Ziel
        return 0
    }

    // überprüft alle Nachfolgeknoten und fügt sie der Open List hinzu, wenn entweder
    // - der Nachfolgeknoten zum ersten Mal gefunden wird, oder
    // - ein besserer Weg zu diesem Knoten gefunden wird
    fun expandNode(grid: List<List<Char>>, currentNode: Node) {
        for (successor in getNeighbors(grid, currentNode)) {
//        foreach successor of currentNode
//                // wenn der Nachfolgeknoten bereits auf der Closed List ist – tue nichts
//                if closedlist.contains(successor) then
//                        continue
//        // g-Wert für den neuen Weg berechnen: g-Wert des Vorgängers plus
//        // die Kosten der gerade benutzten Kante
//        tentative_g = g(currentNode) + c(currentNode, successor)
//        // wenn der Nachfolgeknoten bereits auf der Open List ist,
//        // aber der neue Weg nicht besser ist als der alte – tue nichts
//        if openlist.contains(successor) and tentative_g >= g(successor) then
//                continue
//        // Vorgängerzeiger setzen und g Wert merken oder anpassen
//        successor.predecessor := currentNode
//        g(successor) = tentative_g
//        // f-Wert des Knotens in der Open List aktualisieren
//        // bzw. Knoten mit f-Wert in die Open List einfügen
//        f := tentative_g + h(successor)
//        if openlist.contains(successor) then
//                openlist.updateKey(successor, f)
//        else
//            openlist.enqueue(successor, f)
//        end
        }
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

    fun getNeighbors(grid: List<List<Char>>, node: Node): List<Node> {
        val neighbors = mutableListOf<Node>()
        if (node.row - 1 in grid.indices) neighbors.add(Node(node.col, node.row - 1))
        if (node.col + 1 in grid.indices) neighbors.add(Node(node.col + 1, node.row))
        if (node.row + 1 in grid.indices) neighbors.add(Node(node.col, node.row + 1))
        if (node.col - 1 in grid.indices) neighbors.add(Node(node.col - 1, node.row))
        return neighbors
    }


}

data class Node(
    val col: Int = 0,
    val row: Int = 0,
    val f: Int = 0
) : Comparable<Node> {
    override fun compareTo(other: Node): Int = f.compareTo(other.f)
}