fun main() {
    val mt = State("mt")
    val wa = State("wa")
    val or = State("or")
    val id = State("id")
    val nv = State("nv")
    val ut = State("ut")
    val ca = State("ca")
    val az = State("az")

    val statesNeeded = mutableSetOf(mt, wa, or, id, nv, ut, ca, az)

    val stationOne = RadioStation("one")
    val stationTwo = RadioStation("two")
    val stationThree = RadioStation("three")
    val stationFour = RadioStation("four")
    val stationFive = RadioStation("five")

    val stations = mutableMapOf<RadioStation, Set<State>>()

    stations[stationOne] = setOf(id, nv, ut)
    stations[stationTwo] = setOf(wa, id, mt)
    stations[stationThree] = setOf(or, nv, ca)
    stations[stationFour] = setOf(nv, ut)
    stations[stationFive] = setOf(ca, az)

    val finalStations = mutableSetOf<RadioStation>()

    var bestStation: RadioStation?
    var statesCovered: Set<State>

    while(statesNeeded.isNotEmpty()) {
        bestStation = null
        statesCovered = setOf()
        stations.forEach { (station, statesForStation) ->
            val covered = statesNeeded intersect statesForStation
            if (covered.size > statesCovered.size) {
                bestStation = station
                statesCovered = covered
            }
        }
        statesNeeded -= statesCovered
        bestStation?.let { finalStations.add(it) }
    }

    println("Финальный набор станций для покрытия всех штатов : $finalStations")
}

/**
 * Модель штата США
 *
 * @property name Короткое название штата
 */
data class State(val name: String)

/**
 * Модель радиостанции
 *
 * @property name Название радиостанции
 */
data class RadioStation(val name: String)
