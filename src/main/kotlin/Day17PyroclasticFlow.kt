import PyroclasticFlow.Companion.shapes

class PyroclasticFlow(val input: String) {

    var towerHeight = 0
    var shapeId = 0

    fun createNextTile(): Tile {
        val tileShapeId = shapeId
        val x = 2
        val y = towerHeight + 3 + shapes[tileShapeId].height

        shapeId++
        return Tile(x, y, tileShapeId)
    }

    fun jetMove(tile: Tile, char: Char): Int =
        when (char) {
            '>' -> collideRightWall(tile)
            '<' -> collideLeftWall(tile)
            else -> throw IllegalArgumentException("Unknown jet sign [$char]")
        }

    private fun collideLeftWall(tile: Tile): Int = if (tile.x - 1 > 0) tile.x - 1 else tile.x

    private fun collideRightWall(tile: Tile): Int = if (tile.x + shapes[tile.shapeId].width + 1 <= TUNNEL_WIDTH) {
        tile.x + 1
    } else {
        tile.x
    }

    companion object {
        const val TUNNEL_WIDTH = 7

        val shapes = listOf(
            Shape(4, 1, """
                ####
                """.trimIndent().replace("\n","")),
            Shape(3, 3,"""
                .#.
                ###
                .#.
                """.trimIndent().replace("\n","")),
            Shape(3, 3,"""
                ..#
                ..#
                ###
            """.trimIndent().replace("\n","")),
            Shape(1, 4, """
                #
                #
                #
                #
            """.trimIndent().replace("\n","")),
            Shape(2, 2, """
                ##
                ##
            """.trimIndent().replace("\n","")),
        )

    }
}

data class Tile(
    val x: Int,
    val y: Int,
    val shapeId: Int,
) {
    fun overlap(other: Tile): Boolean {
        return x..x + shapes[shapeId].width overlaps other.x..other.x + shapes[other.shapeId].width
                && y..y + shapes[shapeId].height overlaps other.y..other.y + shapes[other.shapeId].height
    }

    fun collide(other: Tile): Boolean {
        if (!overlap(other)) return false
        // |.......|
        // |..1....|
        // |..1....|
        // |1112...|
        // |..222..|
        // |...2...|
        // |..####.|
        // +-------+


        return true
    }
}

data class Shape(
    val width: Int,
    val height: Int,
    val body: String
) {
    fun globalPoints(x: Int, y: Int): Set<Point> {
        val globalPoints = mutableSetOf<Point>()
        for (h in 0 until height) {
            for (w in 0 until width) {
                if (body[h * height + w] == '#') {
                    globalPoints.add(Point(x + w, y + h))
                }
            }
        }
        return globalPoints
    }
}