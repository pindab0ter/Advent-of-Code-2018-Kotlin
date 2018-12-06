package nl.pindab0ter.aoc2018.day2

import kotlin.test.assertEquals

internal class InventoryManagementSystemTest {

    @org.junit.Test
    fun boxCount() {
        val input = listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")
        val actual = boxCount(input)
        assertEquals(12, actual)
    }

    @org.junit.Test
    fun findBoxes() {
        val input = listOf("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz")
        val actual = findBoxes(input)
        assertEquals("fgij", actual)
    }

}
