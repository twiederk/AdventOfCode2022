import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class CathodeRayTubeTest {

    private val cathodeRayTube = CathodeRayTube()

    @Test
    fun loadData() {

        // act
        val rawData = cathodeRayTube.loadData(Path("src", "test", "resources", "Day10_TestData.txt"))

        // assert
        assertThat(rawData).hasSize(146)
    }

    @Test
    fun cycle_noop() {

        // act
        val cycles = cathodeRayTube.cycle("noop")

        // assert
        assertThat(cycles).containsExactly(1)
    }

    @Test
    fun cycle_addx3() {

        // act
        val cycles = cathodeRayTube.cycle("addx 3")

        // assert
        assertThat(cycles).containsExactly(1, 1)
        assertThat(cathodeRayTube.X).isEqualTo(4)
    }

    @Test
    fun cycle_addMinus5() {

        // act
        val cycles = cathodeRayTube.cycle("addx -5")

        // assert
        assertThat(cycles).containsExactly(1, 1)
        assertThat(cathodeRayTube.X).isEqualTo(-4)
    }

    @Test
    fun cycles_smallProgramm() {
        // arrange
        val instructions = listOf(
            "noop",
            "addx 3",
            "addx -5"
        )

        // act
        val cycles = cathodeRayTube.cycles(instructions)

        // assert
        assertThat(cycles).containsExactly(1, 1, 1, 4, 4)
        assertThat(cathodeRayTube.X).isEqualTo(-1)
    }

    @Test
    fun cycles_testProgramm() {
        // arrange
        val instructions = cathodeRayTube.loadData(Path("src", "test", "resources", "Day10_TestData.txt"))

        // act
        val cycles = cathodeRayTube.cycles(instructions)

        // assert
        assertThat(cycles[19]).isEqualTo(21)
        assertThat(cycles[59]).isEqualTo(19)
        assertThat(cycles[99]).isEqualTo(18)
        assertThat(cycles[139]).isEqualTo(21)
        assertThat(cycles[179]).isEqualTo(16)
        assertThat(cycles[219]).isEqualTo(18)
    }

    @Test
    fun totalSignalStrength() {
        // arrange
        val instructions = cathodeRayTube.loadData(Path("src", "test", "resources", "Day10_TestData.txt"))
        val cycles = cathodeRayTube.cycles(instructions)

        // act
        val totalSignalStrength = cathodeRayTube.totalSignalStrength(cycles)

        // assert
        assertThat(totalSignalStrength).isEqualTo(13140)
    }

    @Test
    fun renderCycle_1() {
        // arrange
        val instructions = cathodeRayTube.loadData(Path("src", "test", "resources", "Day10_TestData.txt"))
        val cycles = cathodeRayTube.cycles(instructions)

        // act
        val pixel = cathodeRayTube.renderCycle(1, cycles[0])

        // assert
        assertThat(pixel).isEqualTo('#')
    }

    @Test
    fun renderCycle_2() {
        // arrange
        val instructions = cathodeRayTube.loadData(Path("src", "test", "resources", "Day10_TestData.txt"))
        val cycles = cathodeRayTube.cycles(instructions)

        // act
        val pixel = cathodeRayTube.renderCycle(2, cycles[1])

        // assert
        assertThat(pixel).isEqualTo('#')
    }

    @Test
    fun renderCycle_3() {
        // arrange
        val instructions = cathodeRayTube.loadData(Path("src", "test", "resources", "Day10_TestData.txt"))
        val cycles = cathodeRayTube.cycles(instructions)

        // act
        val pixel = cathodeRayTube.renderCycle(3, cycles[2])

        // assert
        assertThat(pixel).isEqualTo('.')
    }

    @Test
    fun renderCycles() {
        // arrange
        val instructions = cathodeRayTube.loadData(Path("src", "test", "resources", "Day10_TestData.txt"))
        val cycles = cathodeRayTube.cycles(instructions)

        // act
        val display = cathodeRayTube.renderCycles(cycles)

        // assert
        assertThat(display).isEqualTo("""##..##..##..##..##..##..##..##..##..##..
            |###...###...###...###...###...###...###.
            |####....####....####....####....####....
            |#####.....#####.....#####.....#####.....
            |######......######......######......####
            |#######.......#######.......#######.....""".trimMargin())

    }
}