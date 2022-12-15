import java.nio.file.Path
import kotlin.io.path.readLines

class BeaconExclusionZone {

    fun loadData(path: Path): List<String> = path.readLines()

    fun createSensors(rawData: List<String>): List<Sensor> = rawData.map { createSensor(it) }

    fun createSensor(input: String): Sensor {
        // Sensor at x=2, y=18: closest beacon is at x=-2, y=15
        val (rawSensor, rawbeacon) = input.split(':')
        val x = parseX(rawSensor)
        val y = parseY(rawSensor)

        return Sensor(x, y)

    }

    private fun parseY(rawSensor: String) =
        rawSensor.substring("Sensor at x=".length).split(',')[1].substring(" y=".length).toInt()

    private fun parseX(rawSensor: String) = rawSensor.substring("Sensor at x=".length).split(',')[0].toInt()

}

data class Sensor(
    val x: Int,
    val y: Int,
)