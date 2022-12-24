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
    fun openDir() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()

        // act
        val dir = noSpaceLeftOnDevice.openDir()

        // assert
        assertThat(dir).isEmpty()
        assertThat(noSpaceLeftOnDevice.openDirs).hasSize(1)
    }

    @Test
    fun addFileToOpenDirs() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        val dir = noSpaceLeftOnDevice.openDir()

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
        noSpaceLeftOnDevice.openDir()

        // act
        noSpaceLeftOnDevice.closeDir()

        // assert
        assertThat(noSpaceLeftOnDevice.openDirs).isEmpty()
        assertThat(noSpaceLeftOnDevice.closeDirs).hasSize(1)
    }

    @Test
    fun closeAllDirs() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        noSpaceLeftOnDevice.openDir()
        noSpaceLeftOnDevice.openDir()

        // act
        noSpaceLeftOnDevice.closeAllDirs()

        // assert
        assertThat(noSpaceLeftOnDevice.openDirs).isEmpty()
        assertThat(noSpaceLeftOnDevice.closeDirs).hasSize(2)
    }

    @Test
    fun dryRun() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        noSpaceLeftOnDevice.openDir()
        noSpaceLeftOnDevice.addFileToOpenDirs(14848514)
        noSpaceLeftOnDevice.addFileToOpenDirs(8504156)
        noSpaceLeftOnDevice.openDir()
        noSpaceLeftOnDevice.addFileToOpenDirs(29116)
        noSpaceLeftOnDevice.addFileToOpenDirs(2557)
        noSpaceLeftOnDevice.addFileToOpenDirs(62596)
        noSpaceLeftOnDevice.openDir()
        noSpaceLeftOnDevice.addFileToOpenDirs(584)
        noSpaceLeftOnDevice.closeDir()
        noSpaceLeftOnDevice.closeDir()
        noSpaceLeftOnDevice.openDir()
        noSpaceLeftOnDevice.addFileToOpenDirs(4060174)
        noSpaceLeftOnDevice.addFileToOpenDirs(8033020)
        noSpaceLeftOnDevice.addFileToOpenDirs(5626152)
        noSpaceLeftOnDevice.addFileToOpenDirs(7214296)
        noSpaceLeftOnDevice.closeAllDirs()

        // act
        val totalSize = noSpaceLeftOnDevice.getDirsAtMost(100_000)

        // assert
        assertThat(totalSize).isEqualTo(95437)
    }

    @Test
    fun execute() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        val terminalOutput = noSpaceLeftOnDevice.loadData(Path("src", "test", "resources", "Day07_TestData.txt"))

        // act
        val totalSize = noSpaceLeftOnDevice.execute(terminalOutput, 100_000)

        // assert
        assertThat(totalSize).isEqualTo(95437)
    }

    @Test
    fun parseCommand_openDir() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()

        // act
        val command = noSpaceLeftOnDevice.parseCommand("$ cd /")

        // assert
        assertThat(command).isInstanceOf(OsCommand.OpenDirCommand::class.java)
    }

    @Test
    fun parseCommand_closeDir() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()

        // act
        val command = noSpaceLeftOnDevice.parseCommand("$ cd ..")

        // assert
        assertThat(command).isInstanceOf(OsCommand.CloseDirCommand::class.java)
    }

    @Test
    fun parseCommand_addFile() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()

        // act
        val command = noSpaceLeftOnDevice.parseCommand("14848514 b.txt")

        // assert
        assertThat(command).isInstanceOf(OsCommand.AddFileCommand::class.java)
    }

    @Test
    fun createAddFileCommand() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()

        // act
        val addFileCommand = noSpaceLeftOnDevice.createAddFileCommand(1234)

        // assert
        assertThat(addFileCommand.size).isEqualTo(1234)
    }

    @Test
    fun calculateRequiredSpace() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        val terminalOutput = noSpaceLeftOnDevice.loadData(Path("src", "test", "resources", "Day07_TestData.txt"))
        noSpaceLeftOnDevice.execute(terminalOutput, 100_000)

        // act
        val requiredSpace = noSpaceLeftOnDevice.calculateRequiredSpace()

        // assert
        assertThat(requiredSpace).isEqualTo(8_381_165)
    }

    @Test
    fun smallestDirToDelete() {
        // arrange
        val noSpaceLeftOnDevice = NoSpaceLeftOnDevice()
        val terminalOutput = noSpaceLeftOnDevice.loadData(Path("src", "test", "resources", "Day07_TestData.txt"))
        noSpaceLeftOnDevice.execute(terminalOutput, 100_000)

        // act
        val smallestDirToDelete = noSpaceLeftOnDevice.smallestDirToDelete(8_381_165)

        // assert
        assertThat(smallestDirToDelete).isEqualTo(24933642)

    }
}