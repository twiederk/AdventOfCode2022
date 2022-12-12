import java.nio.file.Path
import kotlin.io.path.readLines

class HillClimbingAlgorithm {

    fun loadData(path: Path): List<List<Char>> {
        val rawData =  path.readLines()
        return rawData.map { line -> line.map { it } }
    }

}