import java.nio.file.Path
import kotlin.io.path.Path
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

    fun totalSignalStrength(cycles: List<Int>): Int {
        val importantCycles = listOf(20, 60, 100, 140, 180, 220)
        var totalSignalStrength = 0
        for (cycle in importantCycles) {
            totalSignalStrength += (cycles[cycle - 1] * cycle)
        }
        return totalSignalStrength
    }

}

fun main() {
    val cathodeRayTube = CathodeRayTube()
    val instructions = cathodeRayTube.loadData(Path("src", "main", "resources", "Day10_Part1_InputData.txt"))
    val cycles = cathodeRayTube.cycles(instructions)
    val totalSignalStrength = cathodeRayTube.totalSignalStrength(cycles)

    println("totalSignalStrength = $totalSignalStrength")

}