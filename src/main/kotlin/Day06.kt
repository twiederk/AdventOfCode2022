import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.readLines

class TuningTrouble {

    fun loadData(path: Path): String = path.readLines()[0]

    fun createWindows(dataStream: String, numberOfDigit: Int): List<String> = dataStream.windowed(size = numberOfDigit, step = 1)

    fun isStartOfPacketMarker(dataPackage: String, numberOfDigits: Int): Boolean {
        var count = 0
        for (i in 0 until numberOfDigits) {
            count += dataPackage.count { it == dataPackage[i]}
        }
        return count == numberOfDigits
    }

    fun findStartOfPacketMarker(windows: List<String>, numberOfDigit: Int): Int {
        var position = 0
        for (window in windows) {
            position++
            if (isStartOfPacketMarker(window, numberOfDigit)) return position + numberOfDigit - 1
        }
        throw IllegalStateException("Can't find start of packet marker")
    }

}

fun main() {
    val tuningTrouble = TuningTrouble()
    val dataStream = tuningTrouble.loadData(Path("src", "main", "resources", "Day06_InputData.txt"))
    val windows = tuningTrouble.createWindows(dataStream, 4)
    val position = tuningTrouble.findStartOfPacketMarker(windows, 4)
    println("position = $position")

    val windows2 = tuningTrouble.createWindows(dataStream, 14)
    val position2 = tuningTrouble.findStartOfPacketMarker(windows2, 14)
    println("position2 = $position2")

}