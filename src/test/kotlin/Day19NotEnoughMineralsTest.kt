import org.assertj.core.api.Assertions.*
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

    private val notEnoughMinerals = NotEnoughMinerals()

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
        val rawBlueprint = "Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian."

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
        assertThat(notEnoughMinerals.countClayRobot).isEqualTo(1)
    }

}