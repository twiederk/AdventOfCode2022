import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.readLines

class RopeBridge {

    var worm = mutableListOf(
        Pair(0, 0),
        Pair(0, 0),
        Pair(0, 0),
        Pair(0, 0),
        Pair(0, 0),
        Pair(0, 0),
        Pair(0, 0),
        Pair(0, 0),
        Pair(0, 0),
        Pair(0, 0)
    )

    val fieldsVisitByTail = mutableSetOf<Pair<Int, Int>>()

    fun loadData(path: Path): List<String> = path.readLines()

    fun parseRopeCommand(rawCommand: String): RopeCommand {

        val (direction, moves) = rawCommand.split(" ")
        return RopeCommand(direction, moves.toInt())
    }

    fun parseRopeCommands(rawData: List<String>): List<RopeCommand> {
        val ropeCommands = mutableListOf<RopeCommand>()
        for (data in rawData) {
            ropeCommands.add(parseRopeCommand(data))
        }
        return ropeCommands
    }

    fun moveHead(direction: String) {
        when (direction) {
            "R" -> worm[0] = worm[0].copy(first = worm[0].first + 1)
            "L" -> worm[0] = worm[0].copy(first = worm[0].first - 1)
            "U" -> worm[0] = worm[0].copy(second = worm[0].second + 1)
            "D" -> worm[0] = worm[0].copy(second = worm[0].second - 1)
        }
    }

    fun executeRopeCommand(ropeCommand: RopeCommand) {
        for (move in 1..ropeCommand.moves) {
            moveHead(ropeCommand.direction)
            for (i in 1 until worm.size) {
                if (!isAdjacent(i)) {
                    moveTail(i)
                }
            }
            fieldsVisitByTail.add(worm[8].copy())
        }
    }

    fun isAdjacent(i: Int): Boolean {
        return worm[i].first in worm[i - 1].first - 1..worm[i - 1].first + 1
                && worm[i].second in worm[i - 1].second - 1..worm[i - 1].second + 1
    }

    fun moveTail(i: Int) {
        // left
        if (worm[i].first + 2 == worm[i - 1].first) {
            worm[i] = if (worm[i].second == worm[i - 1].second) {
                worm[i].copy(first = worm[i].first + 1)
            } else {
                worm[i].copy(first = worm[i].first + 1, second = worm[i - 1].second)
            }
            return
        }

        // right
        if (worm[i].first - 2 == worm[i - 1].first) {
            worm[i] = if (worm[i].second == worm[i - 1].second) {
                worm[i].copy(first = worm[i].first - 1)
            } else {
                worm[i].copy(first = worm[i].first - 1, second = worm[i - 1].second)
            }
            return
        }

        // down
        if (worm[i].second - 2 == worm[i - 1].second) {
            worm[i] = if (worm[i].first == worm[i - 1].first) {
                worm[i].copy(second = worm[i].second - 1)
            } else {
                worm[i].copy(second = worm[i].second - 1, first = worm[i - 1].first)
            }
            return
        }

        // up
        if (worm[i].second + 2 == worm[i - 1].second) {
            worm[i] = if (worm[i].first == worm[i - 1].first) {
                worm[i].copy(second = worm[i].second + 1)
            } else {
                worm[i].copy(second = worm[i].second + 1, first = worm[i - 1].first)
            }
            return
        }
    }

    fun executeRopeCommands(ropeCommands: List<RopeCommand>) {
        for (ropeCommand in ropeCommands) {
            executeRopeCommand(ropeCommand)
        }
    }

    fun countVisitByTail(): Int {
        return fieldsVisitByTail.size
    }
}


class RopeCommand(
    val direction: String,
    val moves: Int
)

fun main() {
    val ropeBridge = RopeBridge()
    val rawData = ropeBridge.loadData(Path("src", "main", "resources", "Day09_Part1_InputData.txt"))
    val ropeCommands = ropeBridge.parseRopeCommands(rawData)

    ropeBridge.executeRopeCommands(ropeCommands)
    println("countVisitByTail = ${ropeBridge.countVisitByTail()}")

}
