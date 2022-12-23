class GrovePositioningSystem(input: List<Int>) {

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
        val fixedStep = if (step >= 0) {
            if (oldIndex + step < size) {
                step
            } else {
                step + 1
            }
        } else {
            step - 1
        }

        var newIndex = (oldIndex + fixedStep).mod(size)
        return newIndex
    }

    fun mixAll() {
        originalList.forEach { mix(it) }
    }

}

class Data(val value: Int) {

    override fun toString(): String = value.toString()
}

data class Original(val value: Int, val data: Data)

fun main() {
    val input = Resources.resourceAsListOfInt("Day20_InputData.txt")
    val grovePositioningSystem = GrovePositioningSystem(input)

    grovePositioningSystem.mixAll()

}