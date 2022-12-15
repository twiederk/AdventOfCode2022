infix fun IntRange.overlaps(other: IntRange): Boolean = first <= other.last && other.first <= last

infix fun IntRange.fullyOverlaps(other: IntRange): Boolean = first <= other.first && last >= other.last
