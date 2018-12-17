package nl.pindab0ter.aoc2018.day8

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .split(" ")
    .map(String::toInt)
    .let { input ->
        print("""
            --- Day 8: Memory Maneuver ---

            Part one: What is the sum of all metadata entries?

            $input

            """.trimIndent()
        )
    }

data class Node(val children: List<Node>, val metadata: List<Int>) {
    val size: Int get() = 2 + children.sumBy { it.size } + metadata.size
    val metaDataSum: Int get() = children.sumBy { it.metaDataSum } + metadata.sum()
}

fun parse(input: List<Int>): Node {
    val amountOfChildren = input[0]
    val amountOfMetadata = input[1]
    val remainder = input.drop(2)

    val children: List<Node> =
        if (amountOfChildren == 0) emptyList()
        else (0 until amountOfChildren).fold(emptyList()) { children: List<Node>, _: Int ->
            val thisRemainder = remainder.drop(children.sumBy { it.size })
            children + parse(thisRemainder)
        }

    val metadata: List<Int> = remainder
        .drop(children.sumBy { it.size })
        .take(amountOfMetadata)

    return Node(children, metadata)
}
