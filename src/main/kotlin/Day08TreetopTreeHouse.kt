import java.nio.file.Path
import kotlin.io.path.readLines

class TreetopTreeHouse {

    fun loadData(path: Path) : List<String> = path.readLines()

    fun convertToInts(input: List<String>): List<List<Int>> {
        return input.map { s -> s.map { it.digitToInt() } }
    }

}