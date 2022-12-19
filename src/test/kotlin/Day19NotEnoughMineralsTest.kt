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
    val blueprint1 = Blueprint(
        id = 1,
        oreRobotOre = 4,
        clayRobotOre = 2,
        obsidianRobotOre = 3,
        obsidianRobotClay = 14,
        geodeRobotOre = 2,
        geodeRobotObsidian = 7
    )


    @Test
    fun loadData() {
        // arrange

        // act
        val rawData = notEnoughMinerals.loadData("Day19_TestData.txt")

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
        assertThat(blueprint.oreRobotOre).isEqualTo(4)
        assertThat(blueprint.clayRobotOre).isEqualTo(2)
        assertThat(blueprint.obsidianRobotOre).isEqualTo(3)
        assertThat(blueprint.obsidianRobotClay).isEqualTo(14)
        assertThat(blueprint.geodeRobotOre).isEqualTo(2)
        assertThat(blueprint.geodeRobotObsidian).isEqualTo(7)

    }

    @Test
    fun collect_oneOreRobot() {

        // act
        notEnoughMinerals.collect()

        // assert
        assertThat(notEnoughMinerals.ore).isEqualTo(1)
    }

    @Test
    fun order_oneClayRobot() {
        // arrange
        notEnoughMinerals.debug = true
        notEnoughMinerals.ore = 2

        // act
        notEnoughMinerals.order()

        // assert
        assertThat(notEnoughMinerals.orderClayRobot).isEqualTo(1)
        assertThat(notEnoughMinerals.ore).isEqualTo(0)
    }

    @Test
    fun deliver_oneClayRobot() {
        // arrange
        notEnoughMinerals.orderClayRobot = 1

        // act
        notEnoughMinerals.deliver()

        // assert
        assertThat(notEnoughMinerals.orderClayRobot).isEqualTo(0)
        assertThat(notEnoughMinerals.countClayRobots).isEqualTo(1)
    }

    @Test
    fun calculateQualityLevel() {
        // arrange
        notEnoughMinerals.countGeodeRobots = 5
        val blueprint = Blueprint(2, 0, 0, 0, 0, 0, 0)

        // act
        val qualityLevel = notEnoughMinerals.calculateQualityLevel(blueprint)

        // assert
        assertThat(qualityLevel).isEqualTo(10)
    }


    @Nested
    inner class Simulation {

        @Test
        fun simulate_minute1() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprint1, 1)

            // assert
            assertThat(totalQualityLevel).isEqualTo(0)
//            == Minute 1 ==
//            1 ore-collecting robot collects 1 ore; you now have 1 ore.
            assertThat(notEnoughMinerals.countOreRobots).isEqualTo(1)
            assertThat(notEnoughMinerals.ore).isEqualTo(1)
        }

        @Test
        fun simulate_minute2() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprint1, 2)

            // assert
            assertThat(totalQualityLevel).isEqualTo(0)
//            == Minute 2 ==
//            1 ore-collecting robot collects 1 ore; you now have 2 ore.
            assertThat(notEnoughMinerals.countOreRobots).isEqualTo(1)
            assertThat(notEnoughMinerals.ore).isEqualTo(2)

        }

        @Test
        fun simulate_minute3() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprint1, 3)

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
        fun simulate_minute4() {

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprint1, 4)

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
        fun simulate_minute5() {
            // arrange
            notEnoughMinerals.debug = true

            // act
            val totalQualityLevel = notEnoughMinerals.simulate(blueprint1, 5)

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

    }
}