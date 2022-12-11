import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MonkeyInTheMiddleTest {

    @Test
    fun initMonkey() {
        // arrange

        // act
        val monkey = Monkey(
            items = mutableListOf(79, 98)
        )

        // assert
        assertThat(monkey.items).containsExactly(79, 98)
    }

}