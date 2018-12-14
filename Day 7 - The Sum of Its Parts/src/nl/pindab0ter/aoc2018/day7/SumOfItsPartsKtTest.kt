package nl.pindab0ter.aoc2018.day7

import kotlin.test.assertEquals

class SumOfItsPartsKtTest {

    private val input = listOf(
        'C' to 'A',
        'C' to 'F',
        'A' to 'B',
        'A' to 'D',
        'B' to 'E',
        'D' to 'E',
        'F' to 'E'
    )

    @org.junit.Test
    fun correctOrder() {
        val actual = correctOrder(input)
        assertEquals("CABDFE", actual)
    }
}
