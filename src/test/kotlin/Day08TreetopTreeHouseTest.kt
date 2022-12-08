import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class TreetopTreeHouseTest {

    @Test
    fun convertToInts() {
        // arrange
        val inputAsString = listOf(
            "30373",
            "25512",
            "65332",
        )

        // act
        val inputAsInt = TreetopTreeHouse().convertToInts(inputAsString)

        // assert
        assertThat(inputAsInt[0].size).isEqualTo(5)
        assertThat(inputAsInt.size).isEqualTo(3)
    }

    @Test
    fun loadData() {

        // act
        val rawData = TreetopTreeHouse().loadData(Path("src", "test", "resources", "Day08_TestData.txt"))

        // assert
        assertThat(rawData).hasSize(5)
    }

    //    30373
//    25512
//    65332
//    33549
//    35390
    @Test
    fun loadAndConvertData() {
        // arrange
        val treetopTreeHouse = TreetopTreeHouse()
        val rawData = treetopTreeHouse.loadData(Path("src", "test", "resources", "Day08_TestData.txt"))

        // act
        val inputAsInt = treetopTreeHouse.convertToInts(rawData)

        // assert
        assertThat(inputAsInt[0][3]).isEqualTo(7)

    }
}