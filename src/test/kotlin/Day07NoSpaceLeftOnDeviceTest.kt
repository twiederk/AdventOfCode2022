import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class NoSpaceLeftOnDeviceTest {

    @Test
    fun loadData() {

        // act
        val terminalOutput = NoSpaceLeftOnDevice().loadData(Path("src", "test", "resources", "Day07_TestData.txt"))

        // assert
        assertThat(terminalOutput).hasSize(23)
    }

    @Test
    fun createDir() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()

        // act
        val dir = noSpaceLeftOnDevice.createDir()

        // assert
        assertThat(dir).isEmpty()
        assertThat(noSpaceLeftOnDevice.openDirs).hasSize(1)
    }

    @Test
    fun addFileToOpenDirs() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        val dir = noSpaceLeftOnDevice.createDir()

        // act
        noSpaceLeftOnDevice.addFileToOpenDirs(1000)

        // assert
        assertThat(dir).hasSize(1)
        assertThat(dir[0]).isEqualTo(1000)
    }

    @Test
    fun closeDir() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        noSpaceLeftOnDevice.createDir()

        // act
        noSpaceLeftOnDevice.closeDir()

        // assert
        assertThat(noSpaceLeftOnDevice.openDirs).isEmpty()
        assertThat(noSpaceLeftOnDevice.closedDirs).hasSize(1)

    }
}