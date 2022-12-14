import Token.*
import java.nio.file.Path
import kotlin.io.path.Path
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
            var left = firstPacket.nextToken()
            var right = secondPacket.nextToken()

            if (left is IntegerToken && right == StartListToken) {
                right = secondPacket.nextToken()
            }

            if (left is IntegerToken && right == EndListToken) {
                return Order.WRONG
            }

            if (left is StartListToken && right is IntegerToken) {
                left = firstPacket.nextToken()
            }

            if (left is StartListToken && right is EndListToken) {
                return Order.WRONG
            }

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
                continue
            }

            if (left == EndListToken && right is IntegerToken) {
                return Order.CORRECT
            }

            if (left == EndPacketToken) {
                return Order.CORRECT
            }

            if (right == EndPacketToken) {
                return Order.WRONG
            }
        }

        return Order.WRONG
    }

    fun decodeAll(packets: List<Pair<String, String>>): Int {
        var result = 0
        for ((index, packet) in packets.withIndex()) {
            if (decode(packet) == Order.CORRECT) {
                result += (index + 1)
            }
        }
        return result
    }

    fun createSingleLists(input: String): String {
        var output = ""
        for (char in input) {
            if (char.isDigit()) {
                output += "[$char]"
            } else {
                output += char
            }
        }
        return output
    }

    fun removeCommas(input: String): String = removeChar(input, ',')

    fun removeStartList(input: String): String = removeChar(input, '[')

    private fun removeChar(input: String, charToRemove: Char) : String {
        var output = ""
        for (char in input) {
            if (char == charToRemove) {
                continue
            }
            output += char
        }
        return output
    }

    fun preparePacket(input: String): String = removeStartList(removeCommas(createSingleLists(input)))

}

data class Packet(val content: String) {
    var listCounter: ListCounter = 0
    var pointer: Pointer = 0

    fun nextToken(): Token {

        if (!hasMoreToken()) return EndPacketToken
        if (content[pointer] == ',') pointer++

        val token = when (content[pointer]) {
            '[' -> StartListToken
            ']' -> EndListToken
            else -> if (pointer + 1 < content.length && content[pointer + 1] == '0') {
                pointer++
                IntegerToken(10)
            } else {
                IntegerToken(content[pointer].digitToInt())
            }
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

fun main() {
    val distressSignal = DistressSignal()
    val packets = distressSignal.loadData(Path("src", "main", "resources", "Day13_Part1_InputData.txt"))
    val result = distressSignal.decodeAll(packets)

    println("result = $result")

}