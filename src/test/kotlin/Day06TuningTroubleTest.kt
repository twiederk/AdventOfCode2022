import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class TuningTroubleTest {

    @Test
    fun loadData() {
        // arrange

        // act
        val dataStream = TuningTrouble().loadData(Path("src", "test", "resources", "Day06_TestData.txt"))

        // assert
        assertThat(dataStream).isEqualTo("mjqjpqmgbljsphdztnvjfqwrcgsmlb")
    }

    @Test
    fun createWindows() {
        // arrange
        val dataStream = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"

        // act
        val windows = TuningTrouble().createWindows(dataStream)

        // assert
        assertThat(windows).hasSize(27)
        assertThat(windows[0]).isEqualTo("mjqj")
        assertThat(windows[1]).isEqualTo("jqjp")
    }

    @Test
    fun isStartOfPacketMarker_mjgj_false() {
        // arrange

        // act
        val startOfPacketMarker = TuningTrouble().isStartOfPacketMarker("mjqj")

        // assert
        assertThat(startOfPacketMarker).isFalse
    }

    @Test
    fun isStartOfPacketMarker_mjgx_true() {
        // arrange

        // act
        val startOfPacketMarker = TuningTrouble().isStartOfPacketMarker("mjqx")

        // assert
        assertThat(startOfPacketMarker).isTrue
    }

    @Test
    fun findStartOfPacketMarker_example1_position7() {
        // arrange
        val dataStream = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
        val windows = TuningTrouble().createWindows(dataStream)

        // act
        val position = TuningTrouble().findStartOfPacketMarker(windows)

        // assert
        assertThat(position).isEqualTo(7)
    }

    @Test
    fun findStartOfPacketMarker_example2_position5() {
        // arrange
        val dataStream = "bvwbjplbgvbhsrlpgdmjqwftvncz"
        val windows = TuningTrouble().createWindows(dataStream)

        // act
        val position = TuningTrouble().findStartOfPacketMarker(windows)

        // assert
        assertThat(position).isEqualTo(5)
    }

    @Test
    fun findStartOfPacketMarker_example3_position6() {
        // arrange
        val dataStream = "nppdvjthqldpwncqszvftbrmjlhg"
        val windows = TuningTrouble().createWindows(dataStream)

        // act
        val position = TuningTrouble().findStartOfPacketMarker(windows)

        // assert
        assertThat(position).isEqualTo(6)
    }

}