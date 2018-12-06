package nl.pindab0ter.aoc2018.day4

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .lines()
    .let { input ->
        print("""
            --- Day 4: Repose Record ---

            Part one: What is the ID of the guard you chose multiplied by the minute you chose?
            ${partOne(input)}

            """.trimIndent()
        )
    }

fun partOne(input: List<String>): Int = 0