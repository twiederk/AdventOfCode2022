import java.nio.file.Path
import kotlin.io.path.readLines

typealias Packet = Pair<String, String>

class DistressSignal {

    fun loadData(path: Path): List<Packet> {
        val chunks = path.readLines().filter { it.isNotEmpty() }.chunked(2)
        val packets = mutableListOf<Packet>()
        for (chunk in chunks) {
            packets.add(Pair(chunk[0], chunk[1]))
        }
        return packets
    }

    fun decode(packet: Packet): Order {
        var listCount = 0
        for (index in packet.first.indices) {
            val left = packet.first[index]
            val right = packet.second[index]


        }
        return Order.WRONG
    }

}

enum class Order {
    RIGHT, WRONG
}