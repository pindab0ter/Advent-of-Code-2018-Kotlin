package nl.pindab0ter.aoc2018.day3

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .lines()
    .map(::parseClaim)
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

data class Claim(
    val id: Int,
    val square: Square
)

data class Square(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int) {
    val occupies: List<Pair<Int, Int>>
        get() = (0 + x until width + x).flatMap { w ->
            (0 + y until height + y).map { h ->
                Pair(w, h)
            }
        }
}

fun contestedAreas(claims: List<Claim>): Int = claims
    .flatMap { it.square.occupies }
    .groupingBy { it }
    .eachCount()
    .count { it.value > 1 }

fun findNonOverlappingClaim(claims: List<Claim>): Claim = claims.first { claim ->
    claims.minus(claim).none { it.overlapsWith(claim) }
}

fun Claim.overlapsWith(other: Claim) = square.occupies.any { other.square.occupies.contains(it) }

fun parseClaim(input: String): Claim = Regex("""^#(\d+) @ (\d+),(\d+): (\d+)x(\d+)$""")
    .find(input)
    .let { it ?: throw IllegalArgumentException("Cannot parse $input to Claim") }
    .destructured
    .toList()
    .map(String::toInt)
    .let { (id, x, y, width, height) ->
        Claim(id, Square(x, y, width, height))
    }
