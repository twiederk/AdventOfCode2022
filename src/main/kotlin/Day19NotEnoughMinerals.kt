class NotEnoughMinerals(
    var debug: Boolean = false
) {

    var orderClayRobot = 0
    var orderObsidianRobot = 0

    var countOreRobots = 1
    var countClayRobots = 0
    var countObsidianRobots = 0
    var countGeodeRobots = 0

    var ore: Int = 0
    var clay: Int = 0

    fun loadData(fileName: String): List<String> = Resources.resourceAsListOfString(fileName)

    fun parseBlueprint(rawBlueprint: String): Blueprints {
        val id = rawBlueprint.substringAfter("Blueprint ").substringBefore(":").toInt()
        val robotResources = rawBlueprint.substringAfter(":").split('.')

        val oreRobotOre = robotResources[0].substringAfter("costs ").substringBefore(" ore").toInt()
        val clayRobotOre = robotResources[1].substringAfter("costs ").substringBefore(" ore").toInt()
        val obsidianRobotOre = robotResources[2].substringAfter("costs ").substringBefore(" ore").toInt()
        val obsidianRobotClay = robotResources[2].substringAfter("and ").substringBefore(" clay").toInt()
        val geodeRobotOre = robotResources[3].substringAfter("costs ").substringBefore(" ore").toInt()
        val geodeRobotObsidian = robotResources[3].substringAfter("and ").substringBefore(" obsidian").toInt()
        return Blueprints(
            id,
            oreRobotOre,
            clayRobotOre,
            obsidianRobotOre,
            obsidianRobotClay,
            geodeRobotOre,
            geodeRobotObsidian
        )
    }

    private fun debug(message: String) {
        if (debug) println(message)
    }

    fun order() {
        when (calculateEvolutionLevel()) {
            1 -> {
                println("...want to order clay-collection robot")
                orderClayCollectingRobot()
            }
            2 -> {
                println("...want to order obsidian-collection robot")
                orderObsidianCollectingRobot()
            }

            3 -> {
                println("...want to order geode-collection robot")

            }
        }
    }

    private fun orderClayCollectingRobot() {
        if (ore >= 2) {
            orderClayRobot++
            ore -= 2
            debug("Spend 2 ore to start building a clay-collecting robot.")
        }
    }


    private fun orderObsidianCollectingRobot() {
        if (ore >= 3 && clay >= 14) {
            orderObsidianRobot++
            ore -= 3
            debug("Spend 3 ore and 14 clay clay to start building a obsidian-collecting robot.")
        }
    }

    fun collect() {
        if (countOreRobots > 0) {
            ore += countOreRobots
            debug("$countOreRobots ore-collecting robot collects $countOreRobots ore; you now have $ore ore.")
        }

        if (countClayRobots > 0) {
            clay += countClayRobots
            debug("$countClayRobots clay-collecting robot collects $countClayRobots clay; you now have $clay clay.")
        }
    }

    fun deliver() {
        if (orderClayRobot == 1) {
            countClayRobots += orderClayRobot
            orderClayRobot = 0
            debug("The new clay-collecting robot is ready; you now have $countClayRobots of them.")
        }
        if (orderObsidianRobot == 1) {
            countObsidianRobots += orderObsidianRobot
            orderObsidianRobot = 0
            debug("The new obsidian-collecting robot is ready; you now have $countObsidianRobots of them.")
        }

    }

    fun calculateEvolutionLevel(): Int {
        if (countObsidianRobots > 0) return 3
        if (countClayRobots > 0) return 2
        if (countOreRobots > 0) return 1
        throw IllegalArgumentException("unknown evolution level")
    }

    fun simulate(blueprint: Blueprints, maxMinutes: Int): Int {
        for (minute in 1..maxMinutes) {
            debug("\n== Minute $minute ==")
            order()
            collect()
            deliver()
        }
        return calculateQualityLevel(blueprint)
    }

    fun calculateQualityLevel(blueprint: Blueprints): Int {
        return blueprint.id * countGeodeRobots
    }

}

data class Blueprints(
    val id: Int,
    val oreRobotOre: Int,
    val clayRobotOre: Int,
    val obsidianRobotOre: Int,
    val obsidianRobotClay: Int,
    val geodeRobotOre: Int,
    val geodeRobotObsidian: Int,
)

data class Blueprint(
    val robot: Robot,
    val ore: Int = 0,
    val clay: Int = 0,
    val obsidian: Int = 0
)

enum class Robot {
    ORE, CLAY, OBSIDIAN, GEODE
}