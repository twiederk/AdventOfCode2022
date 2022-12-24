import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day20Test {

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
        val day20 = Day20(input)

        // assert
        assertThat(day20.originalList).extracting("value").containsExactly(1, 2, 3)
        assertThat(day20.mixList).extracting("value").containsExactly(1, 2, 3)
    }

    @Test
    fun mix_step1() {
        // arrange
        val input = listOf(1, 2, 3)
        val day20 = Day20(input)

        // act
        day20.mix(day20.originalList[0])

        // assert
        assertThat(day20.originalList).extracting("value").containsExactly(1, 2, 3)
        assertThat(day20.mixList).extracting("value").containsExactly(2, 1, 3)
    }

    @Test
    fun mixAll_aocExample_all() {
        // arrange
        val input = listOf(1, 2, -3, 3, -2, 0, 4)
        val day20 = Day20(input)

        // act
        val groveCoordinates = day20.mixAll()

        // assert
        assertThat(groveCoordinates).isEqualTo(3)
        assertThat(day20.originalList).extracting("value").containsExactly(1, 2, -3, 3, -2, 0, 4)
        assertThat(day20.mixList).extracting("value").containsExactly(1, 2, -3, 4, 0, 3, -2)
    }

    @Test
    fun mixAll_aocExample_step1_moveNumber_1() {
        // arrange
        val input = listOf(1, 2, -3, 3, -2, 0, 4)
        val day20 = Day20(input)

        // act
        day20.mix(day20.originalList[0])

        // assert
        assertThat(day20.mixList).extracting("value").containsExactly(2, 1, -3, 3, -2, 0, 4)
    }

    @Test
    fun mixAll_aocExample_step2_moveNumber_2() {
        // arrange
        val input = listOf(1, 2, -3, 3, -2, 0, 4)
        val day20 = Day20(input)
        day20.mix(day20.originalList[0])

        // act
        day20.mix(day20.originalList[1])

        // assert
        assertThat(day20.mixList).extracting("value").containsExactly(1, -3, 2, 3, -2, 0, 4)
    }

    @Test
    fun mixAll_aocExample_step3_moveNumber_minus3() {
        // arrange
        val input = listOf(1, 2, -3, 3, -2, 0, 4)
        val day20 = Day20(input)
        day20.mix(day20.originalList[0])
        day20.mix(day20.originalList[1])

        // act
        day20.mix(day20.originalList[2])

        // assert
        assertThat(day20.mixList).extracting("value").containsExactly(1, 2, 3, -2, -3, 0, 4)
    }

    @Test
    fun mixAll_aocExample_step4_moveNumber_3() {
        // arrange
        val input = listOf(1, 2, -3, 3, -2, 0, 4)
        val day20 = Day20(input)
        day20.mix(day20.originalList[0])
        day20.mix(day20.originalList[1])
        day20.mix(day20.originalList[2])

        // act
        day20.mix(day20.originalList[3])

        // assert
        assertThat(day20.mixList).extracting("value").containsExactly(1, 2, -2, -3, 0, 3, 4)
    }

    @Test
    fun mixAll_aocExample_step5_moveNumber_minus2() {
        // arrange
        val input = listOf(1, 2, -3, 3, -2, 0, 4)
        val day20 = Day20(input)
        day20.mix(day20.originalList[0])
        day20.mix(day20.originalList[1])
        day20.mix(day20.originalList[2])
        day20.mix(day20.originalList[3])

        // act
        day20.mix(day20.originalList[4])

        // assert
        //                                                            1, 2, -2, -3, 0, 3,  4
        assertThat(day20.mixList).extracting("value").containsExactly(1, 2, -3,  0, 3, 4, -2)
    }

    @Test
    fun mixAll_aocExample_step6_moveNumber_zero() {
        // arrange
        val input = listOf(1, 2, -3, 3, -2, 0, 4)
        val day20 = Day20(input)
        day20.mix(day20.originalList[0])
        day20.mix(day20.originalList[1])
        day20.mix(day20.originalList[2])
        day20.mix(day20.originalList[3])
        day20.mix(day20.originalList[4])

        // act
        day20.mix(day20.originalList[5])

        // assert
        //                                                            1, 2, -3, 0, 3, 4, -2
        assertThat(day20.mixList).extracting("value").containsExactly(1, 2, -3, 0, 3, 4, -2)
    }

    @Test
    fun mixAll_aocExample_step7_moveNumber_4() {
        // arrange
        val input = listOf(1, 2, -3, 3, -2, 0, 4)
        val day20 = Day20(input)
        day20.mix(day20.originalList[0])
        day20.mix(day20.originalList[1])
        day20.mix(day20.originalList[2])
        day20.mix(day20.originalList[3])
        day20.mix(day20.originalList[4])
        day20.mix(day20.originalList[5])

        // act
        day20.mix(day20.originalList[6])

        // assert
        //                                                              move number 4: 1, 2, -3, 0, 3, 4, -2
        assertThat(day20.mixList).extracting("value").containsExactly(1, 2, -3, 4, 0, 3, -2)
    }

    @Test
    fun mix_aocExample_step3_error() {
        // arrange
        val input = listOf(1, -3, 2, 3, -2, 0, 4)
        val day20 = Day20(input)

        // act
        day20.mix(day20.originalList[1])

        // assert
        assertThat(day20.mixList).extracting("value").containsExactly(1, 2, 3, -2, -3, 0, 4)
    }

    @Test
    fun mix_aocExample_step7_error() {
        // arrange
        val input = listOf(1, 2, -3, 0, 3, 4, -2)
        val day20 = Day20(input)

        // act
        day20.mix(day20.originalList[6])

        // assert
        assertThat(day20.mixList).extracting("value").containsExactly(1, 2, -3, 4, 0, 3, -2)
    }

    @Test
    fun calculateGroveCoordinates() {
        // arrange
        val day20 = Day20(emptyList())
        day20.mixList = mutableListOf(Data(1), Data(2), Data(-3), Data(4), Data(0), Data(3), Data(-2))

        // act
        val groveCoordinates = day20.calculateGroveCoordinates()

        // assert
        assertThat(groveCoordinates).isEqualTo(3)
    }

    @Nested
    inner class NewIndexCalculation {

        // -step move to the left
        // element removed after newIndex:  oldIndex > newIndex => use new index
        @Test
        fun getNewIndex_leftAfter() {
            // arrange
            val day20 = Day20(emptyList())


            // act
            val newIndex = day20.getNewIndex(5, -3, 7)

            // assert
            assertThat(newIndex).isEqualTo(2)

        }

        // -step move to the left
        // element removed before newIndex: oldIndex < newIndex => use new index - 1
        @Test
        fun getNewIndex_leftBefore() {
            // arrange
            val day20 = Day20(emptyList())

            // act
            val newIndex = day20.getNewIndex(5, -6, 7)

            // assert
            assertThat(newIndex).isEqualTo(5)
        }

        // +step move ot the right
        // element removed after  newIndex: oldIndex > newIndex => use new index
        @Test
        fun getNewIndex_rightAfter() {
            // arrange
            val day20 = Day20(emptyList())

            // act
            val newIndex = day20.getNewIndex(5, 2, 10)

            // assert
            assertThat(newIndex).isEqualTo(7)
        }

        // +step move ot the right
        // element removed before newIndex: oldIndex < newIndex => use new index - 1
        @Test
        fun getNewIndex_rightBefore() {
            // arrange
            val day20 = Day20(emptyList())

            // act
            val newIndex = day20.getNewIndex(5, 7, 10)

            // assert
            assertThat(newIndex).isEqualTo(1)
        }
    }

}