import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.readLines

class RopeBridge {

    var worm = mutableListOf(Pair(0, 0), Pair(0, 0))

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
            if (!isAdjacent()) {
                moveTail()
            }
            fieldsVisitByTail.add(worm[1].copy())
        }
    }

    fun isAdjacent(): Boolean {
        return worm[1].first in worm[0].first - 1..worm[0].first + 1
                && worm[1].second in worm[0].second - 1..worm[0].second + 1
    }

    fun moveTail() {
        if (worm[1].first + 2 == worm[0].first) {
            worm[1] = if (worm[1].second == worm[0].second) {
                worm[1].copy(first = worm[1].first + 1)
            } else {
                worm[1].copy(first = worm[1].first + 1, second = worm[0].second)
            }
            return
        }

        if (worm[1].first - 2 == worm[0].first) {
            worm[1] = if (worm[1].second == worm[0].second) {
                worm[1].copy(first = worm[1].first - 1)
            } else {
                worm[1].copy(first = worm[1].first - 1, second = worm[0].second)
            }
            return
        }

        if (worm[1].second - 2 == worm[0].second) {
            worm[1] = if (worm[1].first == worm[0].first) {
                worm[1].copy(second = worm[1].second - 1)
            } else {
                worm[1].copy(second = worm[1].second - 1, first = worm[0].first)
            }
            return
        }

        if (worm[1].second + 2 == worm[0].second) {
            worm[1] = if (worm[1].first == worm[0].first) {
                worm[1].copy(second = worm[1].second + 1)
            } else {
                worm[1].copy(second = worm[1].second + 1, first = worm[0].first)
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
