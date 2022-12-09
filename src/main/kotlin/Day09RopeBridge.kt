import java.nio.file.Path
import kotlin.io.path.readLines

class RopeBridge {

    fun loadData(path: Path): List<String> = path.readLines()


}