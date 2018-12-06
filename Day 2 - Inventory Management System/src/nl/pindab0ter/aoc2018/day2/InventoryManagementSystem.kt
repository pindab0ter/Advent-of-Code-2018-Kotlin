package nl.pindab0ter.aoc2018.day2

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .lines()
    .let { input ->
        print("""
            Day 2: Inventory Management System

            Part one: What is the checksum for your list of box IDs?
            ${boxCount(input)}
            What letters are common between the two correct box IDs?
            ${findBoxes(input)}
            """.trimIndent()
        )
    }

fun boxCount(boxes: List<String>): Int {
    return count(boxes, 2) * count(boxes, 3)
}

fun count(boxes: List<String>, duplicates: Int) = boxes
    .filter { box ->
        box.fold(false) { acc, c ->
            box.count { it == c } == duplicates || acc
        }
    }.count()

fun findBoxes(boxes: List<String>): String = boxes
    .flatMap { i ->
        boxes.mapNotNull { j ->
            if (i.isNeighbourOf(j)) i.intersect(j)
            else null
        }
    }
    .first(String::isNotBlank)

fun String.isNeighbourOf(other: String) = intersect(other).length == length - 1

fun String.intersect(other: String): String = this
    .toCharArray()
    .intersect(other.toCharArray().asIterable())
    .joinToString("")
