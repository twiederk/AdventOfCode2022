import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class Day01CalorieCountingTest {

    @Test
    fun loadData() {
        // arrange
        val path = Path("src", "test", "resources", "Day01_TestData.txt")

        // act
        val items = CalorieCounting().loadData(path)

        // assert
        assertThat(items).hasSize(14)
    }

    @Test
    fun createElves() {
        // arrange
        val path = Path("src", "test", "resources", "Day01_TestData.txt")
        val items = CalorieCounting().loadData(path)

        // act
        val elves = CalorieCounting().createElves(items)

        // assert
        assertThat(elves).hasSize(5)
    }

    @Test
    fun addFoodToElf() {
        // arrange
        val elf = Elf()

        // act
        elf.add(1000)

        // assert
        assertThat(elf.backpack).hasSize(1)
    }

    @Test
    fun createElvesWithFood() {
        // arrange
        val path = Path("src", "test", "resources", "Day01_TestData.txt")
        val items = CalorieCounting().loadData(path)

        // act
        val elves = CalorieCounting().createElves(items)

        // assert
        assertThat(elves[0].backpack).hasSize(3)
        assertThat(elves[1].backpack).hasSize(1)
        assertThat(elves[2].backpack).hasSize(2)
        assertThat(elves[3].backpack).hasSize(3)
        assertThat(elves[4].backpack).hasSize(1)

    }

}