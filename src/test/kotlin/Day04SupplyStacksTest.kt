import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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

}