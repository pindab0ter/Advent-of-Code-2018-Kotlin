package nl.pindab0ter.aoc2018.day1

import nl.pindab0ter.aoc2018.day1.Change.Decrease
import nl.pindab0ter.aoc2018.day1.Change.Increase

enum class Change { Increase, Decrease }

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .lines()
    .map {
        it.asChange() to it.tail().toInt()
    }
    .fold(0) { current, (change, amount) ->
        when (change) {
            Increase -> current + amount
            Decrease -> current - amount
        }
    }
    .run(::println)

fun String.asChange(): Change = when {
    this.startsWith("+") -> Increase
    this.startsWith("-") -> Decrease
    else -> throw IllegalArgumentException()
}

fun String.tail(): String = this.substring(1)