import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class SupplyStacksTest {

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

    //        [D]
//    [N] [C]
//    [Z] [M] [P]
//     1   2   3
    @Test
    internal fun initSupplyStacks() {
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

        val storage = listOf(stack1, stack2, stack3)

        // act
        val supplyStacks = SupplyStacks(storage)

        // assert
        assertThat(supplyStacks.topCrates()).isEqualTo("NDP")

    }

    //    @Test
//    internal fun executeCommand() {
//        // arrange
//
//        // act
//        SupplyStacks().executeCommand(Command(1, 2, 1))
//
//        // assert
//
//    }
}