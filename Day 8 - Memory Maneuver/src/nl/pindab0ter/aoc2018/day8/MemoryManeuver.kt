package nl.pindab0ter.aoc2018.day8

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .split(" ")
    .map(String::toInt)
    .iterator()
    .let(Node.Factory::from)
    .let { node ->
        print("""
            --- Day 8: Memory Maneuver ---

            Part one: What is the sum of all metadata entries?

            ${node.dataSum}

            Part two: What is the value of the root node?

            ${node.value}

            """.trimIndent()
        )
    }

data class Node(val children: List<Node>, val data: List<Int>) {
    val dataSum: Int
        get() = children.sumBy { it.dataSum } + data.sum()
    val value: Int
        get() =
            if (children.isEmpty()) data.sum()
            else data.sumBy { children.getOrNull(it - 1)?.value ?: 0 }

    companion object Factory {
        fun from(input: Iterator<Int>): Node {
            val childrenSize = input.next()
            val dataSize = input.next()

            val children: List<Node> = (0 until childrenSize).map { Node.from(input) }
            val data: List<Int> = (0 until dataSize).map { input.next() }.toList()

            return Node(children, data)
        }

    }
}
