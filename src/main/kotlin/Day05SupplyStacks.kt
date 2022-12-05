import java.nio.file.Path
import java.util.*
import kotlin.io.path.readLines

class SupplyStacks(
    private val storage: List<Stack<Char>> = listOf()
) {

    //    move 1 from 2 to 1
    fun parseCommand(rawCommand: String): Command {
        val splitCommand = rawCommand.split(" ")
        return Command(splitCommand[1].toInt(), splitCommand[3].toInt(), splitCommand[5].toInt())
    }

    fun parseCommands(rawCommands: List<String>): List<Command> {
        val commands = mutableListOf<Command>()
        for (rawCommand in rawCommands) {
            val command = parseCommand(rawCommand)
            commands.add(command)
        }
        return commands
    }

    fun topCrates(): String {
        var topCrates = ""
        for (stack in storage) {
            topCrates += stack.peek()
        }
        return topCrates
    }

    fun executeCommand(command: Command) {
        for (i in 1..command.count) {
            val crate = storage[command.source - 1].pop()
            storage[command.destination - 1].push(crate)
        }
    }

    fun executeCommands(commands: List<Command>) {
        for (command in commands) {
            executeCommand(command)
        }
    }

    fun loadData(path: Path, linesToDrop: Int): List<String> {
        return path.readLines().drop(linesToDrop)
    }

}

data class Command(
    val count: Int,
    val source: Int,
    val destination: Int
)
