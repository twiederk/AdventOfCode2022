import java.nio.file.Path
import kotlin.io.path.readLines

class NoSpaceLeftOnDevice {

    val openDirs = mutableListOf<MutableList<Int>>()
    val closedDirs = mutableListOf<MutableList<Int>>()

    fun loadData(path: Path): List<String> {
        return path.readLines()
    }

    fun createDir(): MutableList<Int> {
        val dir = mutableListOf<Int>()
        openDirs.add(dir)
        return dir
    }

    fun addFileToOpenDirs(size: Int) {
        for (dir in openDirs) {
            dir.add(size)
        }
    }

    fun closeDir() {
        val dir = openDirs.removeLast()
        closedDirs.add(dir)
    }

}