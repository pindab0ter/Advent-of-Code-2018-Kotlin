package nl.pinbab0ter.aoc2018.day6

import kotlin.math.abs

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

            ${Grid(input).largestArea()}

            """.trimIndent()
        )
    }

data class Grid(val points: List<Point>) {
    private val x: Int = points.minBy { it.x }!!.x
    private val y: Int = points.minBy { it.y }!!.y
    private val width: Int = points.maxBy { it.x }!!.x
    private val height: Int = points.maxBy { it.y }!!.y

    fun largestArea(): Int = (x..width)
        .flatMap { x ->
            (y..height).map { y ->
                points.reduce { closest, point ->
                    if (point.distanceTo(x, y) < closest.distanceTo(x, y)) point else closest
                }
            }
        }
        .filterNot { it.x == x || it.x == width || it.y == y || it.y == width }
        .map { points.indexOf(it) }
        .groupingBy { it }
        .eachCount().values.max()!!
}


data class Point(val x: Int, val y: Int) {
    fun distanceTo(x: Int, y: Int) = abs(this.x - x) + abs(this.y - y)
}
