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

            ${Node.fromIntList(input).dataSum}

            Part two: What is the value of the root node?

            ${Node.fromIntList(input).value}

            """.trimIndent()
        )
    }

data class Node(val children: List<Node>, val data: List<Int>) {
    val size: Int get() = 2 + children.sumBy { it.size } + data.size
    val dataSum: Int get() = children.sumBy { it.dataSum } + data.sum()
    val value: Int
        get() =
            if (children.isEmpty()) data.sum()
            else data.sumBy { children.getOrNull(it - 1)?.value ?: 0 }

    companion object Factory {
        fun fromIntList(input: List<Int>): Node {
            val childrenSize = input[0]
            val dataSize = input[1]
            val remainder = input.drop(2)

            val children: List<Node> = (0 until childrenSize)
                .fold(emptyList()) { nodes: List<Node>, _: Int ->
                    nodes + fromIntList(remainder.drop(nodes.combinedSize))
                }

            val data: List<Int> = remainder
                .drop(children.combinedSize)
                .take(dataSize)

            return Node(children, data)
        }

    }
}

val List<Node>.combinedSize: Int get() = sumBy { it.size }