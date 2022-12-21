import java.util.*
import kotlin.math.ceil

class NotEnoughMinerals {

    companion object {
        fun loadData(fileName: String): List<String> = Resources.resourceAsListOfString(fileName)

    }

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

    fun simulate(blueprintList: BlueprintList, maxMinutes: Int): Int {
        var maxGeodes = 0
        val queue = PriorityQueue<ProductionState>().apply { add(ProductionState()) }

        while (queue.isNotEmpty()) {
            val productionState = queue.poll()
            queue.addAll(productionState.calculateNextStates(blueprintList, maxMinutes))
            maxGeodes = maxOf(maxGeodes, productionState.geode)
        }
        return blueprintList.id * maxGeodes
    }

    fun simulateAll(blueprintLists: List<BlueprintList>, maxMinute: Int): Int {
        return blueprintLists.sumOf { simulate(it, maxMinute) }
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
        val timeRequired = timeUntilBuild(productionState)
        return productionState.copy(
            minute = productionState.minute + timeRequired,

            ore = (productionState.ore - costOre) + (timeRequired * productionState.robots[Robot.ORE.ordinal]),
            clay = (productionState.clay - costClay) + (timeRequired * productionState.robots[Robot.CLAY.ordinal]),
            obsidian = (productionState.obsidian - costObsidian) + (timeRequired * productionState.robots[Robot.OBSIDIAN.ordinal]),
            geode = productionState.geode + (timeRequired * productionState.robots[Robot.GEODE.ordinal]),

            robots = arrayOf(
                productionState.robots[Robot.ORE.ordinal] + (if (robot == Robot.ORE) 1 else 0),
                productionState.robots[Robot.CLAY.ordinal] + (if (robot == Robot.CLAY) 1 else 0),
                productionState.robots[Robot.OBSIDIAN.ordinal] + (if (robot == Robot.OBSIDIAN) 1 else 0),
                productionState.robots[Robot.GEODE.ordinal] + (if (robot == Robot.GEODE) 1 else 0),
            )
        )
    }

    fun timeUntilBuild(productionState: ProductionState): Int {
        return maxOf(
            if (costOre <= productionState.ore) 0 else ceil((costOre - productionState.ore) / productionState.robots[Robot.ORE.ordinal].toFloat()).toInt(),
            if (costClay <= productionState.clay) 0 else ceil((costClay - productionState.clay) / productionState.robots[Robot.CLAY.ordinal].toFloat()).toInt(),
            if (costObsidian <= productionState.obsidian) 0 else ceil((costObsidian - productionState.obsidian) / productionState.robots[Robot.OBSIDIAN.ordinal].toFloat()).toInt()
        ) + 1
    }
}

enum class Robot {
    ORE, CLAY, OBSIDIAN, GEODE
}

data class ProductionState(
    val minute: Int = 1,
    val ore: Int = 1,
    val clay: Int = 0,
    val obsidian: Int = 0,
    val geode: Int = 0,
    val robots: Array<Int> = arrayOf(1, 0, 0, 0)
) : Comparable<ProductionState> {

    override fun compareTo(other: ProductionState): Int = geode.compareTo(other.geode)

    fun calculateNextStates(blueprintList: BlueprintList, maxMinutes: Int): List<ProductionState> {
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
