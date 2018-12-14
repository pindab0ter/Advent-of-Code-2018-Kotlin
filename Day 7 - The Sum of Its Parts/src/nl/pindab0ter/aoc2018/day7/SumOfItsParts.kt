package nl.pindab0ter.aoc2018.day7

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .lines()
    .map { it[5] to it[36] }
    .let { input ->
        print("""
            --- Day 7: Sum of Its Parts ---

            Part one: In what order should the steps in your instructions be completed?

            ${correctOrder(input)}

            """.trimIndent()
        )
    }

// Credit to this solution to u/nutrecht
// https://github.com/nielsutrecht/adventofcode/blob/master/src/main/kotlin/com/nibado/projects/advent/y2018/Day07.kt
// Comments by me
fun correctOrder(input: List<Pair<Char, Char>>): String {
    val result = mutableListOf<Char>()
    val characters = input.flatMap(Pair<Char, Char>::toList).toSortedSet()
    val prerequisites = input                        // Step X must be finished before step Y can begin.
        .groupBy { (_, step) -> step }               // Group prerequisites by step
        .mapValues { (_, prerequisite) ->
            prerequisite.map { it.first }.distinct() // Remove the step from the list of it's prerequisites
        }

    while (result.size < characters.size) {
        result += characters
            .filterNot { result.contains(it) }       // Exclude taken steps
            .first { step ->
                //  Step has no prerequisites    OR                     All prerequisites have been met
                !prerequisites.containsKey(step) || prerequisites[step]!!.all { prerequisite -> result.contains(prerequisite) }
            }
    }

    return result.joinToString("")
}
