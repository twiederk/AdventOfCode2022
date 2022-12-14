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

    fun decode(pairOfPacket: PairOfPacket): Reason {
        val firstPacket = preparePacket(pairOfPacket.first)
        val secondPacket = preparePacket(pairOfPacket.second)

        for (index in firstPacket.indices) {
            val left = firstPacket[index]

            if (index >= secondPacket.length) return Reason(left, '?', Order.WRONG, "END OF RIGHT reached", index)
            val right = secondPacket[index]

            if (left == right) {
                continue
            }

            if (left.isDigitOrA() && right.isDigitOrA()) {
                if (left < right) return Reason(left, right, Order.CORRECT, "LOWER than right", index)
                if (left > right) return Reason(left, right, Order.WRONG, "HIGHER than right", index)
            }

            if (left == ']') return Reason(left, right, Order.CORRECT, "LEFT ran out of elements", index)
            if (right == ']') return Reason(left, right, Order.WRONG, "RIGHT ran out of elements", index)
        }
        return Reason('?', '?', Order.CORRECT, "END OF LEFT reached", -1)
    }

    fun decodeAll(packets: List<Pair<String, String>>): Int {
        var result = 0
        for ((index, packet) in packets.withIndex()) {
            if (decode(packet).order == Order.CORRECT) {
                result += (index + 1)
            }
        }
        return result
    }

    fun replaceTenWithA(input: String): String = input.replace("10", "a")

    fun createSingleLists(input: String): String {
        var output = ""
        for (char in input) {
            if (char.isDigitOrA()) {
                output += "[$char]"
            } else {
                output += char
            }
        }
        return output
    }

    fun removeCommas(input: String): String = removeChar(input, ',')

    fun removeStartList(input: String): String = removeChar(input, '[')

    private fun removeChar(input: String, charToRemove: Char): String {
        var output = ""
        for (char in input) {
            if (char == charToRemove) {
                continue
            }
            output += char
        }
        return output
    }

    fun preparePacket(input: String): String = removeStartList(removeCommas(createSingleLists(replaceTenWithA(input))))

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

data class Reason(
    val left: Char,
    val right: Char,
    val order: Order,
    val reason: String,
    val index: Int
)

fun Char.isDigitOrA(): Boolean = this.isDigit() || this == 'a'

fun main() {
    val distressSignal = DistressSignal()
    val packets = distressSignal.loadData(Path("src", "main", "resources", "Day13_Part1_InputData.txt"))
    val result = distressSignal.decodeAll(packets)

    println("result = $result")

}

