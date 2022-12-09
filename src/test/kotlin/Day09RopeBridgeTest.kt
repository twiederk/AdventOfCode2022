import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class RopeBridgeTest {

    // + loadData
    // + parse RopeCommand
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

    @Test
    fun moveHead_right() {
        // arrange
        val ropeBridge = RopeBridge()

        // act
        ropeBridge.moveHead("R")

        // assert
        assertThat(ropeBridge.head.first).isEqualTo(1)
        assertThat(ropeBridge.head.second).isEqualTo(0)
    }

    @Test
    fun moveHead_left() {
        // arrange
        val ropeBridge = RopeBridge()

        // act
        ropeBridge.moveHead("L")

        // assert
        assertThat(ropeBridge.head.first).isEqualTo(-1)
        assertThat(ropeBridge.head.second).isEqualTo(0)
    }

    @Test
    fun moveHead_up() {
        // arrange
        val ropeBridge = RopeBridge()

        // act
        ropeBridge.moveHead("U")

        // assert
        assertThat(ropeBridge.head.first).isEqualTo(0)
        assertThat(ropeBridge.head.second).isEqualTo(-1)
    }

    @Test
    fun moveHead_down() {
        // arrange
        val ropeBridge = RopeBridge()

        // act
        ropeBridge.moveHead("D")

        // assert
        assertThat(ropeBridge.head.first).isEqualTo(0)
        assertThat(ropeBridge.head.second).isEqualTo(1)
    }

}