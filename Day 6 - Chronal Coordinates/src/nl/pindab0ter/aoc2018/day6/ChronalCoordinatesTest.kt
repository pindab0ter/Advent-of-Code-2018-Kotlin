package nl.pindab0ter.aoc2018.day6

import org.junit.Assert.assertEquals
import org.junit.Test

class ChronalCoordinatesTest {

    private val input = listOf(
        Point(1, 1),
        Point(1, 6),
        Point(8, 3),
        Point(3, 4),
        Point(5, 5),
        Point(8, 9)
    )

    @Test
    fun largestArea() {
        val actual = Grid(input).largestArea()
        assertEquals(17, actual)
    }

    @Test
    fun sizeOfSafestPointWithin() {
        val actual = Grid(input).sizeOfSafestPointWithin(32)
        assertEquals(16, actual)
    }

}
