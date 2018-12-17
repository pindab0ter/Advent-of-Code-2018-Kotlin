package nl.pindab0ter.aoc2018.day8

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .split(" ")
    .let { input ->
        print("""
            --- Day 8: Memory Maneuver ---

            Part one: What is the sum of all metadata entries?

            $input

            """.trimIndent()
        )
    }

fun sumOfEntries(input: List<String>): Int = TODO()