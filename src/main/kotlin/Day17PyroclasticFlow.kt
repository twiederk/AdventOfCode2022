class PyroclasticFlow(val input: String) {

    var towerHeight = 0
    var shapeId = 0

    val shapes = listOf(
        Shape(4, 1),
        Shape(3, 3),
        Shape(3, 3),
        Shape(1, 4),
        Shape(2, 2),
    )

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
            else -> tile.x
        }

    private fun collideRightWall(tile: Tile) : Int {
        return if (tile.x + shapes[tile.shapeId].width + 1 <= TUNNEL_WIDTH) {
            tile.x +1
        } else {
            tile.x
        }
    }

    companion object {
        const val TUNNEL_WIDTH = 7
    }
}

data class Tile(
    val x: Int,
    val y: Int,
    val shapeId: Int,
)

data class Shape(
    val width: Int,
    val height: Int,
)