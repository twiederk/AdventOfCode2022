import java.nio.file.Path
import java.util.*
import kotlin.io.path.readLines
import kotlin.math.abs

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
            if (closedList.contains(successor)) {
                continue
            }

            // g-Wert für den neuen Weg berechnen: g-Wert des Vorgängers plus
            // die Kosten der gerade benutzten Kante
            val tentative_g = currentNode.g + 1
//        tentative_g = g(currentNode) + c(currentNode, successor)

            // wenn der Nachfolgeknoten bereits auf der Open List ist,
            // aber der neue Weg nicht besser ist als der alte – tue nichts
            if (openList.contains(successor) && tentative_g >= successor.g) {
                continue
            }
//        if openlist.contains(successor) and tentative_g >= g(successor) then
//                continue

            // Vorgängerzeiger setzen und g Wert merken oder anpassen
            successor.parent = currentNode
//        successor.predecessor := currentNode
            successor.g = tentative_g
//        g(successor) = tentative_g

            // f-Wert des Knotens in der Open List aktualisieren
            // bzw. Knoten mit f-Wert in die Open List einfügen
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

    fun getNeighbors(grid: List<List<Char>>, currentNode: Node): List<Node> {
        val neighbors = mutableListOf<Node>()
        if (currentNode.row - 1 in grid.indices) {
            val neighborNode = Node(currentNode.row - 1, currentNode.col)
            if (isClimbable(grid, currentNode, neighborNode)) neighbors.add(neighborNode)
        }
        if (currentNode.col + 1 in grid.indices) {
            val neighborNode = Node(currentNode.row, currentNode.col + 1)
            if (isClimbable(grid, currentNode, neighborNode)) neighbors.add(neighborNode)
        }
        if (currentNode.row + 1 in grid.indices) {
            val neighborNode = Node(currentNode.row + 1, currentNode.col)
            if (isClimbable(grid, currentNode, neighborNode)) neighbors.add(neighborNode)
        }
        if (currentNode.col - 1 in grid.indices) {
            val neighborNode = Node(currentNode.row, currentNode.col - 1)
            if (isClimbable(grid, currentNode, neighborNode)) neighbors.add(neighborNode)
        }
        return neighbors
    }

    fun isClimbable(grid: List<List<Char>>, currentNode: Node, nextNode: Node): Boolean {
        val nextNodeChar = normalizeChar(grid[nextNode.row][nextNode.col])
        val currentNodeChar = normalizeChar(grid[currentNode.row][currentNode.col])
        return nextNodeChar - currentNodeChar <= 1
    }

    private fun normalizeChar(input: Char) = when (input) {
        'E' -> 'z'
        'S' -> 'a'
        else -> input
    }

    fun distance(src: Node, dest: Node): Int {
        return abs(src.col - dest.col) + abs(src.row - dest.row)
    }
}


data class Node(
    val row: Int = 0,
    val col: Int = 0,
    var f: Int = 0,
    var parent: Node? = null
) : Comparable<Node> {

    var g: Int = 0
    override fun compareTo(other: Node): Int = f.compareTo(other.f)
}