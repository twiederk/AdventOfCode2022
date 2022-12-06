import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.readLines

class TuningTrouble {

    fun loadData(path: Path): String = path.readLines()[0]

    fun createWindows(dataStream: String): List<String> = dataStream.windowed(size = 4, step = 1)

    fun isStartOfPacketMarker(dataPackage: String): Boolean {
        var count = 0
        count += dataPackage.count { it == dataPackage[0] }
        count += dataPackage.count { it == dataPackage[1] }
        count += dataPackage.count { it == dataPackage[2] }
        count += dataPackage.count { it == dataPackage[3] }
        return count == 4
    }

    fun findStartOfPacketMarker(windows: List<String>): Int {
        var position = 0
        for (window in windows) {
            position++
            if (isStartOfPacketMarker(window)) return position + 3
        }
        throw IllegalStateException("Can't find start of packet marker")
    }

}

fun main() {
    val tuningTrouble = TuningTrouble()
    val dataStream = tuningTrouble.loadData(Path("src", "main", "resources", "Day06_Part1_InputData.txt"))
    val windows = tuningTrouble.createWindows(dataStream)
    val position = tuningTrouble.findStartOfPacketMarker(windows)
    println("position = $position")
}