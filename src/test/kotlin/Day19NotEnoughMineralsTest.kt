import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NotEnoughMineralsTest {

    val notEnoughMinerals = NotEnoughMinerals()

    val blueprintList1 = BlueprintList(
        id = 1, listOf(
            Blueprint(robot = Robot.ORE, costOre = 4),
            Blueprint(robot = Robot.CLAY, costOre = 2),
            Blueprint(robot = Robot.OBSIDIAN, costOre = 3, costClay = 14),
            Blueprint(robot = Robot.GEODE, costOre = 2, costObsidian = 7),
        )
    )

    val blueprintList2 = BlueprintList(
        id = 2, listOf(
            Blueprint(robot = Robot.ORE, costOre = 2),
            Blueprint(robot = Robot.CLAY, costOre = 3),
            Blueprint(robot = Robot.OBSIDIAN, costOre = 3, costClay = 8),
            Blueprint(robot = Robot.GEODE, costOre = 3, costObsidian = 12),
        )
    )

    @Test
    fun loadData() {

        // act
        val rawData = NotEnoughMinerals.loadData("Day19_TestData.txt")

        // assert
        assertThat(rawData).hasSize(2)
    }

    @Test
    fun parseBlueprintList() {
        // arrange
        val rawBlueprint =
            "Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian."

        // act
        val blueprint = NotEnoughMinerals.parseBlueprintList(rawBlueprint)

        // assert
        assertThat(blueprint.id).isEqualTo(1)
        assertThat(blueprint.blueprints[Robot.ORE.ordinal].costOre).isEqualTo(4)
        assertThat(blueprint.blueprints[Robot.ORE.ordinal].costClay).isEqualTo(0)
        assertThat(blueprint.blueprints[Robot.ORE.ordinal].costObsidian).isEqualTo(0)

        assertThat(blueprint.blueprints[Robot.CLAY.ordinal].costOre).isEqualTo(2)
        assertThat(blueprint.blueprints[Robot.CLAY.ordinal].costClay).isEqualTo(0)
        assertThat(blueprint.blueprints[Robot.CLAY.ordinal].costObsidian).isEqualTo(0)

        assertThat(blueprint.blueprints[Robot.OBSIDIAN.ordinal].costOre).isEqualTo(3)
        assertThat(blueprint.blueprints[Robot.OBSIDIAN.ordinal].costClay).isEqualTo(14)
        assertThat(blueprint.blueprints[Robot.OBSIDIAN.ordinal].costObsidian).isEqualTo(0)

        assertThat(blueprint.blueprints[Robot.GEODE.ordinal].costOre).isEqualTo(2)
        assertThat(blueprint.blueprints[Robot.GEODE.ordinal].costClay).isEqualTo(0)
        assertThat(blueprint.blueprints[Robot.GEODE.ordinal].costObsidian).isEqualTo(7)
    }

    @Test
    fun parseAllBlueprints() {
        // arrange
        val rawBlueprintLists = listOf(
            "Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.",
            "Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian."
        )

        // act
        val blueprintLists = NotEnoughMinerals.parseAllBlueprintLists(rawBlueprintLists)

        // assert
        assertThat(blueprintLists).hasSize(2)
    }

    @Nested
    inner class Simulation {

        @Test
        fun simulate_blueprintList_1() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprintList1, 24)

            // assert
            assertThat(totalQualityLevel).isEqualTo(9)
        }

        @Test
        fun simulate_blueprintList_2() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprintList2, 24)

            // assert
            assertThat(totalQualityLevel).isEqualTo(24)
        }

        @Test
        fun simulateAll() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulateAll(listOf(blueprintList1, blueprintList2), 24)

            // assert
            assertThat(totalQualityLevel).isEqualTo(33)
        }
    }

    @Nested
    inner class BlueprintListTest {

        @Test
        fun maxOre() {
            // act
            val maxOre = blueprintList1.maxOre

            // assert
            assertThat(maxOre).isEqualTo(4)
        }

        @Test
        fun maxClay() {
            // act
            val maxOre = blueprintList1.maxClay

            // assert
            assertThat(maxOre).isEqualTo(14)
        }

        @Test
        fun maxObsidian() {
            // act
            val maxOre = blueprintList1.maxObsidian

            // assert
            assertThat(maxOre).isEqualTo(7)
        }
    }

    @Nested
    inner class ProductionStateTest {

        @Test
        fun init() {

            // act
            val productionState = ProductionState()

            // assert
            assertThat(productionState.minute).isEqualTo(1)

            assertThat(productionState.ore).isEqualTo(1)
            assertThat(productionState.clay).isEqualTo(0)
            assertThat(productionState.obsidian).isEqualTo(0)
            assertThat(productionState.geode).isEqualTo(0)

            assertThat(productionState.robots[Robot.ORE.ordinal]).isEqualTo(1)
            assertThat(productionState.robots[Robot.CLAY.ordinal]).isEqualTo(0)
            assertThat(productionState.robots[Robot.OBSIDIAN.ordinal]).isEqualTo(0)
            assertThat(productionState.robots[Robot.GEODE.ordinal]).isEqualTo(0)
        }

        @Test
        fun compare_greater() {
            // arrange
            val productionState1 = ProductionState(geode = 1)
            val productionState2 = ProductionState(geode = 0)

            // act
            val result = productionState1.compareTo(productionState2)

            // assert
            assertThat(result).isEqualTo(1)
        }

        @Test
        fun compare_smaller() {
            // arrange
            val productionState1 = ProductionState(geode = 0)
            val productionState2 = ProductionState(geode = 1)

            // act
            val result = productionState1.compareTo(productionState2)

            // assert
            assertThat(result).isEqualTo(-1)
        }

        @Test
        fun compare_equal() {
            // arrange
            val productionState1 = ProductionState(geode = 0)
            val productionState2 = ProductionState(geode = 0)

            // act
            val result = productionState1.compareTo(productionState2)

            // assert
            assertThat(result).isEqualTo(0)
        }

        @Test
        fun calculateNextStates_minute_1() {

            // act
            val productionStates = ProductionState().calculateNextStates(blueprintList1, 24)

            // assert
            assertThat(productionStates).hasSize(2)

            // build ORE robot in minute 5
            assertThat(productionStates[0].minute).isEqualTo(5)

            assertThat(productionStates[0].ore).isEqualTo(1)
            assertThat(productionStates[0].clay).isEqualTo(0)
            assertThat(productionStates[0].obsidian).isEqualTo(0)
            assertThat(productionStates[0].geode).isEqualTo(0)

            assertThat(productionStates[0].robots[Robot.ORE.ordinal]).isEqualTo(2)
            assertThat(productionStates[0].robots[Robot.CLAY.ordinal]).isEqualTo(0)
            assertThat(productionStates[0].robots[Robot.OBSIDIAN.ordinal]).isEqualTo(0)
            assertThat(productionStates[0].robots[Robot.GEODE.ordinal]).isEqualTo(0)

            // build CLAY robot in minute 3
            assertThat(productionStates[1].minute).isEqualTo(3)
            assertThat(productionStates[1].ore).isEqualTo(1)
            assertThat(productionStates[1].clay).isEqualTo(0)
            assertThat(productionStates[1].obsidian).isEqualTo(0)
            assertThat(productionStates[1].geode).isEqualTo(0)
            assertThat(productionStates[1].robots[Robot.ORE.ordinal]).isEqualTo(1)
            assertThat(productionStates[1].robots[Robot.CLAY.ordinal]).isEqualTo(1)
            assertThat(productionStates[1].robots[Robot.OBSIDIAN.ordinal]).isEqualTo(0)
            assertThat(productionStates[1].robots[Robot.GEODE.ordinal]).isEqualTo(0)
        }

    }

    @Nested
    inner class BlueprintTest {

        @Test
        fun timeUntilBuild_canBuildClayRobotInOneTurn() {
            // arrange
            val productionState = ProductionState(
                ore = 2
            )

            // act
            val timeUntilBuild = blueprintList1.blueprints[Robot.CLAY.ordinal].timeUntilBuild(productionState)

            // assert
            assertThat(timeUntilBuild).isEqualTo(1)


        }

        @Test
        fun timeUntilBuild_canBuildClayRobotWithThreeOreRobotsInTwoTurns() {
            // arrange
            val productionState = ProductionState(
                ore = 0,
                robots = arrayOf(3, 0, 0, 0)
            )

            // act
            val timeUntilBuild = blueprintList1.blueprints[Robot.CLAY.ordinal].timeUntilBuild(productionState)

            // assert
            assertThat(timeUntilBuild).isEqualTo(2)
        }

        @Test
        fun timeUntilBuild_cantBuildClayRobotWithoutOreAndOreRobot() {
            // arrange
            val productionState = ProductionState(
                ore = 0,
                robots = arrayOf(0, 0, 0, 0)
            )

            // act
            val timeUntilBuild = blueprintList1.blueprints[Robot.CLAY.ordinal].timeUntilBuild(productionState)

            // assert
            assertThat(timeUntilBuild).isEqualTo(Int.MIN_VALUE)
        }


    }


}