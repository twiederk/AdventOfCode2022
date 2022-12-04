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

        // act
        val ranges = CampCleanup().createRanges("2-4,6-8")

        // assert
        assertThat(ranges.first).isEqualTo(2..4)
        assertThat(ranges.second).isEqualTo(6..8)
    }

    @Test
    internal fun loadData() {

        // act
        val sectionAssignments = CampCleanup().loadData(Path("src", "test", "resources", "Day04_TestData.txt"))

        // assert
        assertThat(sectionAssignments).hasSize(6)
    }

    @Test
    internal fun createSectionAssignments() {
        // arrange
        val rawData = CampCleanup().loadData(Path("src", "test", "resources", "Day04_TestData.txt"))

        // act
        val sectionAssignments = CampCleanup().createSectionAssignments(rawData)

        // assert
        assertThat(sectionAssignments).hasSize(6)

//        2-4,6-8
//        2-3,4-5
//        5-7,7-9
//        2-8,3-7
//        6-6,4-6
//        2-6,4-8
        assertThat(sectionAssignments[0].first).isEqualTo(2..4)
        assertThat(sectionAssignments[0].second).isEqualTo(6..8)

        assertThat(sectionAssignments[1].first).isEqualTo(2..3)
        assertThat(sectionAssignments[1].second).isEqualTo(4..5)

        assertThat(sectionAssignments[2].first).isEqualTo(5..7)
        assertThat(sectionAssignments[2].second).isEqualTo(7..9)

        assertThat(sectionAssignments[3].first).isEqualTo(2..8)
        assertThat(sectionAssignments[3].second).isEqualTo(3..7)

        assertThat(sectionAssignments[4].first).isEqualTo(6..6)
        assertThat(sectionAssignments[4].second).isEqualTo(4..6)

        assertThat(sectionAssignments[5].first).isEqualTo(2..6)
        assertThat(sectionAssignments[5].second).isEqualTo(4..8)
    }

    @Test
    internal fun containsRange_24And68_false() {
        // arrange

        // act
        val containsRange = CampCleanup().containsRange(2..4, 6..8)

        // assert
        assertThat(containsRange).isFalse
    }

    @Test
    internal fun containsRange_28And37_true() {
        // arrange

        // act
        val containsRange = CampCleanup().containsRange(2..8, 3..7)

        // assert
        assertThat(containsRange).isTrue
    }

    @Test
    internal fun containsRange_66And46_true() {
        // arrange

        // act
        val containsRange = CampCleanup().containsRange(6..6, 4..6)

        // assert
        assertThat(containsRange).isTrue
    }

    @Test
    internal fun containsRange_26And48_false() {
        // arrange

        // act
        val containsRange = CampCleanup().containsRange(2..6, 4..8)

        // assert
        assertThat(containsRange).isFalse
    }

    @Test
    internal fun countSectionAssignments() {
        // arrange
        val rawData = CampCleanup().loadData(Path("src", "test", "resources", "Day04_TestData.txt"))
        val sectionAssignments = CampCleanup().createSectionAssignments(rawData)

        // act
        val countSectionAssignments = CampCleanup().countSectionAssignments(sectionAssignments)

        // assert
        assertThat(countSectionAssignments).isEqualTo(2)

    }

    @Test

    internal fun countOverlapRange() {
        // arrange
        val rawData = CampCleanup().loadData(Path("src", "test", "resources", "Day04_TestData.txt"))
        val sectionAssignments = CampCleanup().createSectionAssignments(rawData)

        // act
        val countOverlap = CampCleanup().countOverlapSections(sectionAssignments)

        // assert
        assertThat(countOverlap).isEqualTo(4)
    }

    @Test
    internal fun overlapRange_57And79_true() {
        // arrange

        // act
        val overlap = CampCleanup().overlapRange(5..7, 7..9)

        // assert
        assertThat(overlap).isTrue

    }
}