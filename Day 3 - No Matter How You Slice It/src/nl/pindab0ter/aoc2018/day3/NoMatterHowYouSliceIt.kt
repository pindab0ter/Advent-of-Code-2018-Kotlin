package nl.pindab0ter.aoc2018.day3

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .lines()
    .map(Claim.Factory::fromString)
    .let { input ->
        print("""
            --- Day 3: No Matter How You Slice It ---

            Part one: How many square inches of fabric are within two or more claims?
            ${contestedAreas(input)}

            Part two: What is the ID of the only claim that doesn't overlap?
            ${findNonOverlappingClaim(input)}
            """.trimIndent()
        )
    }

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

fun contestedAreas(claims: List<Claim>): Int = claims
    .flatMap { it.area.occupies }
    .groupingBy { it }
    .eachCount()
    .count { it.value > 1 }

fun findNonOverlappingClaim(claims: List<Claim>): Claim = claims.first { claim ->
    claims.minus(claim).none { it.overlapsWith(claim) }
}
