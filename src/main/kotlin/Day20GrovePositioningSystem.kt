class GrovePositioningSystem(input: List<Int>) {

    val originalList: List<Original> = input.map { Original(value = it, data = Data(it)) }
    var mixList: MutableList<Data> = mutableListOf()

    init {
        originalList.forEach { mixList.add(it.data) }
    }
}

class Data(val value: Int)

data class Original(val value: Int, val data: Data)