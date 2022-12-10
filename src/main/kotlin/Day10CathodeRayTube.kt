import java.nio.file.Path
import kotlin.io.path.readLines

class CathodeRayTube {

    fun loadData(path: Path): List<String> = path.readLines()

}