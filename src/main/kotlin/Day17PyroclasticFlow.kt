class PyroclasticFlow(val input: String) {

    var towerHeight = 0
    var shapeId = 0

    fun createNextTile(): Tile {
        return Tile(2, towerHeight + 3, shapeId)
    }

}

data class Tile(
    val x: Int,
    val y: Int,
    val shapeId: Int,
)