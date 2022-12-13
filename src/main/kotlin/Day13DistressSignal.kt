import java.nio.file.Path
import kotlin.io.path.readLines

class DistressSignal {

    fun loadData(path: Path): List<Pair<String, String>> {
        val chunks = path.readLines().filter { it.isNotEmpty() }.chunked(2)
        val packets = mutableListOf<Pair<String, String>>()
        for (chunk in chunks) {
            packets.add(Pair(chunk[0], chunk[1]))
        }
        return packets
    }

}