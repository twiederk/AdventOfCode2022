import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class HillClimbingAlgorithmTest {

    private val hillClimbingAlgorithm = HillClimbingAlgorithm()
    private val grid = hillClimbingAlgorithm.loadData(Path("src", "test", "resources", "Day12_TestData.txt"))

    @Test
    fun loadData() {
        // act
        val grid = hillClimbingAlgorithm.loadData(Path("src", "test", "resources", "Day12_TestData.txt"))

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

    @Test
    fun aStar() {

        // act
        val result = hillClimbingAlgorithm.aStar(grid)

        // assert
        assertThat(result).isEqualTo(31)
    }

    @Test
    fun findStartNode() {

        // act
        val start = hillClimbingAlgorithm.findStartNode(grid)

        // assert
        assertThat(start).isEqualTo(Node(0, 0, 0))
    }

    @Test
    fun findEndNode() {

        // act
        val start = hillClimbingAlgorithm.findEndNode(grid)

        // assert
        assertThat(start).isEqualTo(Node(5, 2, 0))
    }

    @Test
    fun getNeighbors_00_twoNeighbors() {

        // act
        val neighbors = hillClimbingAlgorithm.getNeighbors(grid, Node(0, 0))

        // assert
        assertThat(neighbors).containsExactly(
            Node(0, 1),
            Node(1, 0)
        )
    }

    @Test
    fun getNeighbors_10_threeNeighbors() {

        // act
        val neighbors = hillClimbingAlgorithm.getNeighbors(grid, Node(1, 0))

        // assert
        assertThat(neighbors).containsExactly(
            Node(0, 0),
            Node(1, 1),
            Node(2, 0),
        )
    }

    @Test
    fun getNeighbors_11_fourNeighbors() {

        // act
        val neighbors = hillClimbingAlgorithm.getNeighbors(grid, Node(1, 1))

        // assert
        assertThat(neighbors).containsExactly(
            Node(0, 1),
            Node(1, 2),
            Node(2, 1),
            Node(1, 0),
        )
    }

    @Test
    fun getNeighbors_01_fourNeighbors() {

        // act
        val neighbors = hillClimbingAlgorithm.getNeighbors(grid, Node(0, 1))

        // assert
        assertThat(neighbors).containsExactly(
            Node(0, 2),
            Node(1, 1),
            Node(0, 0),
        )
    }

    @Test
    fun getNeighbors_02_twoNeighbors() {

        // act
        val neighbors = hillClimbingAlgorithm.getNeighbors(grid, Node(0, 2))

        // assert
        assertThat(neighbors).containsExactly(
            Node(1, 2),
            Node(0, 1),
        )
    }

    @Test
    fun isClimbable_ab_true() {
        // arrange
        val grid = hillClimbingAlgorithm.loadData(Path("src", "test", "resources", "Day12_TestData.txt"))

        // act
        val climbable = hillClimbingAlgorithm.isClimbable(grid, Node(0, 1), Node(0, 2))

        // assert
        assertThat(climbable).isTrue
    }

    @Test
    fun isClimbable_bq_false() {
        // arrange
        val grid = hillClimbingAlgorithm.loadData(Path("src", "test", "resources", "Day12_TestData.txt"))

        // act
        val climbable = hillClimbingAlgorithm.isClimbable(grid, Node(0, 2), Node(0, 3))

        // assert
        assertThat(climbable).isFalse
    }

}

