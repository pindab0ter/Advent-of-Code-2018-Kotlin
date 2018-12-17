package nl.pindab0ter.aoc2018.day8

import org.junit.Test
import kotlin.test.assertEquals

class MemoryManeuverTest {

    private val input = listOf(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2)

    @Test
    fun dataSum() {
        val actual = Node.fromIntList(input).dataSum
        assertEquals(138, actual)
    }

    @Test
    fun value() {
        val actual = Node.fromIntList(input).value
        assertEquals(66, actual)
    }

}
