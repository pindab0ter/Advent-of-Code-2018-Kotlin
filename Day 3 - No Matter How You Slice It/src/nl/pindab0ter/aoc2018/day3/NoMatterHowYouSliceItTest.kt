package nl.pindab0ter.aoc2018.day3

import org.junit.Assert.assertEquals
import org.junit.Test

class NoMatterHowYouSliceItTest {

    private val input = listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")
        .map(Claim.Factory::fromString)

    @Test
    fun contestedClaims() {
        val actual = contestedAreas(input)
        assertEquals(4, actual)
    }

    @Test
    fun findNonOverlappingClaim() {
        val actual = findNonOverlappingClaim(input)
        assertEquals(Claim(3, Claim.Area(5, 5, 2, 2)), actual)
    }
}
