class NotEnoughMinerals(
    var debug: Boolean = false
) {

    var oreRobots = 1

    var ore: Int = 0

    fun loadData(fileName: String): List<String> = Resources.resourceAsListOfString(fileName)

    fun parseBlueprint(rawBlueprint: String): Blueprint {
        val id = rawBlueprint.substringAfter("Blueprint ").substringBefore(":").toInt()
        val robotResources = rawBlueprint.substringAfter(":").split('.')

        val oreRobotOre = robotResources[0].substringAfter("costs ").substringBefore(" ore").toInt()
        val clayRobotOre = robotResources[1].substringAfter("costs ").substringBefore(" ore").toInt()
        val obsidianRobotOre = robotResources[2].substringAfter("costs ").substringBefore(" ore").toInt()
        val obsidianRobotClay = robotResources[2].substringAfter("and ").substringBefore(" clay").toInt()
        val geodeRobotOre = robotResources[3].substringAfter("costs ").substringBefore(" ore").toInt()
        val geodeRobotObsidian = robotResources[3].substringAfter("and ").substringBefore(" obsidian").toInt()
        return Blueprint(
            id,
            oreRobotOre,
            clayRobotOre,
            obsidianRobotOre,
            obsidianRobotClay,
            geodeRobotOre,
            geodeRobotObsidian
        )
    }

    fun collect() {
        debug("$oreRobots ore-collecting robot collects $oreRobots ore; you now have $ore ore.")
        ore += oreRobots
    }

    private fun debug(message: String) {
        if (debug) println(message)
    }

}

data class Blueprint(
    val id: Int,
    val oreRobotOre: Int,
    val clayRobotOre: Int,
    val obsidianRobotOre: Int,
    val obsidianRobotClay: Int,
    val geodeRobotOre: Int,
    val geodeRobotObsidian: Int,
)

