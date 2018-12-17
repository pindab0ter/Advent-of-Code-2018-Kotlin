package nl.pindab0ter.aoc2018.day7

import org.junit.Test
import kotlin.test.assertEquals

class SumOfItsPartsTest {

    private val input = listOf(
        'C' to 'A',
        'C' to 'F',
        'A' to 'B',
        'A' to 'D',
        'B' to 'E',
        'D' to 'E',
        'F' to 'E'
    )

    @Test
    fun correctOrder() {
        val actual = SumOfItsParts(input).correctOrder()
        assertEquals("CABDFE", actual)
    }

    @Test
    fun teamWork() {
        val actual = SumOfItsParts(input).teamwork(workers = 2, timePerStep = 0)
        assertEquals(15, actual)
    }
}
