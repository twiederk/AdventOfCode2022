import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class BeaconExclusionZoneTest {

    // load data
    // create sensor
    // create beacon
    // calculate Manhatten distance for each sensor
    // calculate area for each sensor covered
    //   by its scan using by drawing a circle with sensor as center and Manhatten distance as radius
    // store all fields of all scans in one place
    // check row (y) how many fields are covered

    @Test
    fun loadData() {
        // arrange

        // act
        val rawData = BeaconExclusionZone().loadData(Path("src", "test", "resources", "Day15_TestData.txt"))

        // assert
        assertThat(rawData).hasSize(14)
    }

}