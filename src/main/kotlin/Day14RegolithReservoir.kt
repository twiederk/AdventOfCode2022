import java.nio.file.Path
import kotlin.io.path.readLines

typealias Cave = Array<CharArray>

class RegolithReservoir {

    fun loadData(path: Path) = path.readLines()

    fun createLines(rawData: List<String>): List<Line> {
        val lines = mutableListOf<Line>()
        for (raw in rawData) {
            val split = raw.split(" -> ")
            val windows = split.windowed(2, 1)
            for (window in windows) {
                val (startX, startY) = window[0].split(",")
                val (endX, endY) = window[1].split(",")
                lines.add(Line(startX.toInt(), startY.toInt(), endX.toInt(), endY.toInt()))
            }

        }
        return lines
    }

    fun maxX(lines: List<Line>): Int {
        var maxX = 0
        for (line in lines) {
            if (line.startX > maxX) maxX = line.startX
            if (line.endX > maxX) maxX = line.endX
        }
        return maxX
    }

    fun maxY(lines: List<Line>): Int {
        var maxY = 0
        for (line in lines) {
            if (line.startY > maxY) maxY = line.startY
            if (line.endY > maxY) maxY = line.endY
        }
        return maxY
    }

    fun createCave(lines: List<Line>): Cave {

        val maxX = maxX(lines)
        val maxY = maxY(lines)

        val cave = Array(maxY) { CharArray(maxX) }

        for (y in 0 until maxY) {
            for (x in 0 until maxX) {
                cave[y][x] = '.'
            }
        }

        val display = StringBuffer()
        for (i in cave.indices) {
            display.append(cave[i]).append('\n')
        }
        println(display.toString())

        return cave
    }


}

data class Line(val startX: Int, val startY: Int, val endX: Int, val endY: Int)