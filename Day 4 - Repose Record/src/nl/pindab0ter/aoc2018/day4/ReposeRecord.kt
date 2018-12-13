package nl.pindab0ter.aoc2018.day4

import nl.pindab0ter.aoc2018.day4.ReposeRecord.Shift.Nap

fun main(args: Array<String>) = ClassLoader
    .getSystemResource("input")
    .readText(Charsets.UTF_8)
    .lines()
    .sorted()
    .let(::ReposeRecord)
    .let { shiftLog ->
        print("""
            --- Day 4: Repose Record ---

            Part one: What is the ID of the guard you chose multiplied by the minute you chose?
            ${shiftLog.safestMomentHash()}

            """.trimIndent()
        )
    }

class ReposeRecord(entries: List<String>) {
    private val log: MutableMap<Int, List<Shift>> = mutableMapOf()

    init {
        var currentGuard: Int? = null
        var sleepingFrom: Int? = null
        entries.forEach { entry ->
            val (month, day, minute, guard) = Regex("""^\[\d+-(\d+)-(\d+) \d+:(\d+)\D*?(?:(\d+)|${'$'})""")
                .find(entry).let { it ?: throw IllegalArgumentException("Cannot parse input \"$entry\"") }
                .destructured.toList()
                .map(String::toIntOrNull)

            when {
                entry.contains("Guard") -> log[guard!!] = log.getOrDefault(guard, mutableListOf())
                    .plus(Shift(month!!, day!!))
                    .also { currentGuard = guard }
                entry.contains("falls asleep") -> sleepingFrom = minute
                entry.contains("wakes up") -> log[currentGuard]?.last()!!.naps
                    .add(Nap(sleepingFrom!!, minute!!))
            }
        }
    }

    fun safestMomentHash(): Int = sleepiestGuard().let { guard -> guard * safestMomentFor(guard) }

    private fun safestMomentFor(guard: Int): Int = log[guard]!!
        .flatMap { it.momentsAsleep() }
        .groupingBy { it }
        .eachCount()
        .maxBy { it.value }!!
        .key

    private fun sleepiestGuard(): Int = log
        .map { (guard: Int, shifts: List<Shift>) ->
            guard to shifts.sumBy { it.timeAsleep() }
        }
        .maxBy { (_, timeAsleep) -> timeAsleep }!!
        .first

    data class Shift(val month: Int, val day: Int, val naps: MutableList<Nap> = mutableListOf()) {
        fun timeAsleep(): Int = naps.sumBy { it.length }
        fun momentsAsleep(): List<Int> = naps.flatMap { it.asRange() }

        data class Nap(val from: Int, val till: Int) {
            val length: Int get() = till - from
            fun asRange(): IntRange = from..till
        }
    }
}


