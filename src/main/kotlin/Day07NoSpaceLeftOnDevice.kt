import java.nio.file.Path
import kotlin.io.path.readLines

class NoSpaceLeftOnDevice {

    val openDirs = mutableListOf<MutableList<Int>>()
    val closeDirs = mutableListOf<MutableList<Int>>()

    fun loadData(path: Path): List<String> {
        return path.readLines()
    }

    fun openDir(): MutableList<Int> {
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
        closeDirs.add(dir)
    }

    fun closeAllDirs() {
        while (openDirs.isNotEmpty()) {
            closeDir()
        }
    }

}