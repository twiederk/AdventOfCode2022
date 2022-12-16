import java.util.*

class ProboscideaVolcanium {

    // load data
    // - create valve
    // - assign neighbors

    fun loadData(fileName: String): List<String> {
        return Resources.resourceAsListOfString(fileName)
    }

    fun createValves(rawData: List<String>): Map<String, Valve> =
        rawData.map { row ->
            Valve(
                row.substringAfter("Valve ").substringBefore(" has "),
                row.substringAfter("rate=").substringBefore(";").toInt()
            )
        }.associateBy { it.name }


    fun addNeighbors(rawData: List<String>, valves: Map<String, Valve>) {
        for (line in rawData) {
            val key = line.substringAfter("Valve ").substringBefore(" has ")
            val keysOfNeighbors = getKeysOfNeighbors(line)
            val valve = valves.getValue(key)
            for (currentKey in keysOfNeighbors) {
                valve.neighbors.add(valves.getValue(currentKey))
            }
        }

    }

    private fun getKeysOfNeighbors(line: String): List<String> {
        if (line.contains("valves")) return line.substringAfter(" valves ").split(", ")
        return listOf(line.substringAfter(" valve "))
    }

    fun breadFirstSearch(
        begin: Valve,
        isGoal: (Int) -> Boolean,
    ): Int {
        val seen = mutableSetOf<Valve>()
        val queue = PriorityQueue<Valve>().apply { add(begin) }

        var minute = 0
        while (queue.isNotEmpty()) {
            val nextValve = queue.poll()

            if (nextValve !in seen) {
                seen += nextValve
                val neighbors = nextValve.neighbors
                if (neighbors.any { isGoal(minute) }) return Int.MAX_VALUE
                queue.addAll(neighbors)
            }
            minute++
        }
        throw IllegalStateException("No valid path")
    }

}

data class Valve(val name: String, val flowRate: Int) : Comparable<Valve> {
    val neighbors = mutableListOf<Valve>()

    override fun compareTo(other: Valve): Int = -this.flowRate.compareTo(other.flowRate)

}