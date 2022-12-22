import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GrovePositioningSystemTest {

    @Nested
    inner class DataTest {

        @Test
        fun equals_notTheSame() {
            // arrange
            val data1 = Data(1)
            val data2 = Data(1)

            // act
            val equal = data1 == data2

            // assert
            assertThat(equal).isFalse
        }

        @Test
        fun equals_theSame() {
            // arrange
            val data1 = Data(1)

            // act
            val equal = data1 == data1

            // assert
            assertThat(equal).isTrue
        }

    }

    @Test
    fun init() {
        // arrange
        val input = listOf(1, 2, 3)

        // act
        val grovePositioningSystem = GrovePositioningSystem(input)

        // assert
        assertThat(grovePositioningSystem.originalList).extracting("value").containsExactly(1, 2, 3)
        assertThat(grovePositioningSystem.mixList).extracting("value").containsExactly(1, 2, 3)
    }
}