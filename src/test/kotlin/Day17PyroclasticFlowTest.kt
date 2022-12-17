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
    //   01234567
    @Test
    fun jetMove_collideLeft() {
        // arrange
        pyroclasticFlow.restTiles.add(Tile(2, 2, 1))
        val tile = Tile(4, 4, 2)

        // act
        val x = pyroclasticFlow.jetMove(tile, '<')

        // assert
        assertThat(x).isEqualTo(4)
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
        pyroclasticFlow.restTiles.add(Tile(2, 2, 1))
        val tile = Tile(0, 4, 2)

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

    @Test
    fun jetMove_error_3() {
        // arrange
        val tile = Tile(x = 1, y = 5, shapeId = 2)

        // act
        val x = pyroclasticFlow.jetMove(tile, '<')

        // assert
        assertThat(x).isEqualTo(0)
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
        val tile1 = Tile(1, 4, 2)
        val tile2 = Tile(2, 2, 1)

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

    //    01234567
    // 6|.....1.|
    // 5|.....1.|
    // 4|...X11.|
    // 3|..222..|
    // 2|...2...|
    // 1|..####.|
    // 0+-------+
    //  01234567

    @Test
    fun collide_error_5() {
        // arrange
        val tile1 = Tile(2, 2, 1)
        val tile2 = Tile(3, 4, 2)

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
            LongPoint(0, 0),
            LongPoint(1, 0),
            LongPoint(2, 0),
            LongPoint(3, 0),
        )
    }

    @Test
    fun shapeGlobalPoints_shape0At11() {
        // act
        val globalPoints = shapes[0].globalPoints(1, 1)

        // assert
        assertThat(globalPoints).contains(
            LongPoint(1, 1),
            LongPoint(2, 1),
            LongPoint(3, 1),
            LongPoint(4, 1),
        )
    }

    @Test
    fun shapeGlobalPoints_shape1At00() {
        // act
        val globalPoints = shapes[1].globalPoints(0, 0)

        // assert
        assertThat(globalPoints).contains(
            LongPoint(1, 0),
            LongPoint(0, 1),
            LongPoint(1, 1),
            LongPoint(2, 1),
            LongPoint(1, 2),
        )
    }

    @Test
    fun shapeGlobalPoints_shape1At22() {
        // act
        val globalPoints = shapes[1].globalPoints(2, 2)

        // assert
        assertThat(globalPoints).contains(
            LongPoint(3, 2),
            LongPoint(2, 3),
            LongPoint(3, 3),
            LongPoint(4, 3),
            LongPoint(3, 4),
        )
    }

    @Test
    fun shapeGlobalPoints_shape2At00() {
        // act
        val globalPoints = shapes[2].globalPoints(0, 0)

        // assert
        assertThat(globalPoints).contains(
            LongPoint(0, 0),
            LongPoint(1, 0),
            LongPoint(2, 0),
            LongPoint(2, 1),
            LongPoint(2, 2),
        )
    }


    @Test
    fun shapeGlobalPoints_shape3At00() {
        // act
        val globalPoints = shapes[3].globalPoints(0, 0)

        // assert
        assertThat(globalPoints).contains(
            LongPoint(0, 0),
            LongPoint(0, 1),
            LongPoint(0, 2),
            LongPoint(0, 3),
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
        pyroclasticFlow.restTiles.add(Tile(2, 1, 0))
        val tile2 = Tile(2, 2, 1)

        // act
        val y = pyroclasticFlow.fallMove(tile2)

        // assert
        assertThat(y).isEqualTo(2)
    }

    @Test
    fun fallMove_error_4() {
        // arrange
        pyroclasticFlow.restTiles.add(Tile(2, 1, 0))
        pyroclasticFlow.restTiles.add(Tile(2, 2, 1))
        pyroclasticFlow.restTiles.add(Tile(0, 4, 2))
        val tile = Tile(4, 5, 3)

        // act
        val y = pyroclasticFlow.fallMove(tile)

        // assert
        assertThat(y).isEqualTo(4)
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

//    |..1111.|
//    +-------+
    @Test
    fun tetris_block1() {

        // act
        val towerHeight = pyroclasticFlow.tetris(1)

        // assert
        assertThat(towerHeight).isEqualTo(1)
        assertThat(pyroclasticFlow.restTiles).containsExactly(Tile(2, 1, 0))
    }

    //    |...2...|
    //    |..222..|
    //    |...2...|
    //    |..1111.|
    //    +-------+
    @Test
    fun tetris_block2() {

        // act
        val towerHeight = pyroclasticFlow.tetris(2)

        // assert
        assertThat(towerHeight).isEqualTo(4)
        assertThat(pyroclasticFlow.restTiles).containsExactly(
            Tile(2, 1, 0),
            Tile(2, 2, 1),
        )
    }

    //    |.......|
    //    |..3....|
    //    |..3....|
    //    |3332...|
    //    |..222..|
    //    |...2...|
    //    |..1111.|
    //    +-------+
    @Test
    fun tetris_block3() {

        // act
        val towerHeight = pyroclasticFlow.tetris(3)

        // assert
        assertThat(towerHeight).isEqualTo(6)
        assertThat(pyroclasticFlow.restTiles).containsExactly(
            Tile(2, 1, 0),
            Tile(2, 2, 1),
            Tile(0, 4, 2),
        )
    }

    //    |.......|
    //    |....4..|
    //    |..3.4..|
    //    |..3.4..|
    //    |33324..|
    //    |..222..|
    //    |...2...|
    //    |..1111.|
    //    +-------+
    @Test
    fun tetris_block4() {

        // act
        val towerHeight = pyroclasticFlow.tetris(4)

        // assert
        assertThat(towerHeight).isEqualTo(7)
        assertThat(pyroclasticFlow.restTiles).containsExactly(
            Tile(2, 1, 0),
            Tile(2, 2, 1),
            Tile(0, 4, 2),
            Tile(4, 4, 3),
        )
    }

    //    |.......|
    //    |....55.|
    //    |....55.|
    //    |....4..|
    //    |..3.4..|
    //    |..3.4..|
    //    |33324..|
    //    |..222..|
    //    |...2...|
    //    |..1111.|
    //    +-------+
    @Test
    fun tetris_block5() {

        // act
        val towerHeight = pyroclasticFlow.tetris(5)

        // assert
        assertThat(towerHeight).isEqualTo(9)
        assertThat(pyroclasticFlow.restTiles).containsExactly(
            Tile(2, 1, 0),
            Tile(2, 2, 1),
            Tile(0, 4, 2),
            Tile(4, 4, 3),
            Tile(4, 8, 4),
        )
    }

    //    |.......|
    //    |.6666..|
    //    |....55.|
    //    |....55.|
    //    |....4..|
    //    |..3.4..|
    //    |..3.4..|
    //    |33324..|
    //    |..222..|
    //    |...2...|
    //    |..1111.|
    //    +-------+
    @Test
    fun tetris_block6() {

        // act
        val towerHeight = pyroclasticFlow.tetris(6)

        // assert
        assertThat(towerHeight).isEqualTo(10)
        assertThat(pyroclasticFlow.restTiles).containsExactly(
            Tile(2, 1, 0),
            Tile(2, 2, 1),
            Tile(0, 4, 2),
            Tile(4, 4, 3),
            Tile(4, 8, 4),
            Tile(1, 10, 0),
        )
    }

    //    |.......|
    //    |..7....|
    //    |.777...|
    //    |..7....|
    //    |.6666..|
    //    |....55.|
    //    |....55.|
    //    |....4..|
    //    |..3.4..|
    //    |..3.4..|
    //    |33324..|
    //    |..222..|
    //    |...2...|
    //    |..1111.|
    //    +-------+
    @Test
    fun tetris_block7() {

        // act
        val towerHeight = pyroclasticFlow.tetris(7)

        // assert
        assertThat(towerHeight).isEqualTo(13)
        assertThat(pyroclasticFlow.restTiles).containsExactly(
            Tile(2, 1, 0),
            Tile(2, 2, 1),
            Tile(0, 4, 2),
            Tile(4, 4, 3),
            Tile(4, 8, 4),
            Tile(1, 10, 0),
            Tile(1, 11, 1),
        )
    }

    //    |.......|
    //    |.......|
    //    |.....8.|
    //    |.....8.|
    //    |..7888.|
    //    |.777...|
    //    |..7....|
    //    |.6666..|
    //    |....55.|
    //    |....55.|
    //    |....4..|
    //    |..3.4..|
    //    |..3.4..|
    //    |33324..|
    //    |..222..|
    //    |...2...|
    //    |..1111.|
    //    +-------+
    @Test
    fun tetris_block8() {

        // act
        val towerHeight = pyroclasticFlow.tetris(8)

        // assert
        assertThat(towerHeight).isEqualTo(15)
        assertThat(pyroclasticFlow.restTiles).containsExactly(
            Tile(2, 1, 0),
            Tile(2, 2, 1),
            Tile(0, 4, 2),
            Tile(4, 4, 3),
            Tile(4, 8, 4),
            Tile(1, 10, 0),
            Tile(1, 11, 1),
            Tile(3, 13, 2),
        )
    }

    //    |.......|
    //    |....9..|
    //    |....9..|
    //    |....98.|
    //    |....98.|
    //    |..7888.|
    //    |.777...|
    //    |..7....|
    //    |.6666..|
    //    |....55.|
    //    |....55.|
    //    |....4..|
    //    |..3.4..|
    //    |..3.4..|
    //    |33324..|
    //    |..222..|
    //    |...2...|
    //    |..1111.|
    //    +-------+
    @Test
    fun tetris_block9() {

        // act
        val towerHeight = pyroclasticFlow.tetris(9)

        // assert
        assertThat(towerHeight).isEqualTo(17)
        assertThat(pyroclasticFlow.restTiles).containsExactly(
            Tile(2, 1, 0),
            Tile(2, 2, 1),
            Tile(0, 4, 2),
            Tile(4, 4, 3),
            Tile(4, 8, 4),
            Tile(1, 10, 0),
            Tile(1, 11, 1),
            Tile(3, 13, 2),
            Tile(4, 14, 3),
        )
    }

    //    |.......|
    //    |....9..|
    //    |....9..|
    //    |....98.|
    //    |aa..98.|
    //    |aa7888.|
    //    |.777...|
    //    |..7....|
    //    |.6666..|
    //    |....55.|
    //    |....55.|
    //    |....4..|
    //    |..3.4..|
    //    |..3.4..|
    //    |33324..|
    //    |..222..|
    //    |...2...|
    //    |..1111.|
    //    +-------+
    @Test
    fun tetris_block10() {

        // act
        val towerHeight = pyroclasticFlow.tetris(10)

        // assert
        assertThat(towerHeight).isEqualTo(17)
        assertThat(pyroclasticFlow.restTiles).containsExactly(
            Tile(2, 1, 0),
            Tile(2, 2, 1),
            Tile(0, 4, 2),
            Tile(4, 4, 3),
            Tile(4, 8, 4),
            Tile(1, 10, 0),
            Tile(1, 11, 1),
            Tile(3, 13, 2),
            Tile(4, 14, 3),
            Tile(0, 13, 4),
        )
    }

    @Test
    fun tetris_example_part1() {
        // act
        val towerHeight = pyroclasticFlow.tetris(2022)

        // assert
        assertThat(towerHeight).isEqualTo(3068)
    }

    @Test
    fun tetris_example_part2() {
        // act
        val towerHeight = pyroclasticFlow.tetris(1_000_000_000_000)

        // assert
        assertThat(towerHeight).isEqualTo(1_514_285_714_288)
    }

}