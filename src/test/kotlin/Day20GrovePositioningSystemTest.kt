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

    @Test
    fun mix_step1() {
        // arrange
        val input = listOf(1, 2, 3)
        val grovePositioningSystem = GrovePositioningSystem(input)

        // act
        grovePositioningSystem.mix(grovePositioningSystem.originalList[0])

        // assert
        assertThat(grovePositioningSystem.originalList).extracting("value").containsExactly(1, 2, 3)
        assertThat(grovePositioningSystem.mixList).extracting("value").containsExactly(2, 1, 3)
    }

    @Test
    fun mix_step2() {
        // arrange
        val input = listOf(1, 2, 3)
        val grovePositioningSystem = GrovePositioningSystem(input)
        grovePositioningSystem.mix(grovePositioningSystem.originalList[0])

        // act
        grovePositioningSystem.mix(grovePositioningSystem.originalList[1])

        // assert
        assertThat(grovePositioningSystem.originalList).extracting("value").containsExactly(1, 2, 3)
        assertThat(grovePositioningSystem.mixList).extracting("value").containsExactly(1, 3, 2)
    }

    @Test
    fun mix_step3() {
        // arrange
        val input = listOf(1, 2, 3)
        val grovePositioningSystem = GrovePositioningSystem(input)
        grovePositioningSystem.mix(grovePositioningSystem.originalList[0])
        grovePositioningSystem.mix(grovePositioningSystem.originalList[1])

        // act
        grovePositioningSystem.mix(grovePositioningSystem.originalList[2])

        // assert
        assertThat(grovePositioningSystem.originalList).extracting("value").containsExactly(1, 2, 3)
        assertThat(grovePositioningSystem.mixList).extracting("value").containsExactly(1, 3, 2)
    }


}