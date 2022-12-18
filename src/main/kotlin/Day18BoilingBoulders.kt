class BoilingBoulders {

    fun loadData(fileName: String): List<Cube> {
        val lines = Resources.resourceAsListOfString(fileName)
        return lines.map { it.split(',') }.map { Cube(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
    }

    fun solve(cubes: List<Cube>): Int {
        var countAdjacent = 0

        for (cube in cubes) {
            for (other in cubes) {
                if (cube == other) continue
                if (cube.isAdjacent(other)) countAdjacent++
            }
        }

        return cubes.size * 6 - countAdjacent
    }

    // Solution from Todd Ginsberg
    // https://todd.ginsberg.com/post/advent-of-code/2022/day18/
    fun solvePart2(cubes: Set<Cube>): Int {
        val xRange = cubes.rangeOf { it.x }
        val yRange = cubes.rangeOf { it.y }
        val zRange = cubes.rangeOf { it.z }

        val queue = ArrayDeque<Cube>().apply { add(Cube(xRange.first, yRange.first, zRange.first)) }
        val seen = mutableSetOf<Cube>()
        var sidesFound = 0
        queue.forEach { lookNext ->
            if (lookNext !in seen) {
                lookNext.cardinalNeighbors()
                    .filter { it.x in xRange && it.y in yRange && it.z in zRange }
                    .forEach { neighbor ->
                        seen += lookNext
                        if (neighbor in cubes) sidesFound++
                        else queue.add(neighbor)
                    }
            }
        }
        return sidesFound
    }

    private fun Set<Cube>.rangeOf(function: (Cube) -> Int): IntRange =
        this.minOf(function) - 1..this.maxOf(function) + 1
}

data class Point3D(
    val x: Int,
    val y: Int,
    val z: Int,
)

data class Cube(
    val x: Int,
    val y: Int,
    val z: Int,
) {
    val sides = mutableSetOf<Side>()
    val cornerPoints = mutableListOf<Point3D>()

    init {
        createCornerPoints()
        createSides()
    }

    fun createCornerPoints() {
        cornerPoints.clear()
        cornerPoints.add(Point3D(x - 1, y - 1, z - 1)) // A
        cornerPoints.add(Point3D(x - 1, y, z - 1)) // B
        cornerPoints.add(Point3D(x, y, z - 1)) // C
        cornerPoints.add(Point3D(x, y - 1, z - 1)) // D
        cornerPoints.add(Point3D(x - 1, y - 1, z)) // E
        cornerPoints.add(Point3D(x - 1, y, z)) // F
        cornerPoints.add(Point3D(x, y, z)) // G
        cornerPoints.add(Point3D(x, y - 1, z)) // H

    }

    fun createSides() {
        sides.clear()
        sides.add(Side(setOf(cornerPoints[0], cornerPoints[1], cornerPoints[2], cornerPoints[3]))) // back
        sides.add(Side(setOf(cornerPoints[4], cornerPoints[5], cornerPoints[6], cornerPoints[7]))) // front
        sides.add(Side(setOf(cornerPoints[1], cornerPoints[2], cornerPoints[5], cornerPoints[6]))) // top
        sides.add(Side(setOf(cornerPoints[0], cornerPoints[3], cornerPoints[4], cornerPoints[7]))) // bottom
        sides.add(Side(setOf(cornerPoints[0], cornerPoints[1], cornerPoints[4], cornerPoints[5]))) // left
        sides.add(Side(setOf(cornerPoints[2], cornerPoints[3], cornerPoints[6], cornerPoints[7]))) // right

    }

    fun isAdjacent(other: Cube) : Boolean {
        return (sides intersect other.sides).size == 1
    }

    fun cardinalNeighbors(): Set<Cube> =
        setOf(
            copy(x = x - 1),
            copy(x = x + 1),
            copy(y = y - 1),
            copy(y = y + 1),
            copy(z = z - 1),
            copy(z = z + 1)
        )

}

data class Side(val points: Set<Point3D>)


fun main() {
    val boilingBoulders = BoilingBoulders()
    val cubes = boilingBoulders.loadData("Day18_InputData.txt")
    val part1 = boilingBoulders.solve(cubes)

    println("visibleSides = $part1")

    val part2 = boilingBoulders.solvePart2(cubes.toSet())
    println("part2 = $part2")

}