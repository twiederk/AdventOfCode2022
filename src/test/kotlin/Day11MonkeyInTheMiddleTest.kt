import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MonkeyInTheMiddleTest {

    val monkey0 = Monkey(
        items = mutableListOf(79, 98),
        operation = { it * 19 },
        divisor = 23,
        throwTo = Pair(2, 3)
    )

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

    @Test
    fun throwTo() {
        // act
        val throwToMonkey = monkey0.throwToMonkey(500)

        // assert
        assertThat(throwToMonkey).isEqualTo(3)
    }

    @Test
    fun inspect() {

        // act
        val item = monkey0.inspect()

        // assert
        assertThat(item).isEqualTo(79)
        assertThat(monkey0.items).containsExactly(98)
        assertThat(monkey0.inspectCount).isEqualTo(1)
    }

}