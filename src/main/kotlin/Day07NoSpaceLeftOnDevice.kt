import java.nio.file.Path
import kotlin.io.path.readLines

class NoSpaceLeftOnDevice {

    val openDirs = mutableListOf<MutableList<Int>>()
    val closeDirs = mutableListOf<MutableList<Int>>()

    fun loadData(path: Path): List<String> {
        return path.readLines()
    }

    fun openDir(): MutableList<Int> {
        val dir = mutableListOf<Int>()
        openDirs.add(dir)
        return dir
    }

    fun addFileToOpenDirs(size: Int) {
        for (dir in openDirs) {
            dir.add(size)
        }
    }

    fun closeDir() {
        val dir = openDirs.removeLast()
        closeDirs.add(dir)
    }

    fun closeAllDirs() {
        while (openDirs.isNotEmpty()) {
            closeDir()
        }
    }

    fun getDirsAtMost(max: Int): Int {
        return closeDirs.filter { it.sum() <= max }.sumOf { it.sum() }
    }

    fun execute(terminalOutput: List<String>, atMost: Int): Int {
        for (output in terminalOutput) {
            when (val osCommand = parseCommand(output)) {
                is OsCommand.OpenDirCommand -> openDir()
                is OsCommand.CloseDirCommand -> closeDir()
                is OsCommand.AddFileCommand -> addFileToOpenDirs(osCommand.size)
                is OsCommand.NothingCommand -> continue
            }
        }
        closeAllDirs()
        return getDirsAtMost(atMost)
    }

    fun parseCommand(output: String): OsCommand {
        if (output == "$ cd ..") return OsCommand.CloseDirCommand
        if (output.startsWith("$ cd ")) return OsCommand.OpenDirCommand
        if (output[0].isDigit()) return createAddFileCommand(output.split(" ")[0].toInt())
        return OsCommand.NothingCommand
    }

    fun createAddFileCommand(size: Int): OsCommand.AddFileCommand {
        return OsCommand.AddFileCommand(size)
    }


}

sealed class OsCommand() {
    object NothingCommand : OsCommand()
    object OpenDirCommand : OsCommand()
    object CloseDirCommand : OsCommand()
    class AddFileCommand(val size: Int) : OsCommand()
}

