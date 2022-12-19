class NotEnoughMinerals {

    fun loadData(fileName: String): List<String> = Resources.resourceAsListOfString(fileName)

    fun parseBlueprint(rawBlueprint: String): Blueprint {
        val id = rawBlueprint.substringAfter("Blueprint ").substringBefore(":").toInt()
        return Blueprint(id)
    }

}

data class Blueprint(
    val id: Int,
)

