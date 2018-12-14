package nl.pindab0ter.aoc2018.day7

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .lines()
    .let { input ->
        print("""
            --- Day 7: Sum of Its Parts ---

            Part one: In what order should the steps in your instructions be completed?

            $input

            """.trimIndent()
        )
    }

fun partOne(input: List<Pair<Char, Char>>): String = TODO()