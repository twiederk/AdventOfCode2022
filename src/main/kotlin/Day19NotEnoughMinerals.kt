class NotEnoughMinerals(
    var debug: Boolean = false
) {

    var orderClayRobot = 0

    var countOreRobots = 1
    var countClayRobots = 0
    var countObsidianRobots = 0
    var countGeodeRobots = 0

    var ore: Int = 0
    var clay: Int = 0

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
        ore += countOreRobots
        debug("$countOreRobots ore-collecting robot collects $countOreRobots ore; you now have $ore ore.")
    }

    private fun debug(message: String) {
        if (debug) println(message)
    }

    fun order() {
        if (ore >= 2) {
            orderClayRobot++
            ore -= 2
            debug("Spend 2 ore to start building a clay-collecting robot.")
        }
    }

    fun deliver() {
        if (orderClayRobot == 1) {
            countClayRobots += orderClayRobot
            orderClayRobot = 0
            debug("The new clay-collecting robot is ready; you now have $countClayRobots of them.")
        }
    }

    fun simulate(blueprint: Blueprint, maxMinutes: Int): Int {
        for(minute in 1..maxMinutes) {
            debug("\n== Minute $minute ==")
            order()
            collect()
            deliver()
        }
        return calculateQualityLevel(blueprint)
    }

    fun calculateQualityLevel(blueprint: Blueprint): Int {
        return blueprint.id * countGeodeRobots
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

