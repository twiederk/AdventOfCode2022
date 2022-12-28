class Day20(private val input: List<Int>) {

    private data class MappedNumber(val originalIndex: Int, val value: Long)

    private fun List<MappedNumber>.groveCoordinates(): Long {
        val zero = indexOfFirst { it.value == 0L }
        return listOf(1000, 2000, 3000).sumOf { this[(zero + it) % size].value }
    }

    private fun parseInput(): MutableList<MappedNumber> =
        input.mapIndexed { index, value ->
            MappedNumber(index, value.toLong())
        }.toMutableList()

    private fun MutableList<MappedNumber>.decrypt() {
        indices.forEach { originalIndex ->
            val index = indexOfFirst { it.originalIndex == originalIndex }
            val toBeMoved = removeAt(index)
            add((index + toBeMoved.value).mod(size), toBeMoved)
        }
    }


    fun solvePart1(): Long {
        val theList = parseInput()
        theList.decrypt()
        return theList.groveCoordinates()
    }

    val originalList: List<Original> = input.map { Original(value = it, data = Data(it)) }
    var mixList: MutableList<Data> = mutableListOf()

    init {
        originalList.forEach { mixList.add(it.data) }
    }

    fun mix(original: Original) {
        val oldIndex = mixList.indexOf(original.data)
        val toBeMoved = mixList.removeAt(oldIndex)
        val newIndex = getNewIndex(oldIndex, original.value, originalList.size)
        mixList.add(newIndex, toBeMoved)
    }

    fun getNewIndex(oldIndex: Int, step: Int, size: Int): Int {
        if (step == 0) return oldIndex
        val newIndex: Int
        if (step < 0) {
            newIndex = (oldIndex + step - 1).mod(size)
            return if (newIndex > oldIndex) newIndex else newIndex + 1
        }
        newIndex = (oldIndex + step).mod(size)
        return if (newIndex > oldIndex) newIndex else newIndex + 1

    }

    fun mixAll(): Int {
        originalList.forEach { mix(it) }
        return calculateGroveCoordinates()
    }

    fun calculateGroveCoordinates(): Int {
        val zero = mixList.indexOfFirst { it.value == 0 }
        return listOf(1000, 2000, 3000).sumOf { mixList[(zero + it) % mixList.size].value }
    }

}

class Data(val value: Int) {

    override fun toString(): String = value.toString()
}

data class Original(val value: Int, val data: Data)

fun main() {
    val input = Resources.resourceAsListOfInt("Day20_InputData.txt")
    val day20 = Day20(input)

    val groveCoordinates = day20.solvePart1()

    println("groveCoordinates = $groveCoordinates")
}