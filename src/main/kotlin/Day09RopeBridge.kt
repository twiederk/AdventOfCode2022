import java.nio.file.Path
import kotlin.io.path.readLines

class RopeBridge {


    var head = Pair(0, 0)

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
            "U" -> head = head.copy(second = head.second - 1)
            "D" -> head = head.copy(second = head.second + 1)
        }
    }

    fun executeRopeCommand(ropeCommand: RopeCommand) {
        for (move in 1..ropeCommand.moves) {
            moveHead(ropeCommand.direction)
        }
    }

}

class RopeCommand(
    val direction: String,
    val moves: Int
)

