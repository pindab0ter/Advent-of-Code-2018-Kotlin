package nl.pindab0ter.aoc2018.day3

import kotlin.test.assertEquals

class NoMatterHowYouSliceItKtTest {

    private val input = listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")
        .map(::parseClaim)

    @org.junit.Test
    fun contestedClaims() {
        val actual = contestedAreas(input)
        assertEquals(4, actual)
    }

    @org.junit.Test
    fun findNonOverlappingClaim() {
        val actual = findNonOverlappingClaim(input)
        assertEquals(Claim(3, (Square(5, 5, 2, 2))), actual)
    }
}
