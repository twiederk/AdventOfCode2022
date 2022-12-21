import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class NotEnoughMineralsTest {

    // load blueprint
    // robot factory needs factory method to produce robots based on resources known from blueprint
    // factory method checks available resources and builds most important robot
    // robots are stored in counters
    // we need a minute counter
    // simulate all blueprints
    // each simulation stops after 24 minutes
    // should we print the data to compare it to the log of aoc puzzle?
    // quality level = number of geodes * id of blueprint
    // solution = sum of quality levels

    val notEnoughMinerals = NotEnoughMinerals()
    val blueprintList = BlueprintList(
        id = 1, listOf(
            Blueprint(robot = Robot.ORE, costOre = 4),
            Blueprint(robot = Robot.CLAY, costOre = 2),
            Blueprint(robot = Robot.OBSIDIAN, costOre = 3, costClay = 14),
            Blueprint(robot = Robot.GEODE, costOre = 2, costObsidian = 7),
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
    fun parseBlueprint() {
        // arrange
        val rawBlueprint =
            "Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian."

        // act
        val blueprint = notEnoughMinerals.parseBlueprint(rawBlueprint)

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
    fun collect_oneOreRobot() {

        // act
        notEnoughMinerals.collect()

        // assert
        assertThat(notEnoughMinerals.ore).isEqualTo(1)
    }

    @Test
    fun deliver_oneClayRobot() {
        // arrange
        notEnoughMinerals.orderedRobot = Robot.CLAY

        // act
        notEnoughMinerals.deliver()

        // assert
        assertThat(notEnoughMinerals.orderedRobot).isNull()
        assertThat(notEnoughMinerals.countClayRobots).isEqualTo(1)
    }

    @Nested
    inner class Simulation {

        @Test
        fun simulate_minute_1() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprintList, 1)

            // assert
            assertThat(totalQualityLevel).isEqualTo(0)
            assertThat(notEnoughMinerals.queue).hasSize(2)
        }

        @Test
        fun simulate_minute_2() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprintList, 2)

            // assert
            assertThat(totalQualityLevel).isEqualTo(0)
//            == Minute 2 ==
//            1 ore-collecting robot collects 1 ore; you now have 2 ore.
            assertThat(notEnoughMinerals.countOreRobots).isEqualTo(1)
            assertThat(notEnoughMinerals.ore).isEqualTo(2)

        }

        @Test
        fun simulate_minute_3() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprintList, 3)

            // assert
//            == Minute 3 ==
            assertThat(totalQualityLevel).isEqualTo(0)
//            Spend 2 ore to start building a clay-collecting robot.
//            1 ore-collecting robot collects 1 ore; you now have 1 ore.
            assertThat(notEnoughMinerals.countOreRobots).isEqualTo(1)
            assertThat(notEnoughMinerals.ore).isEqualTo(1)
//            The new clay-collecting robot is ready; you now have 1 of them.
            assertThat(notEnoughMinerals.countClayRobots).isEqualTo(1)

        }

        @Test
        fun simulate_minute_4() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprintList, 4)

            // assert
            assertThat(totalQualityLevel).isEqualTo(0)
//            == Minute 4 ==
//            1 ore-collecting robot collects 1 ore; you now have 2 ore.
            assertThat(notEnoughMinerals.countOreRobots).isEqualTo(1)
            assertThat(notEnoughMinerals.ore).isEqualTo(2)
//            1 clay-collecting robot collects 1 clay; you now have 1 clay.
            assertThat(notEnoughMinerals.countClayRobots).isEqualTo(1)
            assertThat(notEnoughMinerals.clay).isEqualTo(1)
        }

        @Test
        fun simulate_minute_5() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprintList, 5)

            // assert
            assertThat(totalQualityLevel).isEqualTo(0)
//            == Minute 5 ==
//            Spend 2 ore to start building a clay-collecting robot.
//            1 ore-collecting robot collects 1 ore; you now have 1 ore.
            assertThat(notEnoughMinerals.countOreRobots).isEqualTo(1)
            assertThat(notEnoughMinerals.ore).isEqualTo(1)
