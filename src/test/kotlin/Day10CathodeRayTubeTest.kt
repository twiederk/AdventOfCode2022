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
    fun cycle_smallProgramm() {
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

}