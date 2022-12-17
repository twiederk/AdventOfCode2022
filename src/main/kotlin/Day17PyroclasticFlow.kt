import PyroclasticFlow.Companion.shapes

class PyroclasticFlow(val jetStream: String, var debug: Boolean = false) {

    var towerHeight = 0L
    var shapeId = 0
    var jetCounter = 0

    val restTiles = mutableListOf<Tile>()


    fun createNextTile(): Tile {
        val tileShapeId = shapeId
        val x = 2L
        val y = towerHeight + 4

        shapeId++
        if (shapeId > shapes.lastIndex) shapeId = 0
        return Tile(x, y, tileShapeId)
    }

    fun jetMove(tile: Tile, char: Char): Long = when (char) {
        '>' -> collideRight(tile)
        '<' -> collideLeft(tile)
        else -> throw IllegalArgumentException("Unknown jet sign [$char]")
    }

    private fun collideLeft(tile: Tile): Long {
        val tileMovedToLeft = tile.copy(x = tile.x - 1)
        if (restTiles.firstOrNull { it.collide(tileMovedToLeft) } != null) return tile.x
        return if (tile.x - 1 >= 0) tile.x - 1 else tile.x
    }

    private fun collideRight(tile: Tile): Long {
        val tileMovedToRight = tile.copy(x = tile.x + 1)
        if (restTiles.firstOrNull { it.collide(tileMovedToRight) } != null) return tile.x
        return if (tile.x + shapes[tile.shapeId].width + 1 <= TUNNEL_WIDTH) {
            tile.x + 1
        } else {
            tile.x
        }
    }

    fun fallMove(tile: Tile): Long {
        val tileFallen = tile.copy(y = tile.y - 1)
        if (restTiles.firstOrNull { it.collide(tileFallen) } != null) return tile.y
        if (tile.y - 1 == FLOOR) return tile.y
        return tile.y - 1
    }

    fun tetris(maxTiles: Long): Long {
        while (restTiles.size < maxTiles) {
            val tile = createNextTile()
            debug("appearing: $tile")
            do {
                val jet = getJet()
                tile.x = jetMove(tile, jet)
                debug("after jetMove [$jet]: $tile")
                val y = fallMove(tile)
                if (tile.y == y) {
                    debug("resting: $tile")
                    restTiles.add(tile)
                    debug("restTiles = $restTiles")

                    towerHeight = calculateTowerHeight(y, tile)
                    debug("towerHeight = $towerHeight")
                    debug("####################")
                    break
                } else {
                    tile.y = y
                    debug("after fallMove   : $tile")
                }
            } while (true)
        }
        return towerHeight
    }

    private fun calculateTowerHeight(y: Long, tile: Tile): Long {
        val newTowerHeight = towerHeight + ((y - 1) + shapes[tile.shapeId].height - towerHeight)
        return maxOf(newTowerHeight, towerHeight)
    }

    fun getJet(): Char {
        val jet = jetStream[jetCounter]
        jetCounter++
        if (jetCounter > jetStream.lastIndex) jetCounter = 0
        return jet
    }

    private fun debug(message: String) {
        if (debug) println(message)
    }

    companion object {
        const val TUNNEL_WIDTH = 7L
        const val FLOOR = 0L

        val shapes = listOf(
            // id: 0
            Shape(
                4, 1,
                arrayOf(
                    "####".toCharArray(),
                )
            ),
            // id: 1
            Shape(
                3, 3,
                arrayOf(
                    ".#.".toCharArray(),
                    "###".toCharArray(),
                    ".#.".toCharArray(),
                )
            ),
            // id: 2
            Shape(
                3, 3, arrayOf(
                    "###".toCharArray(),
                    "..#".toCharArray(),
                    "..#".toCharArray(),
                )
            ),
            // id: 3
            Shape(
                1, 4, arrayOf(
                    "#".toCharArray(),
                    "#".toCharArray(),
                    "#".toCharArray(),
                    "#".toCharArray(),
                )
            ),
            // id: 4
            Shape(
                2, 2, arrayOf(
                    "##".toCharArray(),
                    "##".toCharArray(),
                )
            ),
        )

    }
}

data class Tile(
    var x: Long,
    var y: Long,
    val shapeId: Int,
) {
    fun overlap(other: Tile): Boolean {
        val endX : Long = x + shapes[shapeId].width - 1
        val otherEndX : Long = other.x + shapes[other.shapeId].width - 1
        val endY : Long = y + shapes[shapeId].height - 1
        val otherEndY : Long = other.y + shapes[other.shapeId].height - 1
        return x..endX overlaps other.x..otherEndX && y..endY overlaps other.y..otherEndY
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
    val body: Array<CharArray>
) {
    fun globalPoints(x: Long, y: Long): Set<LongPoint> {
        val globalPoints = mutableSetOf<LongPoint>()
        for (row in height - 1 downTo 0) {
            for (col in 0 until width) {
                if (body[row][col] == '#') {
                    globalPoints.add(LongPoint(x + col, y + row))
                }
            }
        }
        return globalPoints
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Shape

        if (width != other.width) return false
        if (height != other.height) return false
        if (!body.contentDeepEquals(other.body)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        result = 31 * result + body.contentDeepHashCode()
        return result
    }
}

fun main() {
    val pyroclasticFlow = PyroclasticFlow(Resources.resourceAsText("Day17_InputData.txt"))
    val towerHeight = pyroclasticFlow.tetris(2022)
    println("towerHeight = $towerHeight")
}