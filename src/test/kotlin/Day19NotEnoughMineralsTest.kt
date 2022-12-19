import org.assertj.core.api.Assertions
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

    @Test
    fun loadData() {
        // arrange

        // act
        val rawData = NotEnoughMinerals().loadData("Day19_TestData.txt")

        // assert
        assertThat(rawData).hasSize(2)
    }

}