//            1 clay-collecting robot collects 1 clay; you now have 2 clay.
//            The new clay-collecting robot is ready; you now have 2 of them.
            assertThat(notEnoughMinerals.countClayRobots).isEqualTo(2)
            assertThat(notEnoughMinerals.clay).isEqualTo(2)
        }

        @Test
        fun simulate_minute_6() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprintList, 6)

            // assert
            assertThat(totalQualityLevel).isEqualTo(0)
//            == Minute 6 ==
//            1 ore-collecting robot collects 1 ore; you now have 2 ore.
            assertThat(notEnoughMinerals.countOreRobots).isEqualTo(1)
            assertThat(notEnoughMinerals.ore).isEqualTo(2)

//            2 clay-collecting robots collect 2 clay; you now have 4 clay.
            assertThat(notEnoughMinerals.countClayRobots).isEqualTo(2)
            assertThat(notEnoughMinerals.clay).isEqualTo(4)
        }

        @Test
        fun simulate_minute_7() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprintList, 7)

            // assert
            assertThat(totalQualityLevel).isEqualTo(0)
//            Spend 2 ore to start building a clay-collecting robot.
//            1 ore-collecting robot collects 1 ore; you now have 1 ore.
            assertThat(notEnoughMinerals.countOreRobots).isEqualTo(1)
            assertThat(notEnoughMinerals.ore).isEqualTo(1)
//            2 clay-collecting robots collect 2 clay; you now have 6 clay.
//            The new clay-collecting robot is ready; you now have 3 of them.
            assertThat(notEnoughMinerals.countClayRobots).isEqualTo(3)
            assertThat(notEnoughMinerals.clay).isEqualTo(6)
        }

        @Test
        fun simulate_minute_8() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprintList, 8)

            // assert
            assertThat(totalQualityLevel).isEqualTo(0)
//            == Minute 8 ==
//            1 ore-collecting robot collects 1 ore; you now have 2 ore.
            assertThat(notEnoughMinerals.countOreRobots).isEqualTo(1)
            assertThat(notEnoughMinerals.ore).isEqualTo(2)
//            3 clay-collecting robots collect 3 clay; you now have 9 clay.
            assertThat(notEnoughMinerals.countClayRobots).isEqualTo(3)
            assertThat(notEnoughMinerals.clay).isEqualTo(9)
        }

        @Test
        fun simulate_minute_9() {
            // arrange
            notEnoughMinerals.debug = true

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprintList, 9)

            // assert
            assertThat(totalQualityLevel).isEqualTo(0)
//            == Minute 9 ==
//            1 ore-collecting robot collects 1 ore; you now have 3 ore.
            assertThat(notEnoughMinerals.countOreRobots).isEqualTo(1)
            assertThat(notEnoughMinerals.ore).isEqualTo(3)
//            3 clay-collecting robots collect 3 clay; you now have 12 clay.
            assertThat(notEnoughMinerals.countClayRobots).isEqualTo(3)
            assertThat(notEnoughMinerals.clay).isEqualTo(12)
        }

        @Test
        fun simulate_minute_10() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprintList, 10)

            // assert
            assertThat(totalQualityLevel).isEqualTo(0)
//            == Minute 10 ==
//            1 ore-collecting robot collects 1 ore; you now have 4 ore.
            assertThat(notEnoughMinerals.countOreRobots).isEqualTo(1)
            assertThat(notEnoughMinerals.ore).isEqualTo(4)
//            3 clay-collecting robots collect 3 clay; you now have 15 clay.
            assertThat(notEnoughMinerals.countClayRobots).isEqualTo(3)
            assertThat(notEnoughMinerals.clay).isEqualTo(15)
        }
    }

    @Nested
    inner class BlueprintTest {

        @Test
        fun maxOre() {
            // act
            val maxOre = blueprintList.maxOre

            // assert
            assertThat(maxOre).isEqualTo(4)
        }

        @Test
        fun maxClay() {
            // act
            val maxOre = blueprintList.maxClay

            // assert
            assertThat(maxOre).isEqualTo(14)
        }

        @Test
        fun maxObsidian() {
            // act
            val maxOre = blueprintList.maxOre

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

            assertThat(productionState.ore).isEqualTo(0)
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
            // arrange

            // act
            val productionStates = ProductionState().calculateNextStates(blueprintList, 24)

            // assert
            assertThat(productionStates).hasSize(2)
        }

    }


}