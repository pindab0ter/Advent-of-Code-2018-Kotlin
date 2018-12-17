package nl.pindab0ter.aoc2018.day5

import org.junit.Assert.assertEquals
import org.junit.Test

class AlchemicalReductionTest {

    private val input = "dabAcCaCBAcCcaDA"

    @Test
    fun reduce() {
        val actual = reduce(input)
        assertEquals("dabCBAcaDA", actual)
    }

    @Test
    fun shortestPolymer() {
        val actual = shortestPolymer(input)
        assertEquals(4, actual)
    }

}
