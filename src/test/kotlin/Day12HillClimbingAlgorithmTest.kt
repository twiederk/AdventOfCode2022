import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class HillClimbingAlgorithmTest {

    @Test
    fun loadData() {
        // act
        val grid = HillClimbingAlgorithm().loadData(Path("src", "test", "resources", "Day12_TestData.txt"))

        // assert
        assertThat(grid[0]).hasSize(8)
        assertThat(grid).hasSize(5)
    }

    @Test
    fun nodeComparator_firstNodeLessWeight() {
        // arrange
        val node1 = Node(f = 1)
        val node2 = Node(f = 2)

        // act
        val result = node1.compareTo(node2)

        // assert
        assertThat(result).isEqualTo(-1)
    }

    @Test
    fun nodeComparator_firstNodeMoreWeight() {
        // arrange
        val node1 = Node(f = 2)
        val node2 = Node(f = 1)

        // act
        val result = node1.compareTo(node2)

        // assert
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun nodeComparator_sameWeight() {
        // arrange
        val node1 = Node(f = 2)
        val node2 = Node(f = 2)

        // act
        val result = node1.compareTo(node2)

        // assert
        assertThat(result).isEqualTo(0)
    }

}

