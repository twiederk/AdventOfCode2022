import java.nio.file.Path
import kotlin.io.path.Path
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

    private fun parseBeaconX(rawBeacon: String) =
        rawBeacon.substring(" closest beacon is at x=".length).split(',')[0].toInt()

    private fun parseBeaconY(rawBeacon: String) =
        rawBeacon.substring(" closest beacon is at x=".length).split(',')[1].substring(" y=".length).toInt()

    fun scan(sensors: List<Sensor>, row: Int): Int {
        val totalScanArea = mutableSetOf<Point>()
        for ((index, sensor) in sensors.withIndex()) {
            println("scan ... $index of ${sensors.size}")
            totalScanArea.addAll(sensor.scanArea())
        }
        val allPositions = totalScanArea.count { it.y == row }
        val sensorPositions = sensors.map { Point(it.x, it.y) }.filter { it.y == row }.toSet().count()
        val beaconPositions = sensors.map { Point(it.beaconX, it.beaconY) }.filter { it.y == row }.toSet().count()

        return allPositions - sensorPositions - beaconPositions
    }

}

data class Sensor(
    val x: Int,
    val y: Int,
    val beaconX: Int,
    val beaconY: Int
) {
    fun scanArea(): List<Point> {
        val minY = y - manhattenDistance
        val maxY = y + manhattenDistance
        val minX = x - manhattenDistance
        val maxX = x + manhattenDistance

        val scanArea = mutableListOf<Point>()
        var distance = manhattenDistance
        for (y in minY..maxY) {
            for (x in (minX + abs(distance)..maxX - abs(distance))) {
                scanArea.add(Point(x, y))
            }
            distance -= 1
        }
        return scanArea
    }

    val manhattenDistance: Int
        get() = abs(x - beaconX) + abs(y - beaconY)
}

data class Point(
    val x: Int,
    val y: Int
)

fun main() {
    val beaconExclusionZone = BeaconExclusionZone()
    val rawData = beaconExclusionZone.loadData(Path("src", "main", "resources", "Day15_part1_InputData.txt"))
    val sensors = beaconExclusionZone.createSensors(rawData)
    val positions = beaconExclusionZone.scan(sensors, 2_000_000)

    println("positions = $positions")

}