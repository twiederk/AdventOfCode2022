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