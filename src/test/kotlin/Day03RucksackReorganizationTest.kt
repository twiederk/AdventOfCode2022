import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

//Lowercase item types a through z have priorities 1 through 26.
//Uppercase item types A through Z have priorities 27 through 52.

class RucksackReorganizationTest {

    @Test
    internal fun calculatePriority_a_1() {
        // arrange

        // act
        val priority = RucksackReorganization().calculatePriority('a')

        // assert
        assertThat(priority).isEqualTo(1)
    }

    @Test
    internal fun calculatePriority_z_26() {
        // arrange

        // act
        val priority = RucksackReorganization().calculatePriority('z')

        // assert
        assertThat(priority).isEqualTo(26)
    }

    @Test
    internal fun calculatePriority_A_27() {
        // arrange

        // act
        val priority = RucksackReorganization().calculatePriority('A')

        // assert
        assertThat(priority).isEqualTo(27)
    }

    @Test
    internal fun calculatePriority_Z_52() {
        // arrange

        // act
        val priority = RucksackReorganization().calculatePriority('Z')

        // assert
        assertThat(priority).isEqualTo(52)
    }

}