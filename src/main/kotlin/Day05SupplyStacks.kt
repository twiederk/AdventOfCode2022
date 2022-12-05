class SupplyStacks {

    //    move 1 from 2 to 1
    fun parseCommand(rawCommand: String): Command {
        val splitCommand = rawCommand.split(" ")
        return Command(splitCommand[1].toInt(), splitCommand[3].toInt(), splitCommand[5].toInt())
    }

}

data class Command(
    val count: Int,
    val source: Int,
    val destination: Int
)
