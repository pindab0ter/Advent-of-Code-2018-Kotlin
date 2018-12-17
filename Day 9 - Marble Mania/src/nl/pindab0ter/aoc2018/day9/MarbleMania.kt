package nl.pindab0ter.aoc2018.day9

import com.ginsberg.cirkle.circular

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .let { input ->
        print("""
            --- Day 9: Marble Mania ---

            Part one: What is the winning Elf's score?

            $input

            """.trimIndent()
        )
    }

class MarbleMania(players: Int, val lastMarble: Int) {
    private val marbles = mutableListOf(0).circular()
    private val players = (1..players).zip(List(players) { 0 })

    fun highScore(): Int {
        var i = 0
        var currentMarble = 0

        while (i < 10) {
            marbles.add(i)
            currentMarble = i
            i++
        }

        return 0
    }

    companion object Factory {
        fun fromString(input: String): MarbleMania = Regex("""(\d+).*(\d+)""")
            .find(input)!!
            .destructured
            .toList()
            .map(String::toInt)
            .let { (players, lastMarble) ->
                MarbleMania(players, lastMarble)
            }
    }
}
