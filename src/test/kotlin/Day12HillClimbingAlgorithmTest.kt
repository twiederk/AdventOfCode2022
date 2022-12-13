import org.assertj.core.api.AbstractAssert
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
        val node1 = Node().apply { f = 1 }
        val node2 = Node().apply { f = 2 }

        // act
        val result = node1.compareTo(node2)

        // assert
        assertThat(result).isEqualTo(-1)
    }

    @Test
    fun nodeComparator_firstNodeMoreWeight() {
        // arrange
        val node1 = Node().apply { f = 2 }
        val node2 = Node().apply { f = 1 }

        // act
        val result = node1.compareTo(node2)

        // assert
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun nodeComparator_sameWeight() {
        // arrange
        val node1 = Node().apply { f = 2 }
        val node2 = Node().apply { f = 2 }

        // act
        val result = node1.compareTo(node2)

        // assert
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun findStartNode() {

        // act
        val start = hillClimbingAlgorithm.findStartNode(grid)

        // assert
        assertThat(start).isEqualTo(Node(0, 0))
    }

    @Test
    fun findEndNode() {

        // act
        val start = hillClimbingAlgorithm.findEndNode(grid)

        // assert
        assertThat(start).isEqualTo(Node(2, 5))
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

    @Test
    fun distance_00_11_is2() {

        // act
        val distance = hillClimbingAlgorithm.distance(Node(0, 0), Node(1, 1))

        // assert
        assertThat(distance).isEqualTo(2)

    }

    @Test
    fun expandNode_00() {

        // act
        hillClimbingAlgorithm.expandNode(grid, Node(0, 0), Node(2, 5))

        // assert
        assertThat(hillClimbingAlgorithm.openList).hasSize(2)
    }

    @Test
    fun aStar() {

        // act
        val result = hillClimbingAlgorithm.aStar(grid)

        // assert
        println("hillClimbingAlgorithm.closedList.size = ${hillClimbingAlgorithm.closedList.size}")
        assertThat(result).isEqualTo(31)
    }

    @Test
    fun aStar_step1() {

        // act
        hillClimbingAlgorithm.aStar(grid, 1)

        // assert
        val openList = hillClimbingAlgorithm.openList
        assertThat(openList).hasSize(2)
        NodeAssert(openList.elementAt(0)).hasCoords(0, 1).hasG(1).hasF(7)
        NodeAssert(openList.elementAt(1)).hasCoords(1, 0).hasG(1).hasF(7)

        val closedList = hillClimbingAlgorithm.closedList
        assertThat(hillClimbingAlgorithm.closedList).hasSize(1)
        NodeAssert(closedList.elementAt(0)).hasCoords(0, 0).hasG(0).hasF(0)
    }

}

class NodeAssert(actual: Node) : AbstractAssert<NodeAssert, Node>(actual, NodeAssert::class.java) {

    companion object {
        fun assertThat(actual: Node): NodeAssert {
            return NodeAssert(actual)
        }
    }

    fun hasRow(row: Int): NodeAssert {
        if (actual.row != row) {
            failWithMessage("Expected node to have ROW [$row] but was [${actual.row}]")
        }
        return this
    }

    fun hasCol(col: Int): NodeAssert {
        if (actual.col != col) {
            failWithMessage("Expected node to have COL [$col] but was [${actual.col}]")
        }
        return this
    }

    fun hasCoords(row: Int, col: Int): NodeAssert {
        hasRow(row)
        hasCol(col)
        return this
    }


    fun hasG(g: Int): NodeAssert {
        if (actual.g != g) {
            failWithMessage("Expected node to have G [$g] but was [${actual.g}]")
        }
        return this
    }

    fun hasF(f: Int): NodeAssert {
        if (actual.f != f) {
            failWithMessage("Expected node to have F [$f] but was [${actual.f}]")
        }
        return this
    }
}

