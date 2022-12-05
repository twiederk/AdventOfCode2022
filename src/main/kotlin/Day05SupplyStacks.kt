import java.util.*

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

}

data class Command(
    val count: Int,
    val source: Int,
    val destination: Int
)
