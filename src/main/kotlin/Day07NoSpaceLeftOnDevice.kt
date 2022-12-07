import java.nio.file.Path
import kotlin.io.path.readLines

class NoSpaceLeftOnDevice {

    fun loadData(path: Path) : List<String> {
        return path.readLines()
    }

}