package nl.pinbab0ter.aoc2018.day6

import org.junit.Assert.assertEquals
import org.junit.Test

class ChronalCoordinatesKtTest {

    private val input = listOf(
        Point(1, 1),
        Point(1, 6),
        Point(8, 3),
        Point(3, 4),
        Point(5, 5),
        Point(8, 9)
    )

    @Test
    fun reduce() {
        val actual = Grid(input).largestArea()
        assertEquals(17, actual)
    }

}
