import java.nio.file.Path
import kotlin.io.path.readLines

class CathodeRayTube {

    var X: Int = 1

    fun loadData(path: Path): List<String> = path.readLines()

    fun cycle(instruction: String): List<Int> {
        if (instruction == "noop") {
            return listOf(X)
        }
        val value = instruction.split(" ")[1].toInt()
        val cycles = listOf(X, X)
        X += value
        return cycles
    }

    fun cycles(instructions: List<String>): List<Int> {
        val cycles = mutableListOf<Int>()
        for (instruction in instructions) {
            cycles.addAll(cycle(instruction))
        }
        return cycles
    }

}