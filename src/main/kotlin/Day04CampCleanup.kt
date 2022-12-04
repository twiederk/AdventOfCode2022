class CampCleanup {

    fun createRange(data: String): IntRange {
        val borders = data.split("-")
        return IntRange(borders[0].toInt(), borders[1].toInt())
    }

    fun createRanges(data: String): Pair<IntRange,IntRange> {
        val ranges = data.split(",")
        val firstRange = createRange(ranges[0])
        val secondRange = createRange(ranges[1])
        return Pair(firstRange, secondRange)
    }

}
