class GrovePositioningSystem(input: List<Int>) {

    val originalList: List<Original> = input.map { Original(value = it, data = Data(it)) }
    var mixList: MutableList<Data> = mutableListOf()

    init {
        originalList.forEach { mixList.add(it.data) }
    }

    fun mix(original: Original) {
        val index = mixList.indexOf(original.data)
        val toBeMoved = mixList.removeAt(index)
        mixList.add((index + original.value) % originalList.size, toBeMoved)
    }

}

class Data(val value: Int)

data class Original(val value: Int, val data: Data)