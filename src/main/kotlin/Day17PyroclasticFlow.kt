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

    fun collide(tile: Tile, other: Tile): Boolean {
        return tile.overlap(other)
    }

    companion object {
        const val TUNNEL_WIDTH = 7

        val shapes = listOf(
            Shape(4, 1),
            Shape(3, 3),
            Shape(3, 3),
            Shape(1, 4),
            Shape(2, 2),
        )

    }
}

data class Tile(
    val x: Int,
    val y: Int,
    val shapeId: Int,
) {
    fun overlap(other: Tile): Boolean {
        return x..x + PyroclasticFlow.shapes[shapeId].width overlaps other.x..other.x + PyroclasticFlow.shapes[other.shapeId].width
                && y..y + PyroclasticFlow.shapes[shapeId].height overlaps other.y..other.y + PyroclasticFlow.shapes[other.shapeId].height
    }
}

data class Shape(
    val width: Int,
    val height: Int,
)