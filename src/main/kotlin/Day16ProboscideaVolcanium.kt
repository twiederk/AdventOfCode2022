class ProboscideaVolcanium {

    fun loadData(fileName: String): List<String> {
        return Resources.resourceAsListOfString(fileName)
    }

    fun createPipes(rawData: List<String>): List<Pipe> =
        rawData.map { row ->
            Pipe(
                row.substringAfter("Valve ").substringBefore(" has "),
                row.substringAfter("rate=").substringBefore(";").toInt()
            )
        }

}

data class Pipe(val name: String, val flowRate: Int)