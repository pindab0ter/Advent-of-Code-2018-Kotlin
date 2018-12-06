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

fun contestedAreas(claims: List<Claim>): Int = claims
    .flatMap { it.area.occupies }
    .groupingBy { it }
    .eachCount()
    .count { it.value > 1 }

fun findNonOverlappingClaim(claims: List<Claim>): Claim = claims.first { claim ->
    claims.minus(claim).none { it.overlapsWith(claim) }
}
