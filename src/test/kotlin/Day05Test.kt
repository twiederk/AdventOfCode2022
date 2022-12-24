import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.io.path.Path

class SupplyStacksTest {

    lateinit var storage: List<Stack<Char>>

    @BeforeEach
    fun beforeEach() {
        // arrange
        val stack1 = Stack<Char>()
        stack1.push('Z')
        stack1.push('N')

        val stack2 = Stack<Char>()
        stack2.push('M')
        stack2.push('C')
        stack2.push('D')

        val stack3 = Stack<Char>()
        stack3.push('P')

        storage = listOf(stack1, stack2, stack3)

    }

    @Test
    internal fun parseCommand_move1from2to1() {
        // arrange

        // act
        val command = SupplyStacks().parseCommand("move 1 from 2 to 1")

        // assert
        assertThat(command.count).isEqualTo(1)
        assertThat(command.source).isEqualTo(2)
        assertThat(command.destination).isEqualTo(1)
    }

    @Test
    internal fun parseCommand_move3from1to3() {
        // arrange

        // act
        val command = SupplyStacks().parseCommand("move 3 from 1 to 3")

        // assert
        assertThat(command.count).isEqualTo(3)
        assertThat(command.source).isEqualTo(1)
        assertThat(command.destination).isEqualTo(3)
    }

    @Test
    internal fun parseCommands() {
        // arrange
        val rawCommands = listOf(
            "move 1 from 2 to 1",
            "move 3 from 1 to 3",
            "move 2 from 2 to 1",
            "move 1 from 1 to 2",
        )

        // act
        val commands = SupplyStacks().parseCommands(rawCommands)

        // assert
        assertThat(commands).hasSize(4)
    }

    @Test
    internal fun initSupplyStacks() {

        // act
        val supplyStacks = SupplyStacks(storage)

        // assert
        assertThat(supplyStacks.topCrates()).isEqualTo("NDP")

    }

    @Test
    internal fun executeCommand() {
        // arrange
        val supplyStacks = SupplyStacks(storage)

        // act
        supplyStacks.executeCommand(Command(1, 2, 1))

        // assert
        assertThat(supplyStacks.topCrates()).isEqualTo("DCP")
    }

    @Test
    internal fun executeCommands() {
        // arrange
        val rawCommands = listOf(
            "move 1 from 2 to 1",
            "move 3 from 1 to 3",
            "move 2 from 2 to 1",
            "move 1 from 1 to 2",
        )
        val supplyStacks = SupplyStacks(storage)
        val commands = supplyStacks.parseCommands(rawCommands)

        // act
        supplyStacks.executeCommands(commands)

        // assert
        assertThat(supplyStacks.topCrates()).isEqualTo("CMZ")
    }

    @Test
    internal fun loadData() {
        // arrange

        // act
        val rawCommands = SupplyStacks().loadData(Path("src", "test", "resources", "Day05_TestData.txt"), 5)

        // assert
        assertThat(rawCommands).hasSize(4)
    }

    @Test
    internal fun executeCommand9001() {
        // arrange
        val stack1 = Stack<Char>()
        stack1.push('Z')
        stack1.push('N')
        stack1.push('D')

        val stack2 = Stack<Char>()
        stack2.push('M')
        stack2.push('C')

        val stack3 = Stack<Char>()
        stack3.push('P')

        val storage = listOf(stack1, stack2, stack3)
        val supplyStacks = SupplyStacks(storage)

        // act
        supplyStacks.executeCommand9001(Command(3, 1, 3))

        // assert
        assertThat(supplyStacks.topCrates()).isEqualTo(" CD")
    }

    @Test
    internal fun executeCommands9001() {
        // arrange
        val rawCommands = listOf(
            "move 1 from 2 to 1",
            "move 3 from 1 to 3",
            "move 2 from 2 to 1",
            "move 1 from 1 to 2",
        )
        val supplyStacks = SupplyStacks(storage)
        val commands = supplyStacks.parseCommands(rawCommands)

        // act
        supplyStacks.executeCommands9001(commands)

        // assert
        assertThat(supplyStacks.topCrates()).isEqualTo("MCD")
    }

}