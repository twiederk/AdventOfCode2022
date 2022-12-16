class ProboscideaVolcanium {

    // load data
    // - create valve
    // - assign neighbors

    fun loadData(fileName: String): List<String> {
        return Resources.resourceAsListOfString(fileName)
    }

    fun createValves(rawData: List<String>): List<Valve> =
        rawData.map { row ->
            Valve(
                row.substringAfter("Valve ").substringBefore(" has "),
                row.substringAfter("rate=").substringBefore(";").toInt()
            )
        }

}

data class Valve(val name: String, val flowRate: Int)