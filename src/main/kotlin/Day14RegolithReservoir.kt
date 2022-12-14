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

        val maxX = maxX(lines) + 1
        val maxY = maxY(lines) + 1

        val cave = Array(maxY) { CharArray(maxX) }
        emptyCave(maxY, maxX, cave)
        drawRocks(lines, cave)

        return cave
    }

    private fun drawRocks(lines: List<Line>, cave: Array<CharArray>) {
        for (line in lines) {
            if (line.startY == line.endY) {
                drawHorizontalLine(line, cave)
            } else {
                drawVerticalLine(line, cave)
            }
        }
    }

    private fun emptyCave(maxY: Int, maxX: Int, cave: Array<CharArray>) {
        for (y in 0 until maxY) {
            for (x in 0 until maxX) {
                cave[y][x] = '.'
            }
        }
    }

    private fun drawVerticalLine(line: Line, cave: Array<CharArray>) {
        val rangeY = if (line.startY < line.endY) line.startY..line.endY else line.endY..line.startY
        for (y in rangeY) {
            cave[y][line.startX] = '#'
        }
    }

    private fun drawHorizontalLine(line: Line, cave: Array<CharArray>) {
        val rangeX = if (line.startX < line.endX) line.startX..line.endX else line.endX..line.startX
        for (x in rangeX) {
            cave[line.startY][x] = '#'
        }
    }

    fun renderCave(cave: Array<CharArray>, startX: Int = 0, startY: Int = 0, endX: Int = cave[0].size, endY: Int = cave.size): String {
        val display = StringBuffer()
        for (y in startY until endY) {
            for (x in startX until endX) {
                display.append(cave[y][x])
            }
            display.append('\n')
        }
        return display.toString()
    }


}

data class Line(val startX: Int, val startY: Int, val endX: Int, val endY: Int)