import java.nio.file.Path
import kotlin.io.path.readLines

class TuningTrouble {

    fun loadData(path: Path) : String = path.readLines()[0]

}