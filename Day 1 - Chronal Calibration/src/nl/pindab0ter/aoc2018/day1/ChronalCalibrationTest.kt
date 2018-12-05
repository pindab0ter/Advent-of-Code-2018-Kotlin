package nl.pindab0ter.aoc2018.day1

import kotlin.test.assertEquals

internal class ChronalCalibrationTest {

    @org.junit.Test
    fun findFinalFrequency() = listOf(
        3 to findFinalFrequency(listOf(1, -2, 3, 1)),
        3 to findFinalFrequency(listOf(1, 1, 1)),
        0 to findFinalFrequency(listOf(1, 1, -2)),
        -6 to findFinalFrequency(listOf(-1, -2, -3))
    ).forEach { (expected, actual) ->
        assertEquals(expected, actual)
    }

    @org.junit.Test
    fun findFirstRepeatedFrequency() = listOf(
        (0) to findFirstRepeatedFrequency(listOf(1, -1)),
        (10) to findFirstRepeatedFrequency(listOf(3, 3, 4, -2, -4)),
        (5) to findFirstRepeatedFrequency(listOf(-6, 3, 8, 5, -6)),
        (14) to findFirstRepeatedFrequency(listOf(7, 7, -2, -7, -4))
    ).forEach { (expected, actual) ->
        assertEquals(expected, actual)
    }
}