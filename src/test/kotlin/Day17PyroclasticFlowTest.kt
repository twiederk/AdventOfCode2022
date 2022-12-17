import PyroclasticFlow.Companion.shapes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PyroclasticFlowTest {

    private val pyroclasticFlow = PyroclasticFlow(Resources.resourceAsText("Day17_TestData.txt"))

    @Test
    fun loadData() {

        // assert
        assertThat(pyroclasticFlow.jetStream).isEqualTo(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>")
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
        assertThat(tile.y).isEqualTo(5)
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
        assertThat(tile.y).isEqualTo(8)
        assertThat(tile.shapeId).isEqualTo(2)
        assertThat(pyroclasticFlow.shapeId).isEqualTo(3)
    }

    @Test
    fun createNextTile_5() {
        // arrange
        pyroclasticFlow.towerHeight = 9
        pyroclasticFlow.shapeId = 4

        // act
        val tile = pyroclasticFlow.createNextTile()

        // assert
        assertThat(tile.x).isEqualTo(2)
        assertThat(tile.y).isEqualTo(13)
        assertThat(tile.shapeId).isEqualTo(4)
        assertThat(pyroclasticFlow.shapeId).isEqualTo(0)
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

    //   01234567
    // 6|......1|
    // 5|......1|
    // 4|...2111|
    // 3|..222..|
    // 2|...2...|
    // 1|..####.|
    // 0+-------+
    @Test
    fun jetMove_collideLeft() {
        // arrange
        pyroclasticFlow.restTiles.add(Tile(2, 4, 1))
        val tile = Tile(4, 6, 2)

        // act
        val x = pyroclasticFlow.jetMove(tile, '<')

        // assert
        assertThat(x).isEqualTo(tile.x)
    }

    //   01234567
    // 6|..1....|
    // 5|..1....|
    // 4|1112...|
    // 3|..222..|
    // 2|...2...|
    // 1|..####.|
    // 0+-------+
    @Test
    fun jetMove_collideRight() {
        // arrange
        pyroclasticFlow.restTiles.add(Tile(2, 4, 1))
        val tile = Tile(0, 6, 2)

        // act
        val x = pyroclasticFlow.jetMove(tile, '>')

        // assert
        assertThat(x).isEqualTo(tile.x)
    }

    //    |..@....|
    //    |.@@@...|
    //    |..@....|
    //    |..####.|
    //    +-------+
    @Test
    fun jetMove_error_1() {
        // arrange
        pyroclasticFlow.restTiles.add(Tile(2, 1, 0))
        val tile = Tile(1, 2, 1)

        // act
        val x = pyroclasticFlow.jetMove(tile, '>')

        // assert
        assertThat(x).isEqualTo(2)

    }

    //   01234567
    // 6|..1....|
    // 5|..1....|
    // 4|1112...|
    // 3|..222..|
    // 2|...2...|
    // 1|..####.|
    // 0+-------+
    @Test
    fun collide_1_false() {
        // arrange
        val tile1 = Tile(0, 6, 2)
        val tile2 = Tile(2, 4, 1)

        // act
        val collide = tile1.collide(tile2)

        // assert
        assertThat(collide).isFalse
    }

    //   01234567
    // 6|...1...|
    // 5|...1...|
    // 4|.11*..|
    // 3|..222..|
    // 2|...2...|
    // 1|..####.|
    // 0+-------+
    @Test
    fun collide_2_true() {
        // arrange
        val tile1 = Tile(1, 6, 2)
        val tile2 = Tile(2, 4, 1)

        // act
        val collide = tile1.collide(tile2)

        // assert
        assertThat(collide).isTrue
    }

    //   01234567
    // 6|.....1.|
    // 5|.....1.|
    // 4|...*11.|
    // 3|..222..|
    // 2|...2...|
    // 1|..####.|
    // 0+-------+
    @Test
    fun collide_3_true() {
        // arrange
        val tile1 = Tile(3, 6, 2)
        val tile2 = Tile(2, 4, 1)

        // act
        val collide = tile1.collide(tile2)

        // assert
        assertThat(collide).isTrue
    }

    //    |..@....|
//    |.@@@...|
//    |..@....|
//    |..####.|
//    +-------+
    @Test
    fun collide_error_1() {
        // arrange
        val tile1 = Tile(1, 2, 1)
        val tile2 = Tile(2, 1, 0)

        // act
        val collide = tile1.collide(tile2)

        // assert
        assertThat(collide).isFalse
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

    //    |..@....|
    //    |.@@@...|
    //    |..@....|
    //    |..####.|
    //    +-------+
    @Test
    fun tileOverlap_error_1() {
        // arrange
        val tile1 = Tile(1, 2, 1)
        val tile2 = Tile(2, 1, 0)

        // act
        val overlap = tile1.overlap(tile2)

        // assert
        assertThat(overlap).isFalse

    }

    @Test
    fun shapeGlobalPoints_shape0At00() {
        // act
        val globalPoints = shapes[0].globalPoints(0, 0)

        // assert
        assertThat(globalPoints).contains(
            Point(0, 0),
            Point(1, 0),
            Point(2, 0),
            Point(3, 0),
        )
    }

    @Test
    fun shapeGlobalPoints_shape0At11() {
        // act
        val globalPoints = shapes[0].globalPoints(1, 1)

        // assert
        assertThat(globalPoints).contains(
            Point(1, 1),
            Point(2, 1),
            Point(3, 1),
            Point(4, 1),
        )
    }

    @Test
    fun shapeGlobalPoints_shape1At00() {
        // act
        val globalPoints = shapes[1].globalPoints(0, 0)

        // assert
        assertThat(globalPoints).contains(
            Point(1, 0),
            Point(0, -1),
            Point(1, -1),
            Point(2, -1),
            Point(1, -2),
        )
    }

    @Test
    fun shapeGlobalPoints_shape1At22() {
        // act
        val globalPoints = shapes[1].globalPoints(2, 2)

        // assert
        assertThat(globalPoints).contains(
            Point(3, 0),
            Point(2, 1),
            Point(3, 1),
            Point(4, 1),
            Point(3, 2),
        )
    }


    @Test
    fun shapeGlobalPoints_shape3At00() {
        // act
        val globalPoints = shapes[3].globalPoints(0, 0)

        // assert
        assertThat(globalPoints).contains(
            Point(0, 0),
            Point(0, -1),
            Point(0, -2),
            Point(0, -3),
        )
    }

    @Test
    fun fallMove_free() {
        // arrange
        val tile = Tile(2, 4, 0)

        // act
        val y = pyroclasticFlow.fallMove(tile)

        // assert
        assertThat(y).isEqualTo(3)
    }

    @Test
    fun fallMove_ground() {
        // arrange
        val tile = Tile(2, 1, 0)

        // act
        val y = pyroclasticFlow.fallMove(tile)

        // assert
        assertThat(y).isEqualTo(1)
    }

    @Test
    fun fallMove_tile() {
        // arrange
        pyroclasticFlow.restTiles.add(Tile(2, 1, 0))
        val tile = Tile(2, 2, 0)

        // act
        val y = pyroclasticFlow.fallMove(tile)

        // assert
        assertThat(y).isEqualTo(2)
    }


    //    |...@...|
    //    |..@@@..|
    //    |...@...|
    //    |..####.|
    //    +-------+
    @Test
    fun fallMove_error_2() {
        // arrange
        pyroclasticFlow.restTiles.add(Tile(2,1,0))
        val tile2 = Tile(2,2,1)

        // act
        val y = pyroclasticFlow.fallMove(tile2)

        // assert
        assertThat(y).isEqualTo(2)
    }

    @Test
    fun getJet() {
        // act
        val jet = pyroclasticFlow.getJet()

        // assert
        assertThat(jet).isEqualTo('>')
        assertThat(pyroclasticFlow.jetCounter).isEqualTo(1)
    }

    @Test
    fun getJet_lastIndex() {
        // arrange
        pyroclasticFlow.jetCounter = pyroclasticFlow.jetStream.lastIndex

        // act
        val jet = pyroclasticFlow.getJet()

        // assert
        assertThat(jet).isEqualTo('>')
        assertThat(pyroclasticFlow.jetCounter).isEqualTo(0)
    }

    @Test
    fun tetris_block1() {
        pyroclasticFlow.debug = true

        // act
        val towerHeight = pyroclasticFlow.tetris(1)

        // assert
        assertThat(towerHeight).isEqualTo(1)
        assertThat(pyroclasticFlow.restTiles).containsExactly(Tile(2, 1, 0))
    }

    @Test
    fun tetris_block2() {
        pyroclasticFlow.debug = true

        // act
        val towerHeight = pyroclasticFlow.tetris(2)

        // assert
        assertThat(towerHeight).isEqualTo(4)
        assertThat(pyroclasticFlow.restTiles).containsExactly(
            Tile(2, 1, 0),
            Tile(2, 2, 1),
        )
    }

    @Test
    fun tetris_block3() {
        // act
        val towerHeight = pyroclasticFlow.tetris(3)

        // assert
        assertThat(towerHeight).isEqualTo(6)
    }

    @Test
    fun tetris_example() {
        // act
        val towerHeight = pyroclasticFlow.tetris(2022)

        // assert
        assertThat(towerHeight).isEqualTo(3068)
    }

}