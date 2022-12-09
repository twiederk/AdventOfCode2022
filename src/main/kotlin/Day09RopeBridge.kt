import java.nio.file.Path
import kotlin.io.path.readLines

class RopeBridge {

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


}

class RopeCommand(
    val direction: String,
    val moves: Int
)

