package nl.pinbab0ter.aoc2018.day6

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .lines()
    .map { it.split(", ").map(String::toInt) }
    .map { (x, y) -> Point(x, y) }
    .let { input ->
        print("""
            --- Day 6: Chronal Coordinates ---

            Part one: What is the size of the largest area that isn't infinite?

            $input

            """.trimIndent()
        )
    }

data class Point(val x: Int, val y: Int)

fun largestArea(input: List<Point>): Int = 0