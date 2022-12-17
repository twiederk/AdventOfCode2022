import org.assertj.core.api.Assertions.*
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

}