import java.nio.file.Path
import kotlin.io.path.readLines

class TuningTrouble {

    fun loadData(path: Path) : String = path.readLines()[0]

    fun createWindows(dataStream: String): List<String> = dataStream.windowed(size = 4, step = 1)

    fun isStartOfPacketMarker(dataPackage: String): Boolean {
        var count = 0
        count += dataPackage.count { it == dataPackage[0] }
        count += dataPackage.count { it == dataPackage[1] }
        count += dataPackage.count { it == dataPackage[2] }
        count += dataPackage.count { it == dataPackage[3] }
        return count == 4
    }

}