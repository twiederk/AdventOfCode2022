import kotlin.math.max

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

}

data class Valve(val name: String, val flowRate: Int) : Comparable<Valve> {
    val neighbors = mutableListOf<Valve>()

    override fun compareTo(other: Valve): Int = -this.flowRate.compareTo(other.flowRate)

}

//Solution from https://github.com/ckainz11/AdventOfCode2022/blob/main/src/main/kotlin/days/day16/Day16.kt
class Day16(input: String) {

    private val parsed = input.lines().map { Valve.from(it) }
    private val valves = parsed.associateBy { it.id }
    private val shortestPaths =
        floydWarshall(parsed.associate { it.id to it.neighborIds.associateWith { 1 }.toMutableMap() }.toMutableMap())
    private var score = 0
    private var totalTime = 30

    fun solve1(): Int {
        dfs(0, "AA", emptySet(), 0)
        return score
    }

    fun solve2(): Int {
        totalTime = 26
        score = 0
        dfs(0, "AA", emptySet(), 0, true)
        return score
    }
    private fun dfs(currScore: Int, currentValve: String, visited: Set<String>, time: Int, part2: Boolean = false) {
        score = max(score, currScore)
        for ((valve, dist) in shortestPaths[currentValve]!!) {
            if (!visited.contains(valve) && time + dist + 1 < totalTime) {
                dfs(
                    currScore + (totalTime - time - dist - 1) * valves[valve]?.flowRate!!,
                    valve,
                    visited.union(listOf(valve)),
                    time + dist + 1,
                    part2
                )
            }
        }
        if(part2)
            dfs(currScore, "AA", visited, 0, false)
    }


    private fun floydWarshall(shortestPaths: MutableMap<String, MutableMap<String, Int>>): MutableMap<String, MutableMap<String, Int>> {
        for (k in shortestPaths.keys) {
            for (i in shortestPaths.keys) {
                for (j in shortestPaths.keys) {
                    val ik = shortestPaths[i]?.get(k) ?: 9999
                    val kj = shortestPaths[k]?.get(j) ?: 9999
                    val ij = shortestPaths[i]?.get(j) ?: 9999
                    if (ik + kj < ij)
                        shortestPaths[i]?.set(j, ik + kj)
                }
            }
        }
        //remove all paths that lead to a valve with rate 0
        shortestPaths.values.forEach {
            it.keys.map { key -> if (valves[key]?.flowRate == 0) key else "" }
                .forEach { toRemove -> if (toRemove != "") it.remove(toRemove) }
        }
        return shortestPaths
    }



    data class Valve(val id: String, val flowRate: Int, val neighborIds: List<String>) {
        companion object {
            fun from(line: String): Valve {
                val (name, rate) = line.split("; ")[0].split(" ").let { it[1] to it[4].split("=")[1].toInt() }
                val neighbors = line.split(", ").toMutableList()
                neighbors[0] = neighbors[0].takeLast(2)
                return Valve(name, rate, neighbors)
            }
        }
    }
}

fun main() {
    val rawData = Resources.resourceAsText("Day16_InputData.txt")
    val day16 = Day16(rawData)

    val resultPart1 = day16.solve1()
    println("resultPart1 = $resultPart1")

    val resultPart2 = day16.solve2()
    println("resultPart2 = $resultPart2")
}