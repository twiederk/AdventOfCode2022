import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.readLines

typealias PairOfPacket = Pair<String, String>

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
                return Reason(left, right, Order.WRONG, "HIGHER than right", index)
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

    fun flatPackets(packets: List<Pair<String, String>>): List<String> {
        val flatPackets = mutableListOf<String>()
        for (packet in packets) {
            flatPackets.add(packet.first)
            flatPackets.add(packet.second)
        }
        flatPackets.add("[[2]]")
        flatPackets.add("[[6]]")
        return flatPackets
    }

    fun sortAllSignals(signals: List<String>): List<String> {
        val preparedSignals = mutableListOf<String>()
        for (signal in signals) {
            preparedSignals.add(preparePacket(signal))
        }
        return preparedSignals.sortedWith(SignalComparator()).reversed()

    }

    fun decoderKeyForDistressSignal(packets: List<Pair<String, String>>): Int {
        val signals = flatPackets(packets)
        val sortedSignals = sortAllSignals(signals)
        val startSignal = sortedSignals.indexOf("2]]]") + 1
        val endSignal = sortedSignals.indexOf("6]]]") + 1
        return startSignal * endSignal
    }

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


class SignalComparator: Comparator<String> {

    override fun compare(signal1: String, signal2: String): Int {
        for (index in signal1.indices) {
            val left = signal1[index]

            if (index >= signal2.length) return -1
            val right = signal2[index]

            if (left == right) {
                continue
            }

            if (left.isDigitOrA() && right.isDigitOrA()) {
                if (left < right) return 1
                return -1
            }

            if (left == ']') return 1
            if (right == ']') return -1
        }
        return 1
    }
}




fun main() {
    val distressSignal = DistressSignal()
    val packets = distressSignal.loadData(Path("src", "main", "resources", "Day13_InputData.txt"))
    val result = distressSignal.decodeAll(packets)

    println("result = $result")

    val decoderKey = distressSignal.decoderKeyForDistressSignal(packets)
    println("decoderKey = $decoderKey")

}

