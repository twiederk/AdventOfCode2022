import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class RegolithReservoirTest {

    // + create/render all rocks (lines)
    // need a canvas (cave)
    // find lowest and highest x value
    // find lowest and highest y value
    // implement logic of falling sand.
    // sand drops endless if max y is exceeded

    private val regolithReservoir = RegolithReservoir()

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
        // arrange
        val rawData = regolithReservoir.loadData(Path("src", "test", "resources", "Day14_TestData.txt"))
        val lines = regolithReservoir.createLines(rawData)

        // act
        val maxX = regolithReservoir.maxX(lines)

        // assert
        assertThat(maxX).isEqualTo(503)
    }

    @Test
    fun maxY() {
        // arrange
        val rawData = regolithReservoir.loadData(Path("src", "test", "resources", "Day14_TestData.txt"))
        val lines = regolithReservoir.createLines(rawData)

        // act
        val maxY = regolithReservoir.maxY(lines)

        // assert
        assertThat(maxY).isEqualTo(9)
    }
}