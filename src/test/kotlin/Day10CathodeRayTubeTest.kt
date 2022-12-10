import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class CathodeRayTubeTest {

    @Test
    fun loadData() {

        // act
        val rawData = CathodeRayTube().loadData(Path("src", "test", "resources", "Day10_TestData.txt"))

        // assert
        assertThat(rawData).hasSize(146)
    }

}