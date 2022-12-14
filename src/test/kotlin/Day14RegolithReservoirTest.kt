import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class RegolithReservoirTest {

    // + create/render all rocks (lines)
    // + need a canvas (cave)
    // + find lowest and highest x value
    // + find lowest and highest y value
    // implement logic of falling sand.
    // sand drops endless if max y is exceeded

    private val regolithReservoir = RegolithReservoir()
    private val lines = listOf(
        Line(498,4, 498,6),
        Line(498,6, 496,6),
        Line(503,4, 502,4),
        Line(502,4, 502,9),
        Line(502,9, 494,9),
    )

    @Test
    fun loadData() {

        // act
        val rawData = regolithReservoir.loadData(Path("src", "test", "resources", "Day14_TestData.txt"))

        // assert
        assertThat(rawData).hasSize(2)
    }

    @Test
    fun createLines() {
        // arrange
        val rawData = regolithReservoir.loadData(Path("src", "test", "resources", "Day14_TestData.txt"))

        // act
        val lines = regolithReservoir.createLines(rawData)

        // assert
        assertThat(lines).containsExactly(
            Line(498,4, 498,6),
            Line(498,6, 496,6),
            Line(503,4, 502,4),
            Line(502,4, 502,9),
            Line(502,9, 494,9),
        )
    }

    @Test
    fun maxX() {

        // act
        val maxX = regolithReservoir.maxX(lines)

        // assert
        assertThat(maxX).isEqualTo(503)
    }

    @Test
    fun maxY() {

        // act
        val maxY = regolithReservoir.maxY(lines)

        // assert
        assertThat(maxY).isEqualTo(9)
    }

    @Test
    fun createCave() {

        // act
        val cave = regolithReservoir.createCave(lines)

        // assert
        assertThat(cave[0]).hasSize(504)
        assertThat(cave.size).isEqualTo(10)

        assertThat(cave[4][498]).isEqualTo('#')
        assertThat(cave[5][498]).isEqualTo('#')
        assertThat(cave[6][498]).isEqualTo('#')
        assertThat(cave[6][497]).isEqualTo('#')
        assertThat(cave[6][496]).isEqualTo('#')
    }

    @Test
    fun createCaveWithFloor() {

        // act
        val cave = regolithReservoir.createCaveWithFloor(lines)

        // assert
        assertThat(cave[0]).hasSize(504)
        assertThat(cave.size).isEqualTo(12)

        assertThat(cave[11][0]).isEqualTo('#')
        assertThat(cave[11][503]).isEqualTo('#')
    }

    @Test
    fun renderCave() {

        // arrange
        val cave = regolithReservoir.createCave(lines)

        // act
        val display = regolithReservoir.renderCave(cave, startX = 494, endX = 504)

        // assert
        assertThat(display).isEqualTo(
            """
            ..........
            ..........
            ..........
            ..........
            ....#...##
            ....#...#.
            ..###...#.
            ........#.
            ........#.
            #########.
            
            """.trimIndent())
    }

    @Test
     fun fallingDown_down() {
        // arrange
        val cave = regolithReservoir.createCave(lines)
        val startPosition = Position(500, 0)

        // act
        val nextPosition = regolithReservoir.fallingDown(cave, startPosition)

        // assert
        assertThat(nextPosition).isEqualTo(Position(500, 1))
    }

    @Test
     fun fallingDown_downLeft() {
        // arrange
        val cave = regolithReservoir.createCave(lines)
        val startPosition = Position(502, 3)

        // act
        val nextPosition = regolithReservoir.fallingDown(cave, startPosition)

        // assert
        assertThat(nextPosition).isEqualTo(Position(501, 4))
    }

    @Test
     fun fallingDown_downRight() {
        // arrange
        val cave = regolithReservoir.createCave(lines)
        cave[4][497] = '#'
        val startPosition = Position(498, 3)

        // act
        val nextPosition = regolithReservoir.fallingDown(cave, startPosition)

        // assert
        assertThat(nextPosition).isEqualTo(Position(499, 4))
    }

    @Test
     fun fallingDown_rest() {
        // arrange
        val cave = regolithReservoir.createCave(lines)
        val startPosition = Position(500, 8)

        // act
        val nextPosition = regolithReservoir.fallingDown(cave, startPosition)

        // assert
        assertThat(nextPosition).isEqualTo(Position(500, 8))
    }

    @Test
    fun fallingDown_outOfBounds() {
        // arrange
        val cave = regolithReservoir.createCave(lines)
        val startPosition = Position(493, 9)

        // act
        val nextPosition = regolithReservoir.fallingDown(cave, startPosition)

        // assert
        assertThat(nextPosition).isEqualTo(Position(-1, -1))
    }

    @Test
    fun fallingToRest_sand_1() {
        // arrange
        val cave = regolithReservoir.createCave(lines)
        val startPosition = Position(500, 0)

        // act
        val restPosition = regolithReservoir.fallingToRest(cave, startPosition)

        // assert
        assertThat(restPosition).isEqualTo(Position(500, 8))
        assertThat(cave[restPosition.second][restPosition.first]).isEqualTo('o')
    }

    @Test
    fun fallingToRest_sand_2() {
        // arrange
        val cave = regolithReservoir.createCave(lines)
        val startPosition = Position(500, 0)
        regolithReservoir.fallingToRest(cave, startPosition)

        // act
        val restPosition = regolithReservoir.fallingToRest(cave, startPosition)

        // assert
        assertThat(restPosition).isEqualTo(Position(499, 8))
        assertThat(cave[restPosition.second][restPosition.first]).isEqualTo('o')
    }

    @Test
    fun drizzle_noFloor() {
        // arrange
        val cave = regolithReservoir.createCave(lines)
        val startPosition = Position(500, 0)

        // act
        val unitOfSand = regolithReservoir.drizzle(cave, startPosition)

        // assert
        assertThat(unitOfSand).isEqualTo(24)
    }

    @Test
    fun drizzle_floor() {
        // arrange
        val cave = regolithReservoir.createCaveWithFloor(lines)
        val startPosition = Position(500, 0)

        // act
        val unitOfSand = regolithReservoir.drizzle(cave, startPosition)

        // assert
        println(regolithReservoir.renderCave(cave, startX = 488, endX = 513 ))
        assertThat(unitOfSand).isEqualTo(93)
    }


}