package nl.pindab0ter.aoc2018.day2

fun main(args: Array<String>) {
    val boxes = ClassLoader
        .getSystemResource("input")
        .readText(Charsets.UTF_8)
        .lines()

    println(boxes.appearancesFor(2) * boxes.appearancesFor(3))
}

fun List<String>.appearancesFor(n: Int): Int = this
    .filter { it.hasCharsAppearing(n) }
    .count()

fun String.hasCharsAppearing(n: Int) = fold(false) { acc, c ->
    this.count { it == c } == n || acc
}