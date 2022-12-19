class NotEnoughMinerals {

    fun loadData(fileName: String): List<String> = Resources.resourceAsListOfString(fileName)

    fun parseBlueprint(rawBlueprint: String): Blueprint {
        val id = rawBlueprint.substringAfter("Blueprint ").substringBefore(":").toInt()
        val robotResources = rawBlueprint.substringAfter(":").split('.')
//        [ Each ore robot costs 4 ore,  Each clay robot costs 2 ore,  Each obsidian robot costs 3 ore and 14 clay,  Each geode robot costs 2 ore and 7 obsidian, ]
//        " Each ore robot costs 4 ore"
//        " Each clay robot costs 2 ore"
//        " Each obsidian robot costs 3 ore and 14 clay"
//        " Each geode robot costs 2 ore and 7 obsidian"
//        ""

        val oreRobot = robotResources[0].substringAfter("costs ").substringBefore(" ore").toInt()
        println(robotResources)
        return Blueprint(id, oreRobot)
    }

}

data class Blueprint(
    val id: Int,
    val oreRobot: Int,
)

