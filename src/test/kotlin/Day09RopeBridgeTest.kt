import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class RopeBridgeTest {

    // + loadData
    // + parse RopeCommand
    // + move head
    // + check adjacent
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
        assertThat(ropeBridge.head.second).isEqualTo(1)
    }

    @Test
    fun moveHead_down() {
        // arrange
        val ropeBridge = RopeBridge()

        // act
        ropeBridge.moveHead("D")

        // assert
        assertThat(ropeBridge.head.first).isEqualTo(0)
        assertThat(ropeBridge.head.second).isEqualTo(-1)
    }

    @Test
    fun isAdjacent_coverEachOther_true() {
        // arrange
        val ropeBridge = RopeBridge()

        // act
        val adjacent = ropeBridge.isAdjacent()

        // assert
        assertThat(adjacent).isTrue
    }

    @Test
    fun isAdjacent_oneToTheRight_true() {
        // arrange
        val ropeBridge = RopeBridge()
        ropeBridge.head = Pair(1, 0)

        // act
        val adjacent = ropeBridge.isAdjacent()

        // assert
        assertThat(adjacent).isTrue
    }

    @Test
    fun isAdjacent_oneToTheRightAndDown_true() {
        // arrange
        val ropeBridge = RopeBridge()
        ropeBridge.head = Pair(1, 1)

        // act
        val adjacent = ropeBridge.isAdjacent()

        // assert
        assertThat(adjacent).isTrue
    }

    @Test
    fun isAdjacent_twoToTheRight_false() {
        // arrange
        val ropeBridge = RopeBridge()
        ropeBridge.head = Pair(2, 0)

        // act
        val adjacent = ropeBridge.isAdjacent()

        // assert
        assertThat(adjacent).isFalse
    }

    @Test
    fun moveTail_headIsTwoToRight() {
        // arrange
        val ropeBridge = RopeBridge()
        ropeBridge.head = Pair(2, 0)

        // act
        ropeBridge.moveTail()

        // assert
        assertThat(ropeBridge.tail).isEqualTo(Pair(1, 0))
    }

    @Test
    fun moveTail_headIsTwoToLeft() {
        // arrange
        val ropeBridge = RopeBridge()
        ropeBridge.head = Pair(-2, 0)

        // act
        ropeBridge.moveTail()

        // assert
        assertThat(ropeBridge.tail).isEqualTo(Pair(-1, 0))
    }

    @Test
    fun moveTail_headIsTwoDown() {
        // arrange
        val ropeBridge = RopeBridge()
        ropeBridge.head = Pair(0, -2)

        // act
        ropeBridge.moveTail()

        // assert
        assertThat(ropeBridge.tail).isEqualTo(Pair(0, -1))
    }

    @Test
    fun moveTail_headIsTwoUp() {
        // arrange
        val ropeBridge = RopeBridge()
        ropeBridge.head = Pair(0, 2)

        // act
        ropeBridge.moveTail()

        // assert
        assertThat(ropeBridge.tail).isEqualTo(Pair(0, 1))
    }

    @Test
    fun moveTail_headIsOneRightTwoUp() {
        // arrange
        val ropeBridge = RopeBridge()
        ropeBridge.head = Pair(1, 2)

        // act
        ropeBridge.moveTail()

        // assert
        assertThat(ropeBridge.tail).isEqualTo(Pair(1, 1))
    }

    @Test
    fun executeRopeCommands_firstCommand() {
        // arrange
        val ropeBridge = RopeBridge()
        val ropeCommands = listOf(RopeCommand("R", 4))

        // act
        ropeBridge.executeRopeCommands(ropeCommands)

        // assert
        assertThat(ropeBridge.head).isEqualTo(Pair(4, 0))
        assertThat(ropeBridge.tail).isEqualTo(Pair(3, 0))
    }

    @Test
    fun executeRopeCommands_secondCommand() {
        // arrange
        val ropeBridge = RopeBridge()
        val ropeCommands = listOf(
            RopeCommand("R", 4),
            RopeCommand("U", 4),
        )

        // act
        ropeBridge.executeRopeCommands(ropeCommands)

        // assert
        assertThat(ropeBridge.head).isEqualTo(Pair(4, 4))
        assertThat(ropeBridge.tail).isEqualTo(Pair(4, 3))
    }

    @Test
    fun executeRopeCommand_moveWholeTestData() {
        // arrange
        val ropeBridge = RopeBridge()
        val rawData = ropeBridge.loadData(Path("src", "test", "resources", "Day09_TestData.txt"))
        val ropeCommands = ropeBridge.parseRopeCommands(rawData)

        // act
        ropeBridge.executeRopeCommands(ropeCommands)

        // assert
        assertThat(ropeBridge.head).isEqualTo(Pair(2, 2))
        assertThat(ropeBridge.tail).isEqualTo(Pair(1, 2))
    }

    @Test
    fun moveTail_diagonal1() {
        // arrange
        val ropeBridge = RopeBridge()
        ropeBridge.head = Pair(2, 3)
        ropeBridge.tail = Pair(1, 1)

        // act
        ropeBridge.moveTail()

        // assert
        assertThat(ropeBridge.tail).isEqualTo(Pair(2, 2))

    }

    @Test
    fun moveTail_diagonal2() {
        // arrange
        val ropeBridge = RopeBridge()
        ropeBridge.head = Pair(3, 2)
        ropeBridge.tail = Pair(2, 2)

        // act
        ropeBridge.moveTail()

        // assert
        assertThat(ropeBridge.tail).isEqualTo(Pair(2, 2))

    }

}