import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoilingBouldersTest {

    @Test
    fun loadData() {

        // act
        val cubes = BoilingBoulders().loadData("Day18_TestData.txt")

        // assert
        assertThat(cubes).hasSize(13)
    }

    @Test
    fun createCornerPoints() {
        // arrange
        val cube = Cube(1, 1, 1)

        // act
        cube.createCornerPoints()

        // assert
        assertThat(cube.cornerPoints).containsExactly(
            Point3D(0, 0, 0), // A
            Point3D(0, 1, 0), // B
            Point3D(1, 1, 0), // C
            Point3D(1, 0, 0), // D
            Point3D(0, 0, 1), // E
            Point3D(0, 1, 1), // F
            Point3D(1, 1, 1), // G
            Point3D(1, 0, 1), // H
        )
    }

    @Test
    fun createSides() {
        // arrange
        val cube = Cube(1,1,1)
        cube.createCornerPoints()

        // act
        cube.createSides()

        // assert
        val a = Point3D(0, 0, 0)
        val b = Point3D(0, 1, 0)
        val c = Point3D(1, 1, 0)
        val d = Point3D(1, 0, 0)
        val e = Point3D(0, 0, 1)
        val f = Point3D(0, 1, 1)
        val g = Point3D(1, 1, 1)
        val h = Point3D(1, 0, 1)

        assertThat(cube.sides).containsExactly(
            Side(setOf(a,b,c,d)), // back
            Side(setOf(e,f,g,h)), // front
            Side(setOf(b,c,f,g)), // top
            Side(setOf(a,d,e,h)), // bottom
            Side(setOf(a,b,e,f)), // left
            Side(setOf(c,d,g,h)), // right
        )

    }
}