import PyroclasticFlow.Companion.shapes

class PyroclasticFlow(val jetStream: String) {

    var towerHeight = 0
    var shapeId = 0
    var jetCounter = 0

    val restTiles = mutableListOf<Tile>()


    fun createNextTile(): Tile {
        val tileShapeId = shapeId
        val x = 2
        val y = towerHeight + 3 + shapes[tileShapeId].height

        shapeId++
        return Tile(x, y, tileShapeId)
    }

    fun jetMove(tile: Tile, char: Char): Int = when (char) {
        '>' -> collideRight(tile)
        '<' -> collideLeft(tile)
        else -> throw IllegalArgumentException("Unknown jet sign [$char]")
    }

    private fun collideLeft(tile: Tile): Int {
        val tileMovedToLeft = tile.copy(x = tile.x - 1)
        if (restTiles.firstOrNull { it.collide(tileMovedToLeft) } != null) return tile.x
        return if (tile.x - 1 > 0) tile.x - 1 else tile.x
    }

    private fun collideRight(tile: Tile): Int {
        val tileMovedToRight = tile.copy(x = tile.x + 1)
        if (restTiles.firstOrNull { it.collide(tileMovedToRight) } != null) return tile.x
        return if (tile.x + shapes[tile.shapeId].width + 1 <= TUNNEL_WIDTH) {
            tile.x + 1
        } else {
            tile.x
        }
    }

    fun fallMove(tile: Tile): Int {
        if (restTiles.firstOrNull { it.collide(tile) } != null) return tile.y
        if (tile.y - 1 == FLOOR) return tile.y
        return tile.y - 1
    }

    fun tetris(maxTiles: Int): Int {
        while (restTiles.size < maxTiles) {
            val tile = createNextTile()
            do {
                val jet = getJet()
                tile.x = jetMove(tile, jet)
                val y = fallMove(tile)
                if (tile.y == y) {
                    restTiles.add(tile)
                    towerHeight += (y - towerHeight)
                    break
                } else {
                    tile.y = y
                }
            } while (true)
        }
        return towerHeight
    }

    fun getJet(): Char {
        val jet = jetStream[jetCounter]
        jetCounter++
        if (jetCounter > jetStream.lastIndex) jetCounter = 0
        return jet
    }

    companion object {
        const val TUNNEL_WIDTH = 7
        const val FLOOR = 0

        val shapes = listOf(
            // id: 0
            Shape(
                4, 1, """
                ####
                """.trimIndent().replace("\n", "")
            ),
            // id: 1
            Shape(
                3, 3, """
                .#.
                ###
                .#.
                """.trimIndent().replace("\n", "")
            ),
            // id: 2
            Shape(
                3, 3, """
                ..#
                ..#
                ###
            """.trimIndent().replace("\n", "")
            ),
            // id: 3
            Shape(
                1, 4, """
                #
                #
                #
                #
            """.trimIndent().replace("\n", "")
            ),
            // id: 4
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
    var x: Int,
    var y: Int,
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
                if (body[h * width + w] == '#') {
                    globalPoints.add(Point(x + w, y - h))
                }
            }
        }
        return globalPoints
    }
}