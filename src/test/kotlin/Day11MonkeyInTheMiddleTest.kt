import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MonkeyInTheMiddleTest {

    @Test
    fun initMonkey() {
        // arrange

        // act
        val monkey = Monkey(
            items = mutableListOf(79, 98),
            operation = { it * 19 }

        )

        // assert
        assertThat(monkey.items).containsExactly(79, 98)
    }

    @Test
    fun calculateWorryLevel() {
        // arrange
        val monkey = Monkey(
            items = mutableListOf(79, 98),
            operation = { it * 19 }

        )

        // act
        val worryLevel = monkey.calculateWorryLevel(79)

        // assert
        assertThat(worryLevel).isEqualTo(1501)

    }
}