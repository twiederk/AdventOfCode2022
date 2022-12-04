import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class CampCleanupTest {

    @Test
    fun createRange() {
        // arrange

        // act
        val range = CampCleanup().createRange("2-4")

        // assert
        assertThat(range).isEqualTo(2..4)
    }

    @Test
    internal fun createRanges() {
        // arrange

        // act
        val ranges = CampCleanup().createRanges("2-4,6-8")

        // assert
        assertThat(ranges.first).isEqualTo(2..4)
        assertThat(ranges.second).isEqualTo(6..8)
    }

    @Test
    internal fun loadData() {
        // arrange


        // act
        val sectionAssignments = CampCleanup().loadData(Path("src", "test", "resources", "Day04_TestData.txt"))

        // assert
        assertThat(sectionAssignments).hasSize(6)
    }

}