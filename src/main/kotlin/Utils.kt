import java.io.File
import java.net.URI

// Resources
// =========
// https://todd.ginsberg.com/post/advent-of-code/2022/
// https://www.youtube.com/playlist?list=PLlFc5cFwUnmwxQlKf8uWp-la8BVSTH47J
// https://www.reddit.com/r/adventofcode/


/**
 * Check if IntRanges overlap
 */
infix fun IntRange.overlaps(other: IntRange): Boolean = first <= other.last && other.first <= last

/**
 * Check if InRanges overlap fully
 */
infix fun IntRange.fullyOverlaps(other: IntRange): Boolean = first <= other.first && last >= other.last

/**
 * Check if IntRanges overlap
 */
infix fun LongRange.overlaps(other: LongRange): Boolean = first <= other.last && other.first <= last

/**
 * Check if InRanges overlap fully
 */
infix fun LongRange.fullyOverlaps(other: LongRange): Boolean = first <= other.first && last >= other.last

internal object Resources {
    fun resourceAsString(fileName: String, delimiter: String = ""): String =
        resourceAsListOfString(fileName).reduce { a, b -> "$a$delimiter$b" }

    fun resourceAsText(fileName: String): String =
        File(fileName.toURI()).readText()

    fun resourceAsListOfString(fileName: String): List<String> =
        File(fileName.toURI()).readLines()

    fun resourceAsListOfInt(fileName: String): List<Int> =
        resourceAsListOfString(fileName).map { it.toInt() }

    fun resourceAsListOfLong(fileName: String): List<Long> =
        resourceAsListOfString(fileName).map { it.toLong() }

    private fun String.toURI(): URI =
        Resources.javaClass.classLoader.getResource(this)?.toURI()
            ?: throw IllegalArgumentException("Cannot find Resource: $this")
}

data class Point(
    val x: Int,
    val y: Int
)

data class LongPoint(
    val x: Long,
    val y: Long
)
