import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class RopeBridgeTest {

    // loadData
    // parse RopeCommand
    // move head
    // check adjacent
    // if not adjacent move tail
    // store position the tail visited at least once

    @Test
    fun loadData() {
        // act
        val rawData = RopeBridge().loadData(Path("src", "test", "resources", "Day09_TestData.txt"))

        // assert
        assertThat(rawData).hasSize(8)
    }

    @Test
    fun parseRopeCommand() {
        // act
        val ropeCommand = RopeBridge().parseRopeCommand("R 19")

        // assert
        assertThat(ropeCommand.direction).isEqualTo("R")
        assertThat(ropeCommand.moves).isEqualTo(19)
    }

    @Test
    fun parseRopeCommands() {
        // arrange
        val rawData = RopeBridge().loadData(Path("src", "test", "resources", "Day09_TestData.txt"))

        // act
        val ropeCommands = RopeBridge().parseRopeCommands(rawData)

        // assert
        assertThat(ropeCommands).hasSize(8)
    }

}