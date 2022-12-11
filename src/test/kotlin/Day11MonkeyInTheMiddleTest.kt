import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MonkeyInTheMiddleTest {

    val monkey0 = Monkey(
        items = mutableListOf(79, 98),
        operation = { it * 19 }
    )

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
    fun newWorryLevel() {
        // act
        val worryLevel = monkey0.newWorryLevel(79)

        // assert
        assertThat(worryLevel).isEqualTo(1501)
    }

    @Test
    fun normalizedWorryLevel_item79_result500() {
        // act
        val normalizedWorryLevel = monkey0.normalizedWorryLevel(79)

        // assert
        assertThat(normalizedWorryLevel).isEqualTo(500)
    }

    @Test
    fun normalizedWorryLevel_item98_result620() {
        // act
        val normalizedWorryLevel = monkey0.normalizedWorryLevel(98)

        // assert
        assertThat(normalizedWorryLevel).isEqualTo(620)
    }

}