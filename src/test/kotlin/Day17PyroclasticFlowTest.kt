import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class PyroclasticFlowTest {

    @Test
    fun loadData() {

        // act
        val pyroclasticFlow = PyroclasticFlow(Resources.resourceAsText("Day17_TestData.txt"))

        // assert
        assertThat(pyroclasticFlow.input).isEqualTo(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>")
    }

}