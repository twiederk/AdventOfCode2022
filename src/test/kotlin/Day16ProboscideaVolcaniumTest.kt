import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProboscideaVolcaniumTest {

    private val proboscideaVolcanium = ProboscideaVolcanium()

    @Test
    fun loadData() {

        // act
        val rawData = proboscideaVolcanium.loadData("Day16_TestData.txt")

        // assert
        assertThat(rawData).hasSize(10)
    }

    @Test
    fun createValves() {
        // arrange
        val rawData = proboscideaVolcanium.loadData("Day16_TestData.txt")

        // act
        val valves = proboscideaVolcanium.createValves(rawData)

        // assert
        assertThat(valves).hasSize(10)
        assertThat(valves["AA"]?.flowRate).isEqualTo(0)
    }

    @Test
    fun addNeighbors() {
        // arrange
        val rawData = proboscideaVolcanium.loadData("Day16_TestData.txt")
        val valves = proboscideaVolcanium.createValves(rawData)

        // act
        proboscideaVolcanium.addNeighbors(rawData, valves)

        // assert
        assertThat(valves.getValue("AA").neighbors).hasSize(3)
        assertThat(valves.getValue("HH").neighbors).hasSize(1)
    }

    @Test
    fun solve1_example() {
        // arrange
        val rawData = Resources.resourceAsText("Day16_TestData.txt")

        // act
        val result = Day16(rawData).solve1()

        // assert
        assertThat(result).isEqualTo(1651)
    }

    @Test
    fun solve2_example() {
        // arrange
        val rawData = Resources.resourceAsText("Day16_TestData.txt")

        // act
        val result = Day16(rawData).solve2()

        // assert
        assertThat(result).isEqualTo(1707)
    }

}