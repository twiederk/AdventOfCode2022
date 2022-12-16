// Resources
// =========
// https://todd.ginsberg.com/post/advent-of-code/2022/
// https://www.youtube.com/playlist?list=PLlFc5cFwUnmwxQlKf8uWp-la8BVSTH47J


/**
 * Check if IntRanges overlap
 */
infix fun IntRange.overlaps(other: IntRange): Boolean = first <= other.last && other.first <= last

/**
 * Check if InRanges overlap fully
 */
infix fun IntRange.fullyOverlaps(other: IntRange): Boolean = first <= other.first && last >= other.last
