import PyroclasticFlow.Companion.shapes

class PyroclasticFlow(val input: String) {

    var towerHeight = 0
    var shapeId = 0

    val restTiles = mutableListOf<Tile>()


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

    fun fallMove(tile: Tile): Int {
        if (restTiles.firstOrNull { it.collide(tile) } != null) return tile.y
        if (tile.y - 1 == FLOOR) return tile.y
        return tile.y - 1
    }

    companion object {
        const val TUNNEL_WIDTH = 7
        const val FLOOR = 0

        val shapes = listOf(
            Shape(
                4, 1, """
                ####
                """.trimIndent().replace("\n", "")
            ),
            Shape(
                3, 3, """
                .#.
                ###
                .#.
                """.trimIndent().replace("\n", "")
            ),
            Shape(
                3, 3, """
                ..#
                ..#
                ###
            """.trimIndent().replace("\n", "")
            ),
            Shape(
                1, 4, """
                #
                #
                #
                #
            """.trimIndent().replace("\n", "")
            ),
            Shape(
                2, 2, """
                ##
                ##
            """.trimIndent().replace("\n", "")
            ),
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
        val myShape = shapes[shapeId].globalPoints(x, y)
        val otherShape = shapes[other.shapeId].globalPoints(other.x, other.y)
        val collide = myShape intersect otherShape
        return collide.isNotEmpty()
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