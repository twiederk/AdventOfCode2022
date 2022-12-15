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
    fun scanWithPoints() {
        // arrange
        val rawData = beaconExclusionZone.loadData(Path("src", "test", "resources", "Day15_TestData.txt"))
        val sensors = beaconExclusionZone.createSensors(rawData)

        // act
        val positions = beaconExclusionZone.scanWithPoints(sensors, 10)

        // assert
        assertThat(positions).isEqualTo(26)
    }

    @Test
    fun findSensorsScanningRow() {
        // arrange
        val rawData = beaconExclusionZone.loadData(Path("src", "test", "resources", "Day15_TestData.txt"))
        val sensors = beaconExclusionZone.createSensors(rawData)

        // act
        val sensorsScanningRow = beaconExclusionZone.findSensorsScanningRow(sensors, 10)

        // assert
        assertThat(sensorsScanningRow).hasSize(6)
    }

    @Test
    fun scanRow_minus2() {
        // arrange
        val sensor = Sensor(x = 8, y = 7, beaconX = 2, beaconY = 10)

        // act
        val scanRow = sensor.scanRow(-2)

        // assert
        assertThat(scanRow).isEqualTo(8..8)
    }

    @Test
    fun scanRow_minus1() {
        // arrange
        val sensor = Sensor(x = 8, y = 7, beaconX = 2, beaconY = 10)

        // act
        val scanRow = sensor.scanRow(-1)

        // assert
        assertThat(scanRow).isEqualTo(7..9)
    }

    @Test
    fun scanRow_plus7() {
        // arrange
        val sensor = Sensor(x = 8, y = 7, beaconX = 2, beaconY = 10)

        // act
        val scanRow = sensor.scanRow(7)

        // assert
        assertThat(scanRow).isEqualTo(-1..17)
    }

    @Test
    fun scanRow_plus16() {
        // arrange
        val sensor = Sensor(x = 8, y = 7, beaconX = 2, beaconY = 10)

        // act
        val scanRow = sensor.scanRow(16)

        // assert
        assertThat(scanRow).isEqualTo(8..8)
    }

    @Test
    fun scanRowByAllSensors() {
        // arrange
        val rawData = beaconExclusionZone.loadData(Path("src", "test", "resources", "Day15_TestData.txt"))
        val sensors = beaconExclusionZone.createSensors(rawData)

        // act
        val scanRanges = beaconExclusionZone.scanRowByAllSensors(sensors, 10)

        // assert
        assertThat(scanRanges).contains(12..12, 2..14, 2..2, -2..2, 16..24, 14..18)
    }

    @Test
    fun mergeRanges_oneRange() {
        // arrange
        val ranges = listOf(12..12, 2..14, 2..2, -2..2, 16..24, 14..18)

        // act
        val mergedRanges = beaconExclusionZone.mergeRanges(ranges)

        // assert
        assertThat(mergedRanges).contains(-2..24)
    }

    @Test
    fun mergeRanges_twoRanges() {
        // arrange
        val ranges = listOf(12..12, 2..14, 2..2, -2..2, 16..24)

        // act
        val mergedRanges = beaconExclusionZone.mergeRanges(ranges)

        // assert
        assertThat(mergedRanges).contains(-2..14, 16..24)
    }

    @Test
    fun scanWithRanges() {
        // arrange
        val rawData = beaconExclusionZone.loadData(Path("src", "test", "resources", "Day15_TestData.txt"))
        val sensors = beaconExclusionZone.createSensors(rawData)

        // act
        val positions = beaconExclusionZone.scanWithRanges(sensors, 10)

        // assert
        assertThat(positions).isEqualTo(26)
    }

}