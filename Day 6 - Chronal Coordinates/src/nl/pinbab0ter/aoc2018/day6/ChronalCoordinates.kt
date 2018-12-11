package nl.pinbab0ter.aoc2018.day6

import java.util.*
import kotlin.math.abs

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .lines()
    .map { it.split(", ").map(String::toInt) }
    .map { (x, y) -> Point(x, y) }
    .let(::Grid)
    .let { grid ->
        print("""
            --- Day 6: Chronal Coordinates ---

            Part one: What is the size of the largest area that isn't infinite?

            ${grid.largestArea()}

            """.trimIndent()
        )
    }

data class Grid(val points: List<Point>) {
    private val x: Int = points.minBy { it.x }!!.x
    private val y: Int = points.minBy { it.y }!!.y
    private val width: Int = points.maxBy { it.x }!!.x
    private val height: Int = points.maxBy { it.y }!!.y

    fun largestArea(): Int = iterateOverGrid { x, y ->
        points.map { point -> point.distanceTo(x, y) to point }              // Calculate the distance for each Point
            .groupBy({ (distance, _) -> distance }, { (_, point) -> point }) // Group by distance
            .toSortedMap().first()                                           // Take the closest
            .let { if (it?.size == 1) it.first() else null }                 // Take it unless there's more than one
    }
    .filterNotNull()
    .filterNot { it.x in listOf(x, width) || it.y in listOf(y, height) }     // Remove any Points on an edge
    .groupingBy { it }                                                       // Group by Point
    .eachCount().values.max()!!                                              // Get the group with the highest count

    private fun <T> iterateOverGrid(transform: (Int, Int) -> T): List<T> = (x until width).flatMap { x: Int ->
        (y until height).map { y: Int ->
            transform(x, y)
        }
    }
}

data class Point(val x: Int, val y: Int) {
    fun distanceTo(x: Int, y: Int) = abs(this.x - x) + abs(this.y - y)
}

fun <K, V> SortedMap<K, V>.first(): V? = get(firstKey())