package nl.pindab0ter.aoc2018.day3

data class Claim(val id: Int, val area: Area) {

    data class Area(val x: Int, val y: Int, val w: Int, val h: Int) {
        val occupies: List<Pair<Int, Int>>
            get() = (0 + x until w + x).flatMap { w ->
                (0 + y until h + y).map { h ->
                    Pair(w, h)
                }
            }
    }

    fun overlapsWith(other: Claim) = area.occupies.any { other.area.occupies.contains(it) }

    companion object Factory {
        fun fromString(input: String): Claim = Regex("""^#(\d+) @ (\d+),(\d+): (\d+)x(\d+)$""")
            .find(input)
            .let { it ?: throw IllegalArgumentException("Cannot parse $input to Claim") }
            .destructured
            .toList()
            .map(String::toInt)
            .let { (id, x, y, w, h) ->
                Claim(id, Area(x, y, w, h))
            }
    }
}
