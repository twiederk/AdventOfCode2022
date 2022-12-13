import java.nio.file.Path
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.abs

class HillClimbingAlgorithm {

    fun loadData(path: Path): List<List<Char>> {
        val rawData = path.readLines()
        return rawData.map { line -> line.map { it } }
    }

    val openList = PriorityQueue<Node>()
    val closedList = mutableSetOf<Node>()

    fun aStar(grid: List<List<Char>>, maxRounds: Int = Int.MAX_VALUE, debug: Boolean = false): List<Node> {

        val start = findStartNode(grid)
        val end = findEndNode(grid)

        // Initialisierung der Open List, die Closed List ist noch leer
        // (die Priorität bzw. der f-Wert des Startknotens ist unerheblich)
        openList.add(start)
        // diese Schleife wird durchlaufen bis entweder
        // - die optimale Lösung gefunden wurde oder
        // - feststeht, dass keine Lösung existiert
        var round = 0
        do {
            // Knoten mit dem geringsten f-Wert aus der Open List entfernen
            val currentNode = openList.remove()
            debug("######## round $round #############", debug)
            debug("currentNode = $currentNode", debug)

            // Wurde das Ziel gefunden?
            if (currentNode == end) {
                closedList.add(currentNode)
                return getPath(end)
            }

            // Der aktuelle Knoten soll durch nachfolgende Funktionen
            // nicht weiter untersucht werden, damit keine Zyklen entstehen
            closedList.add(currentNode)

            // Wenn das Ziel noch nicht gefunden wurde: Nachfolgeknoten
            // des aktuellen Knotens auf die Open List setzen
            expandNode(grid, currentNode, end)

            round++
            debug(render(grid), debug)

        } while (openList.isNotEmpty() && round < maxRounds)

        // die Open List ist leer, es existiert kein Pfad zum Ziel
        return emptyList()
    }

    private fun debug(message: String, debug: Boolean) {
        if (debug) {
            println(message)
        }
    }

    // überprüft alle Nachfolgeknoten und fügt sie der Open List hinzu, wenn entweder
    // - der Nachfolgeknoten zum ersten Mal gefunden wird, oder
    // - ein besserer Weg zu diesem Knoten gefunden wird
    fun expandNode(grid: List<List<Char>>, currentNode: Node, end: Node) {
        for (successor in getNeighbors(grid, currentNode)) {
            if (closedList.contains(successor)) {
                continue
            }

            // g-Wert für den neuen Weg berechnen: g-Wert des Vorgängers plus
            // die Kosten der gerade benutzten Kante
            val tentativeG = currentNode.g + 1

            // wenn der Nachfolgeknoten bereits auf der Open List ist,
            // aber der neue Weg nicht besser ist als der alte – tue nichts
            if (openList.contains(successor) && tentativeG >= successor.g) {
                continue
            }

            // Vorgängerzeiger setzen und g Wert merken oder anpassen
            successor.parent = currentNode
            successor.g = tentativeG

            // f-Wert des Knotens in der Open List aktualisieren
            // bzw. Knoten mit f-Wert in die Open List einfügen
            val f = tentativeG + distance(successor, end)

            successor.f = f
            if (!openList.contains(successor)) {
                openList.add(successor)
            }
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
                if (grid[row][col] == c) return Node(row, col)
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
        if (currentNode.col + 1 in grid[0].indices) {
            val neighborNode = Node(currentNode.row, currentNode.col + 1)
            if (isClimbable(grid, currentNode, neighborNode)) neighbors.add(neighborNode)
        }
        if (currentNode.row + 1 in grid.indices) {
            val neighborNode = Node(currentNode.row + 1, currentNode.col)
            if (isClimbable(grid, currentNode, neighborNode)) neighbors.add(neighborNode)
        }
        if (currentNode.col - 1 in grid[0].indices) {
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

    fun render(grid: List<List<Char>>): String {
        var display = ""
        for (row in grid.indices) {
            for (col in grid[0].indices) {
                display += renderNode(row, col)
            }
            if (row < grid.lastIndex) display += "\n"
        }
        return display
    }

    fun renderNode(row: Int, col: Int): Char {
        if (openList.contains(Node(row, col))) return 'O'
        if (closedList.contains(Node(row, col))) return 'C'
        return '.'
    }

    fun getPath(end: Node): List<Node> {
        var currentNode: Node? = closedList.first { it == end }
        val path = mutableListOf<Node>()
        while (currentNode != null) {
            path.add(currentNode)
            currentNode = currentNode.parent
        }
        return path.reversed()
    }

    fun renderPath(grid: List<List<Char>>, path: List<Node>): String {
        val char2d = Array(grid.size) { CharArray(grid[0].size) { '.' } }

        for (node in path) {
            char2d[node.row][node.col] = 'X'
        }

        val display = StringBuffer()
        for (i in char2d.indices) {
            display.append(char2d[i]).append('\n')
        }
        return display.toString()

    }
}


data class Node(
    val row: Int = 0,
    val col: Int = 0
) : Comparable<Node> {
    var f: Int = 0
    var g: Int = 0
    var parent: Node? = null
    override fun compareTo(other: Node): Int = f.compareTo(other.f)
}

fun main() {
    val hillClimbingAlgorithm = HillClimbingAlgorithm()
    val grid = hillClimbingAlgorithm.loadData(Path("src", "main", "resources", "Day12_Part1_InputData.txt"))

    val result = hillClimbingAlgorithm.aStar(grid)

    println("result.size - 1 = ${result.size - 1}")
}
