import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.readLines

class RucksackReorganization {

    fun calculatePriority(c: Char): Int {
        return priorityMap.getValue(c)
    }

    fun findItem(allItems: String): Char {
        val firstCompartment = getFirstCompartment(allItems)
        val secondCompartment = getSecondCompartment(allItems)
        for (item in firstCompartment) {
            if (item in secondCompartment) {
                return item
            }
        }
        throw IllegalStateException("Can't find item")
    }

    fun getFirstCompartment(allItems: String): String {
        val totalOfItems = allItems.length
        return allItems.substring(0, totalOfItems / 2)
    }

    fun getSecondCompartment(allItems: String): String {
        val totalOfItems = allItems.length
        return allItems.substring(totalOfItems / 2)
    }

    fun loadRucksack(path: Path): List<String> {
        return path.readLines()
    }

    fun reorganize(allRucksacks: List<String>): Int {
        var sumOfPriorities = 0
        for (rucksack in allRucksacks) {
            val item = findItem(rucksack)
            sumOfPriorities += calculatePriority(item)
        }
        return sumOfPriorities
    }

    fun findBadge(group: List<String>): Char {
        val rucksack1 = group[0]
        val rucksack2 = group[1]
        val rucksack3 = group[2]
        val badges1 = itemsInBothRucksacks(rucksack1, rucksack2)
        val badges2 = itemsInBothRucksacks(badges1, rucksack3)
        return badges2[0]
    }

    fun itemsInBothRucksacks(rucksack1: String, rucksack2: String): String {
        var badges = ""
        for (item in rucksack1) {
            if (item in rucksack2) {
                badges += item
            }
        }
        return badges.toCharArray().distinct().joinToString(separator = "")
    }

    fun organizeBadges(rucksack: List<String>): Int {
        val groups = rucksack.chunked(3)
        var sumOfBadges = 0
        for (group in groups) {
            val badge = findBadge(group)
            sumOfBadges += calculatePriority(badge)
        }
        return sumOfBadges
    }

    private val priorityMap : Map<Char,Int> =
        mapOf(
            'a' to 1,
            'b' to 2,
            'c' to 3,
            'd' to 4,
            'e' to 5,
            'f' to 6,
            'g' to 7,
            'h' to 8,
            'i' to 9,
            'j' to 10,
            'k' to 11,
            'l' to 12,
            'm' to 13,
            'n' to 14,
            'o' to 15,
            'p' to 16,
            'q' to 17,
            'r' to 18,
            's' to 19,
            't' to 20,
            'u' to 21,
            'v' to 22,
            'w' to 23,
            'x' to 24,
            'y' to 25,
            'z' to 26,
            'A' to 27,
            'B' to 28,
            'C' to 29,
            'D' to 30,
            'E' to 31,
            'F' to 32,
            'G' to 33,
            'H' to 34,
            'I' to 35,
            'J' to 36,
            'K' to 37,
            'L' to 38,
            'M' to 39,
            'N' to 40,
            'O' to 41,
            'P' to 42,
            'Q' to 43,
            'R' to 44,
            'S' to 45,
            'T' to 46,
            'U' to 47,
            'V' to 48,
            'W' to 49,
            'X' to 50,
            'Y' to 51,
            'Z' to 52)

}

fun main() {

    val rucksack = RucksackReorganization().loadRucksack(Path("src", "main", "resources", "Day03_Part1_InputData.txt"))
    val sumOfPriorities = RucksackReorganization().reorganize(rucksack)
    println("sumOfPriorities = $sumOfPriorities")

}