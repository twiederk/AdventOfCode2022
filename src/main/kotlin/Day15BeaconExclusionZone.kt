import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
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

    fun scanWithPoints(sensors: List<Sensor>, row: Int): Int {
        val totalScanArea = mutableSetOf<Point>()
        for (sensor in sensors) {
            totalScanArea.addAll(sensor.scanArea())
        }
        val allPositions = totalScanArea.count { it.y == row }
        val sensorPositions = countSensorPositions(sensors, row)
        val beaconPositions = countBeaconPositions(sensors, row)

        return allPositions - sensorPositions - beaconPositions
    }

    fun scanWithRanges(sensors: List<Sensor>, row: Int): Int {
        val sensorsScanningRow = findSensorsScanningRow(sensors, row)
        val scanRanges = scanRowByAllSensors(sensorsScanningRow, row)
        val mergedRanges = mergeRanges(scanRanges)

        val allPositions = mergedRanges.sumOf { it.count() }
        val sensorPositions = countSensorPositions(sensors, row)
        val beaconPositions = countBeaconPositions(sensors, row)
        return allPositions - sensorPositions - beaconPositions

    }

    private fun countBeaconPositions(sensors: List<Sensor>, row: Int) =
        sensors.map { Point(it.beaconX, it.beaconY) }.filter { it.y == row }.toSet().count()

    private fun countSensorPositions(sensors: List<Sensor>, row: Int) =
        sensors.map { Point(it.x, it.y) }.filter { it.y == row }.toSet().count()


    fun findSensorsScanningRow(sensors: List<Sensor>, row: Int): List<Sensor> = sensors.filter { it.isInScanArea(row) }

    fun scanRowByAllSensors(sensors: List<Sensor>, row: Int): List<IntRange> =
        sensors.filter { it.isInScanArea(row) }.map { it.scanRow(row) }

    fun mergeRanges(ranges: List<IntRange>): List<IntRange> {
        val sortedRanges = ranges.sortedBy { it.first }
        val mergedRanges = mutableListOf<IntRange>()

        var currentRange = sortedRanges[0]
        for (i in 1..sortedRanges.lastIndex) {
            if (currentRange fullyOverlaps sortedRanges[i]) continue
            if (currentRange overlaps sortedRanges[i]) {
                currentRange = currentRange.first..sortedRanges[i].last
                continue
            }
            mergedRanges.add(currentRange)
            currentRange = sortedRanges[i]
        }
        mergedRanges.add(currentRange)
        return mergedRanges
    }

    fun findEmptySpaceInScan(sensors: List<Sensor>, maxY: Int): Point {
        for (y in 0..maxY) {
            val sensorsScanningRow = findSensorsScanningRow(sensors, y)
            val scanRanges = scanRowByAllSensors(sensorsScanningRow, y)
            val mergedRanges = mergeRanges(scanRanges)

            if (mergedRanges.size == 1) continue
            if (mergedRanges.size == 2) return Point(mergedRanges[0].last + 1, y)
            if (mergedRanges.size >= 3) throw IllegalStateException("Too many ranges: $mergedRanges")
        }
        throw IllegalArgumentException("Can't find beacon position")
    }

    fun calculateFrequency(point: Point): Int = point.x * 4_000_000 + point.y


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

    fun isInScanArea(row: Int): Boolean {
        val minY = y - manhattenDistance
        val maxY = y + manhattenDistance
        return (row in minY..maxY)
    }

    fun scanRow(row: Int): IntRange {
        val distanceX = manhattenDistance - abs(y - row)
        val minX = x - distanceX
        val maxX = x + distanceX
        return minX..maxX
    }

    val manhattenDistance: Int
        get() = abs(x - beaconX) + abs(y - beaconY)
}

data class Point(
    val x: Int,
    val y: Int
)

infix fun IntRange.overlaps(other: IntRange): Boolean = first <= other.last && other.first <= last

infix fun IntRange.fullyOverlaps(other: IntRange): Boolean = first <= other.first && last >= other.last

fun main() {
    val beaconExclusionZone = BeaconExclusionZone()
    val rawData = beaconExclusionZone.loadData(Path("src", "main", "resources", "Day15_part1_InputData.txt"))
    val sensors = beaconExclusionZone.createSensors(rawData)
    val positions = beaconExclusionZone.scanWithRanges(sensors, 2_000_000)

    println("positions = $positions")

}