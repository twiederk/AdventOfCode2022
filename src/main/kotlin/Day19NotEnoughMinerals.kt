import java.util.*

class NotEnoughMinerals(
    var debug: Boolean = false
) {

    companion object {
        fun loadData(fileName: String): List<String> = Resources.resourceAsListOfString(fileName)

    }


    val queue = PriorityQueue<ProductionState>()
    var countOreRobots = 1
    var countClayRobots = 0
    var countObsidianRobots = 0
    var countGeodeRobots = 0

    var ore: Int = 0
    var clay: Int = 0
    var obsidian: Int = 0

    var orderedRobot: Robot? = null


    fun parseBlueprint(rawBlueprint: String): BlueprintList {
        val id = rawBlueprint.substringAfter("Blueprint ").substringBefore(":").toInt()
        val robotResources = rawBlueprint.substringAfter(":").split('.')

        val oreRobotOre = robotResources[0].substringAfter("costs ").substringBefore(" ore").toInt()

        val clayRobotOre = robotResources[1].substringAfter("costs ").substringBefore(" ore").toInt()

        val obsidianRobotOre = robotResources[2].substringAfter("costs ").substringBefore(" ore").toInt()
        val obsidianRobotClay = robotResources[2].substringAfter("and ").substringBefore(" clay").toInt()

        val geodeRobotOre = robotResources[3].substringAfter("costs ").substringBefore(" ore").toInt()
        val geodeRobotObsidian = robotResources[3].substringAfter("and ").substringBefore(" obsidian").toInt()

        val blueprints = listOf(
            Blueprint(Robot.ORE, costOre = oreRobotOre),
            Blueprint(Robot.CLAY, costOre = clayRobotOre),
            Blueprint(Robot.OBSIDIAN, costOre = obsidianRobotOre, costClay = obsidianRobotClay),
            Blueprint(Robot.GEODE, costOre = geodeRobotOre, costObsidian = geodeRobotObsidian)
        )

        return BlueprintList(id, blueprints)
    }

    private fun debug(message: String) {
        if (debug) println(message)
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
        when (orderedRobot) {
            Robot.ORE -> {
                countOreRobots++
                orderedRobot = null
                debug("The new ore-collecting robot is ready; you now have $countOreRobots of them.")
            }

            Robot.CLAY -> {
                countClayRobots++
                orderedRobot = null
                debug("The new clay-collecting robot is ready; you now have $countClayRobots of them.")
            }

            Robot.OBSIDIAN -> {
                countObsidianRobots++
                orderedRobot = null
                debug("The new obsidian-collecting robot is ready; you now have $countObsidianRobots of them.")
            }

            Robot.GEODE -> {
                countGeodeRobots++
                orderedRobot = null
                debug("The new geode-collecting robot is ready; you now have $countGeodeRobots of them.")
            }

            else -> {}
        }
    }

    fun simulate(blueprintList: BlueprintList, maxMinutes: Int): Int {
        var maxGeodes = 0
        val queue = PriorityQueue<ProductionState>().apply { add(ProductionState()) }

        while (queue.isNotEmpty()) {
            val productionState = queue.poll()
            queue.addAll(productionState.calculateNextStates(blueprintList, maxMinutes))
            maxGeodes = maxOf(maxGeodes, productionState.geode)
        }
        return maxGeodes
    }

}

data class BlueprintList(
    val id: Int,
    val blueprints: List<Blueprint>
) {
    val maxOre: Int =
        maxOf(
            blueprints[Robot.ORE.ordinal].costOre,
            blueprints[Robot.CLAY.ordinal].costOre,
            blueprints[Robot.OBSIDIAN.ordinal].costOre,
            blueprints[Robot.GEODE.ordinal].costOre
        )

    val maxClay: Int =
        maxOf(
            blueprints[Robot.ORE.ordinal].costOre,
            blueprints[Robot.CLAY.ordinal].costClay,
            blueprints[Robot.OBSIDIAN.ordinal].costClay,
            blueprints[Robot.GEODE.ordinal].costClay
        )

    val maxObsidian: Int =
        maxOf(
            blueprints[Robot.ORE.ordinal].costOre,
            blueprints[Robot.CLAY.ordinal].costObsidian,
            blueprints[Robot.OBSIDIAN.ordinal].costObsidian,
            blueprints[Robot.GEODE.ordinal].costObsidian
        )
}

data class Blueprint(
    val robot: Robot,
    val costOre: Int = 0,
    val costClay: Int = 0,
    val costObsidian: Int = 0
) {
    fun scheduleBuild(productionState: ProductionState): ProductionState {
        TODO("Not yet implemented")
    }
}

enum class Robot {
    ORE, CLAY, OBSIDIAN, GEODE
}

data class ProductionState(
    val minute: Int = 1,
    val ore: Int = 0,
    val clay: Int = 0,
    val obsidian: Int = 0,
    val geode: Int = 0,
    val robots: Array<Int> = arrayOf(1, 0, 0, 0)
) : Comparable<ProductionState> {

    override fun compareTo(other: ProductionState): Int = geode.compareTo(other.geode)

    fun calculateNextStates(blueprintList: BlueprintList, maxMinutes: Int): Collection<ProductionState> {
        val nextStates = mutableListOf<ProductionState>()
        if (minute < maxMinutes) {
            if (blueprintList.maxOre > robots[Robot.ORE.ordinal] && ore > 0) {
                nextStates += blueprintList.blueprints[Robot.ORE.ordinal].scheduleBuild(this)
            }
            if (blueprintList.maxClay > robots[Robot.CLAY.ordinal] && ore > 0) {
                nextStates += blueprintList.blueprints[Robot.CLAY.ordinal].scheduleBuild(this)
            }
            if (blueprintList.maxObsidian > robots[Robot.OBSIDIAN.ordinal] && ore > 0 && clay > 0) {
                nextStates += blueprintList.blueprints[Robot.OBSIDIAN.ordinal].scheduleBuild(this)
            }
            if (ore > 0 && obsidian > 0) {
                nextStates += blueprintList.blueprints[Robot.GEODE.ordinal].scheduleBuild(this)
            }
        }
        return nextStates.filter { it.minute <= maxMinutes }
    }


}
