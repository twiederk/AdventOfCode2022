import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.math.abs

class BeaconExclusionZone {

    fun loadData(path: Path): List<String> = path.readLines()

    fun createSensors(rawData: List<String>): List<Sensor> = rawData.map { createSensor(it) }

    fun createSensor(input: String): Sensor {
        // Sensor at x=2, y=18: closest beacon is at x=-2, y=15
        val (rawSensor, rawBeacon) = input.split(':')
        val x = parseX(rawSensor)
        val y = parseY(rawSensor)
        val beaconX = parseBeaconX(rawBeacon)
        val beaconY = parseBeaconY(rawBeacon)

        return Sensor(x, y, beaconX, beaconY)
    }

    private fun parseX(rawSensor: String) = rawSensor.substring("Sensor at x=".length).split(',')[0].toInt()

    private fun parseY(rawSensor: String) =
        rawSensor.substring("Sensor at x=".length).split(',')[1].substring(" y=".length).toInt()

    private fun parseBeaconX(rawBeacon: String) = rawBeacon.substring(" closest beacon is at x=".length).split(',')[0].toInt()

    private fun parseBeaconY(rawBeacon: String) =
        rawBeacon.substring(" closest beacon is at x=".length).split(',')[1].substring(" y=".length).toInt()

}

data class Sensor(
    val x: Int,
    val y: Int,
    val beaconX: Int,
    val beaconY: Int
) {
    val manhattenDistance: Int
        get() = abs(x - beaconX) + abs(y - beaconY)
}