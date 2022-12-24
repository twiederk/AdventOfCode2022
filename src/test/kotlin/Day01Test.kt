import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class Day01Test {

    @Test
    fun loadData() {
        // arrange
        val path = Path("src", "test", "resources", "Day01_TestData.txt")

        // act
        val items = Day01().loadData(path)

        // assert
        assertThat(items).hasSize(14)
    }

    @Test
    fun createElves() {
        // arrange
        val path = Path("src", "test", "resources", "Day01_TestData.txt")
        val items = Day01().loadData(path)

        // act
        val elves = Day01().createElves(items)

        // assert
        assertThat(elves).hasSize(5)
    }

    @Test
    fun createElvesWithFood() {
        // arrange
        val path = Path("src", "test", "resources", "Day01_TestData.txt")
        val items = Day01().loadData(path)

        // act
        val elves = Day01().createElves(items)

        // assert
        assertThat(elves[0].backpack).hasSize(3)
        assertThat(elves[1].backpack).hasSize(1)
        assertThat(elves[2].backpack).hasSize(2)
        assertThat(elves[3].backpack).hasSize(3)
        assertThat(elves[4].backpack).hasSize(1)
    }

    @Test
    fun maxCalories() {
        // arrange
        val path = Path("src", "test", "resources", "Day01_TestData.txt")
        val items = Day01().loadData(path)
        val elves = Day01().createElves(items)

        // act
        val maxCalories = Day01().maxCalories(elves)

        // assert
        assertThat(maxCalories).isEqualTo(24000)
    }

    @Test
    fun top3Calories() {
        // arrange
        val path = Path("src", "test", "resources", "Day01_TestData.txt")
        val items = Day01().loadData(path)
        val elves = Day01().createElves(items)

        // act
        val top3Calories = Day01().top3Calories(elves)

        // assert
        assertThat(top3Calories).isEqualTo(45000)

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
    fun countFoodOfElf() {
        // arrange
        val elf = Elf()
        elf.add(1000)
        elf.add(2000)

        // act
        val totalOfCalories = elf.sumUpCalories()

        // assert
        assertThat(totalOfCalories).isEqualTo(3000)

    }

}