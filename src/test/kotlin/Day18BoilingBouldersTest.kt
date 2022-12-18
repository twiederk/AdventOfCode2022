import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoilingBouldersTest {


    /*
    x,y,z

    (1,1,1), (2,1,1)

    (1,1,1)=>
    8 corners:
    1.) 0,0,0
    2.) 1,0,0
    3.) 0,1,0
    4.) 0,0,1
    5.) 1,0,1
    6.) 1,1,0
    7.) 0,1,1
    8.) 1,1,1

    6 sides:
    1.)
     */

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
}