import Token.*
import java.nio.file.Path
import kotlin.io.path.readLines

typealias PairOfPacket = Pair<String, String>
typealias Pointer = Int
typealias ListCounter = Int

class DistressSignal {

    fun loadData(path: Path): List<PairOfPacket> {
        val chunks = path.readLines().filter { it.isNotEmpty() }.chunked(2)
        val packets = mutableListOf<PairOfPacket>()
        for (chunk in chunks) {
            packets.add(Pair(chunk[0], chunk[1]))
        }
        return packets
    }

    fun decode(pairOfPacket: PairOfPacket): Order {
        val firstPacket = Packet(pairOfPacket.first)
        val secondPacket = Packet(pairOfPacket.second)

        while (firstPacket.hasMoreToken()) {
            val left = firstPacket.nextToken()
            val right = firstPacket.nextToken()

            if (left == StartListToken && right == StartListToken) {
                firstPacket.listCounter++
                secondPacket.listCounter++
                continue
            }

            if (left == EndListToken && right == EndListToken) {
                firstPacket.listCounter--
                secondPacket.listCounter--
                continue
            }

            if (left is IntegerToken && right is IntegerToken) {
                if (left.value < right.value) return Order.CORRECT
                if (left.value > right.value) return Order.WRONG
                if (left.value == right.value) continue
            }
        }

        return Order.WRONG
    }

}

class Packet(val content: String) {
    var listCounter: ListCounter = 0
    var pointer: Pointer = 0

    fun nextToken(): Token {

        if (!hasMoreToken()) return EndPacketToken
        if (content[pointer] == ',') pointer ++

        val token = when (content[pointer]) {
            '[' -> StartListToken
            ']' -> EndListToken
            else -> IntegerToken(content[pointer].digitToInt())
        }
        pointer++
        return token

    }

    fun hasMoreToken(): Boolean = pointer < content.length

}




sealed class Token {
    object StartListToken : Token()
    object EndListToken : Token()
    object EndPacketToken : Token()
    class IntegerToken(val value: Int) : Token()
}

enum class Order {
    CORRECT, WRONG
}