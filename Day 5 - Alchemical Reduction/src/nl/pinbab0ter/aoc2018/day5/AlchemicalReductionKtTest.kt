package nl.pinbab0ter.aoc2018.day5

import org.junit.Assert.assertEquals
import org.junit.Test

class AlchemicalReductionKtTest {

    private val input = "dabAcCaCBAcCcaDA"

    @Test
    fun reducePairs() {
        val actual = reduce(input)
        println(input)
        assertEquals("dabCBAcaDA", actual)
    }
}
