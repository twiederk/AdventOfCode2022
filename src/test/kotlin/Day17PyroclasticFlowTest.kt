import PyroclasticFlow.Companion.shapes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PyroclasticFlowTest {

    private val pyroclasticFlow = PyroclasticFlow(Resources.resourceAsText("Day17_TestData.txt"))

    @Test
    fun loadData() {

        // assert
        assertThat(pyroclasticFlow.input).isEqualTo(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>")
    }

    @Test
    fun createNextTile_1() {

        // act
        val tile = pyroclasticFlow.createNextTile()

        // assert
        assertThat(tile.x).isEqualTo(2)
        assertThat(tile.y).isEqualTo(4)
        assertThat(tile.shapeId).isEqualTo(0)
        assertThat(pyroclasticFlow.shapeId).isEqualTo(1)
    }

    @Test
    fun createNextTile_2() {
        // arrange
        pyroclasticFlow.towerHeight = 1
        pyroclasticFlow.shapeId = 1

        // act
        val tile = pyroclasticFlow.createNextTile()

        // assert
        assertThat(tile.x).isEqualTo(2)
        assertThat(tile.y).isEqualTo(7)
        assertThat(tile.shapeId).isEqualTo(1)
        assertThat(pyroclasticFlow.shapeId).isEqualTo(2)
    }

    @Test
    fun createNextTile_3() {
        // arrange
        pyroclasticFlow.towerHeight = 4
        pyroclasticFlow.shapeId = 2

        // act
        val tile = pyroclasticFlow.createNextTile()

        // assert
        assertThat(tile.x).isEqualTo(2)
        assertThat(tile.y).isEqualTo(10)
        assertThat(tile.shapeId).isEqualTo(2)
        assertThat(pyroclasticFlow.shapeId).isEqualTo(3)
    }

    @Test
    fun jetMove_rightFree() {
        // arrange
        val tile = Tile(2, 4, 0)

        // act
        val x = pyroclasticFlow.jetMove(tile, '>')

        // assert
        assertThat(x).isEqualTo(3)
    }

    @Test
    fun jetMove_rightWall() {
        // arrange
        val tile = Tile(3, 4, 0)

        // act
        val x = pyroclasticFlow.jetMove(tile, '>')

        // assert
        assertThat(x).isEqualTo(3)
    }

    @Test
    fun jetMove_leftFree() {
        // arrange
        val tile = Tile(2, 4, 0)

        // act
        val x = pyroclasticFlow.jetMove(tile, '<')

        // assert
        assertThat(x).isEqualTo(1)
    }

    @Test
    fun jetMove_leftWall() {
        // arrange
        val tile = Tile(0, 4, 0)

        // act
        val x = pyroclasticFlow.jetMove(tile, '<')

        // assert
        assertThat(x).isEqualTo(0)
    }

    // |.......|
    // |..1....|
    // |..1....|
    // |1112...|
    // |..222..|
    // |...2...|
    // |..####.|
    // +-------+
    @Test
    fun collide_false() {
        // arrange
        val tile1 = Tile(0, 4, 2)
        val tile2 = Tile(2, 4, 1)

        // act
        val collide = tile1.collide(tile2)

        // assert
        assertThat(collide).isFalse
    }

    // |.......|
    // |...1...|
    // |...1...|
    // |.11*...|
    // |..222..|
    // |...2...|
    // |..####.|
    // +-------+
    @Test
    fun collide_true() {
        // arrange
        val tile1 = Tile(1, 4, 2)
        val tile2 = Tile(2, 4, 1)

        // act
        val collide = tile1.collide(tile2)

        // assert
        assertThat(collide).isTrue
    }

    @Test
    fun tileOverlap_false() {
        // arrange
        val tile1 = Tile(1, 0, 2)
        val tile2 = Tile(2, 4, 1)

        // act
        val overlap = tile1.overlap(tile2)

        // assert
        assertThat(overlap).isFalse
    }

    @Test
    fun tileOverlap_true() {
        // arrange
        val tile1 = Tile(1, 4, 2)
        val tile2 = Tile(2, 4, 1)

        // act
        val overlap = tile1.overlap(tile2)

        // assert
        assertThat(overlap).isTrue
    }


    @Test
    fun shapeGlobalPoints_shape0At00() {
        // act
        val globalPoints = shapes[0].globalPoints(0, 0)

        // assert
        assertThat(globalPoints).contains(
            Point(0,0),
            Point(1,0),
            Point(2,0),
            Point(3,0),
        )
    }

    @Test
    fun shapeGlobalPoints_shape0At11() {
        // act
        val globalPoints = shapes[0].globalPoints(1, 1)

        // assert
        assertThat(globalPoints).contains(
            Point(1,1),
            Point(2,1),
            Point(3,1),
            Point(4,1),
        )
    }

    @Test
    fun shapeGlobalPoints_shape1At00() {
        // act
        val globalPoints = shapes[1].globalPoints(0, 0)

        // assert
        assertThat(globalPoints).contains(
            Point(1,0),
            Point(0,1),
            Point(1,1),
            Point(2,1),
            Point(1,2),
        )
    }

    @Test
    fun shapeGlobalPoints_shape1At22() {
        // act
        val globalPoints = shapes[1].globalPoints(2, 2)

        // assert
        assertThat(globalPoints).contains(
            Point(3,2),
            Point(2,3),
            Point(3,3),
            Point(4,3),
            Point(3,4),
        )
    }

}