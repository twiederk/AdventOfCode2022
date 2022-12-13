import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class DistressSignalTest {

    private val distressSignal = DistressSignal()

    @Test
    fun loadData() {

        // act
        val packets = distressSignal.loadData(Path("src", "test", "resources", "Day13_TestData.txt"))

        // assert
        assertThat(packets).hasSize(8)
    }

    @Test
    fun decode_packet_1() {

        // act
        val order = distressSignal.decode(Pair("[1,1,3,1,1]", "[1,1,5,1,1]"))

        // assert
        assertThat(order).isEqualTo(Order.RIGHT)

    }
}