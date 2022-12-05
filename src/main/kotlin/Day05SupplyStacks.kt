import java.nio.file.Path
import java.util.*
import kotlin.io.path.Path
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
            if (stack.isEmpty()) {
                topCrates += " "
            } else {
                topCrates += stack.peek()
            }
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

    fun executeCommand9001(command: Command) {
        val tempStack = Stack<Char>()
        for (i in 1..command.count) {
            val crate = storage[command.source - 1].pop()
            tempStack.push(crate)
        }

        for (i in 1..command.count) {
            val crate = tempStack.pop()
            storage[command.destination - 1].push(crate)
        }
    }

}

data class Command(
    val count: Int,
    val source: Int,
    val destination: Int
)


fun main() {
    val stack1 = Stack<Char>()
    stack1.push('G')
    stack1.push('D')
    stack1.push('V')
    stack1.push('Z')
    stack1.push('J')
    stack1.push('S')
    stack1.push('B')

    val stack2 = Stack<Char>()
    stack2.push('Z')
    stack2.push('S')
    stack2.push('M')
    stack2.push('G')
    stack2.push('V')
    stack2.push('P')

    val stack3 = Stack<Char>()
    stack3.push('C')
    stack3.push('L')
    stack3.push('B')
    stack3.push('S')
    stack3.push('W')
    stack3.push('T')
    stack3.push('Q')
    stack3.push('F')

    val stack4 = Stack<Char>()
    stack4.push('H')
    stack4.push('J')
    stack4.push('G')
    stack4.push('W')
    stack4.push('M')
    stack4.push('R')
    stack4.push('V')
    stack4.push('Q')

    val stack5 = Stack<Char>()
    stack5.push('C')
    stack5.push('L')
    stack5.push('S')
    stack5.push('N')
    stack5.push('F')
    stack5.push('M')
    stack5.push('D')

    val stack6 = Stack<Char>()
    stack6.push('R')
    stack6.push('G')
    stack6.push('C')
    stack6.push('D')

    val stack7 = Stack<Char>()
    stack7.push('H')
    stack7.push('G')
    stack7.push('T')
    stack7.push('R')
    stack7.push('J')
    stack7.push('D')
    stack7.push('S')
    stack7.push('Q')

    val stack8 = Stack<Char>()
    stack8.push('P')
    stack8.push('F')
    stack8.push('V')

    val stack9 = Stack<Char>()
    stack9.push('D')
    stack9.push('R')
    stack9.push('S')
    stack9.push('T')
    stack9.push('J')


    val storage = listOf(stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9)
    val supplyStacks = SupplyStacks(storage)

    check(supplyStacks.topCrates() == "BPFQDDQVJ") { "Storage is wrong" }

    val rawCommands = supplyStacks.loadData(Path("src", "main", "resources", "Day05_Part1_InputData.txt"), 10)
    val commands = supplyStacks.parseCommands(rawCommands)
    supplyStacks.executeCommands(commands)
    println("supplyStacks.topCrates() = ${supplyStacks.topCrates()}")

}