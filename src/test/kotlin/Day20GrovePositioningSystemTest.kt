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
    fun mix_ownExample_step2() {
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
    fun mix_ownExample_step3() {
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

    @Test
    fun mixAll_ownExample() {
        // arrange
        val input = listOf(1, 2, 3)
        val grovePositioningSystem = GrovePositioningSystem(input)

        // act
        grovePositioningSystem.mixAll()

        // assert
        assertThat(grovePositioningSystem.originalList).extracting("value").containsExactly(1, 2, 3)
        assertThat(grovePositioningSystem.mixList).extracting("value").containsExactly(1, 3, 2)
    }

    @Test
    fun mixAll_aocExample_all() {
        // arrange
        val input = listOf(1, 2, -3, 3, -2, 0, 4)
        val grovePositioningSystem = GrovePositioningSystem(input)

        // act
        grovePositioningSystem.mixAll()

        // assert
        assertThat(grovePositioningSystem.originalList).extracting("value").containsExactly(1, 2, -3, 3, -2, 0, 4)
        assertThat(grovePositioningSystem.mixList).extracting("value").containsExactly(1, 2, -3, 4, 0, 3, -2)
    }

    @Test
    fun mixAll_aocExample_step1() {
        // arrange
        val input = listOf(1, 2, -3, 3, -2, 0, 4)
        val grovePositioningSystem = GrovePositioningSystem(input)

        // act
        grovePositioningSystem.mix(grovePositioningSystem.originalList[0])

        // assert
        assertThat(grovePositioningSystem.mixList).extracting("value").containsExactly(2, 1, -3, 3, -2, 0, 4)
    }

    @Test
    fun mixAll_aocExample_step2() {
        // arrange
        val input = listOf(1, 2, -3, 3, -2, 0, 4)
        val grovePositioningSystem = GrovePositioningSystem(input)
        grovePositioningSystem.mix(grovePositioningSystem.originalList[0])

        // act
        grovePositioningSystem.mix(grovePositioningSystem.originalList[1])

        // assert
        assertThat(grovePositioningSystem.mixList).extracting("value").containsExactly(1, -3, 2, 3, -2, 0, 4)
    }

    @Test
    fun mixAll_aocExample_step3() {
        // arrange
        val input = listOf(1, 2, -3, 3, -2, 0, 4)
        val grovePositioningSystem = GrovePositioningSystem(input)
        grovePositioningSystem.mix(grovePositioningSystem.originalList[0])
        grovePositioningSystem.mix(grovePositioningSystem.originalList[1])

        // act
        grovePositioningSystem.mix(grovePositioningSystem.originalList[2])

        // assert
        println("grovePositioningSystem = ${grovePositioningSystem.mixList}")
        assertThat(grovePositioningSystem.mixList).extracting("value").containsExactly(1, 2, 3, -2, -3, 0, 4)
    }

    @Test
    fun getNewInt_positive10() {

        // act
        val index = GrovePositioningSystem(emptyList()).getNewIndex(4, 10, 5)

        // assert
        assertThat(index).isEqualTo(3)
    }

    @Test
    fun getNewInt_minus3() {

        // act
        val index = GrovePositioningSystem(emptyList()).getNewIndex(0, -3, 10)

        // assert
        assertThat(index).isEqualTo(6)
    }

    @Test
    fun getNewInt_minus5() {
        // arrange

        // act
        val index = GrovePositioningSystem(emptyList()).getNewIndex(0, -5, 10)

        // assert
        assertThat(index).isEqualTo(4)
    }

}