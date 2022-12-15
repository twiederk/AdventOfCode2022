import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class BeaconExclusionZoneTest {

    // + load data
    // + create sensor
    // + create beacon
    // + calculate Manhatten distance for each sensor
    // + calculate area for each sensor covered
    //   + by its scan using by drawing a circle with sensor as center and Manhatten distance as radius
    // store all fields of all scans in one place (Set)
    // check row (y) how many fields are covered

    private val beaconExclusionZone = BeaconExclusionZone()

    @Test
    fun loadData() {

        // act
        val rawData = beaconExclusionZone.loadData(Path("src", "test", "resources", "Day15_TestData.txt"))

        // assert
        assertThat(rawData).hasSize(14)
    }

    @Test
    fun createSensors() {
        // arrange
        val rawData = beaconExclusionZone.loadData(Path("src", "test", "resources", "Day15_TestData.txt"))

        // act
        val sensors = beaconExclusionZone.createSensors(rawData)

        // assert
        assertThat(sensors).hasSize(14)
    }

    @Test
    fun createSensor() {

        // act
        val sensor = beaconExclusionZone.createSensor("Sensor at x=2, y=18: closest beacon is at x=-2, y=15")

        // assert
        assertThat(sensor.x).isEqualTo(2)
        assertThat(sensor.y).isEqualTo(18)
        assertThat(sensor.beaconX).isEqualTo(-2)
        assertThat(sensor.beaconY).isEqualTo(15)
        assertThat(sensor.manhattenDistance).isEqualTo(7)
    }

    @Test
    fun scanArea() {
        // arrange
        val sensor = Sensor(2, 18, -2, 15)

        // act
        val scanArea = sensor.scanArea()

        // assert
        assertThat(scanArea).hasSize(113)
    }

    @Test
    fun scan() {
        // arrange
        val rawData = beaconExclusionZone.loadData(Path("src", "test", "resources", "Day15_TestData.txt"))
        val sensors = beaconExclusionZone.createSensors(rawData)

        // act
        val positions = beaconExclusionZone.scan(sensors, 10)

        // assert
        assertThat(positions).isEqualTo(26)
    }

}