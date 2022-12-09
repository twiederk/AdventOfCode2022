import java.nio.file.Path
import kotlin.io.path.readLines

class RopeBridge {


    var head = Pair(0, 0)
    var tail = Pair(0, 0)

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
            "R" -> head = head.copy(first = head.first + 1)
            "L" -> head = head.copy(first = head.first - 1)
            "U" -> head = head.copy(second = head.second + 1)
            "D" -> head = head.copy(second = head.second - 1)
        }
    }

    fun executeRopeCommand(ropeCommand: RopeCommand) {
        for (move in 1..ropeCommand.moves) {
            moveHead(ropeCommand.direction)
            if (!isAdjacent()) {
                moveTail()
            }
        }
    }

    fun isAdjacent(): Boolean {
        return tail.first in head.first - 1..head.first + 1
                && tail.second in head.second - 1..head.second + 1
    }

    fun moveTail() {
        if (tail.first + 2 == head.first) {
            tail = if (tail.second == head.second) {
                tail.copy(first = tail.first + 1)
            } else {
                tail.copy(first = tail.first + 1, second = head.second)
            }
            return
        }

        if (tail.first - 2 == head.first) {
            tail = if (tail.second == head.second) {
                tail.copy(first = tail.first - 1)
            } else {
                tail.copy(first = tail.first - 1, second = head.second)
            }
            return
        }

        if (tail.second - 2 == head.second) {
            tail = if (tail.first == head.first) {
                tail.copy(second = tail.second - 1)
            } else {
                tail.copy(second = tail.second - 1, first = head.first)
            }
            return
        }

        if (tail.second + 2 == head.second) {
            tail = if (tail.first == head.first) {
                tail.copy(second = tail.second + 1)
            } else {
                tail.copy(second = tail.second + 1, first = head.first)
            }
            return
        }
    }

    fun executeRopeCommands(ropeCommands: List<RopeCommand>) {
        for (ropeCommand in ropeCommands) {
            executeRopeCommand(ropeCommand)
        }
    }
}


class RopeCommand(
    val direction: String,
    val moves: Int
)

