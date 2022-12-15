import java.nio.file.Path
import kotlin.io.path.readLines

class BeaconExclusionZone {

    fun loadData(path: Path): List<String> = path.readLines()

}