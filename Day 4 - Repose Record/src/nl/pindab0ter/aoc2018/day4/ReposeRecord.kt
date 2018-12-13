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

            Strategy one: Find the guard that has the most minutes asleep. What minute does that guard spend asleep the most?
            Guard: ${shiftLog.sleepiestGuard()}
            Hash:  ${shiftLog.sleepiestGuardHash()}

            Strategy two: Of all guards, which guard is most frequently asleep on the same minute?
            Guard: ${shiftLog.mostConsistentlySleepingGuard().first}
            Hash:  ${shiftLog.mostConsistentlySleepingGuardHash()}

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
                entry.contains("Guard")        -> log[guard!!] = log.getOrDefault(guard, mutableListOf())
                    .plus(Shift(month!!, day!!))
                    .also { currentGuard = guard }
                entry.contains("falls asleep") -> sleepingFrom = minute
                entry.contains("wakes up")     -> log[currentGuard]?.last()!!.naps
                    .add(Nap(sleepingFrom!!, minute!!))
            }
        }
    }

    fun sleepiestGuardHash(): Int = sleepiestGuard()
        .let { guard ->
            guard * timesAsleepPerMinute(log[guard]!!)
                .maxBy { (_, times) -> times }!!
                .let { (minute, _) -> minute }
        }

    fun sleepiestGuard(): Int = log
        .map { (guard: Int, shifts: List<Shift>) ->
            guard to shifts.sumBy { it.timeAsleep() }
        }
        .maxBy { (_, timeAsleep) -> timeAsleep }!!
        .let { (guard, _) -> guard }

    fun mostConsistentlySleepingGuardHash(): Int = mostConsistentlySleepingGuard()
        .let { (guard, minute) ->
            guard * minute
        }

    fun mostConsistentlySleepingGuard(): Pair<Int, Int> = log
        .map { (guard, shifts) ->
            println("$guard, $shifts")
            guard to timesAsleepPerMinute(shifts)
                .maxBy { (_, times) -> times }!! // TODO: One guard never sleeps
        }
        .maxBy { (_, record) -> record.let { (_, times) -> times } }!!
        .let { (guard, record) -> guard to record.let { (minute, _) -> minute } }

    private fun timesAsleepPerMinute(shifts: List<Shift>): Map<Int, Int> = shifts
        .flatMap { it.asleepOnMinutes() }
        .groupingBy { it }
        .eachCount()

    data class Shift(val month: Int, val day: Int, val naps: MutableList<Nap> = mutableListOf()) {
        fun timeAsleep(): Int = naps.sumBy { it.length }
        fun asleepOnMinutes(): List<Int> = naps.flatMap { it.asRange() }

        data class Nap(val from: Int, val till: Int) {
            val length: Int get() = till - from
            fun asRange(): IntRange = from..till
        }
    }
}


