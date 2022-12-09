import java.nio.file.Path
import kotlin.io.path.readLines

class RopeBridge {

    fun loadData(path: Path): List<String> = path.readLines()

    fun parseRopeCommand(rawCommand: String): RopeCommand {

        val (direction, moves) = rawCommand.split(" ")
        return RopeCommand(direction, moves.toInt())
    }


}

class RopeCommand(
    val direction: String,
    val moves: Int
)

