package nl.pindab0ter.aoc2018.day4

private val input = listOf(
    "[1518-11-01 00:00] Guard #10 begins shift",
    "[1518-11-01 00:05] falls asleep",
    "[1518-11-01 00:25] wakes up",
    "[1518-11-01 00:30] falls asleep",
    "[1518-11-01 00:55] wakes up",
    "[1518-11-01 23:58] Guard #99 begins shift",
    "[1518-11-02 00:40] falls asleep",
    "[1518-11-02 00:50] wakes up",
    "[1518-11-03 00:05] Guard #10 begins shift",
    "[1518-11-03 00:24] falls asleep",
    "[1518-11-03 00:29] wakes up",
    "[1518-11-04 00:02] Guard #99 begins shift",
    "[1518-11-04 00:36] falls asleep",
    "[1518-11-04 00:46] wakes up",
    "[1518-11-05 00:03] Guard #99 begins shift",
    "[1518-11-05 00:45] falls asleep",
    "[1518-11-05 00:55] wakes up"
)

/* TODO: Loop through list:
         - Create new Guard
         - Create new Shift for that guard
         - Add Events to Shift
         - If line contains 'guard' add current Guard to list and start over
*/

//fun main(args: Array<String>) = ClassLoader
//    .getSystemResource("input")
//    .readText(Charsets.UTF_8)
//    .lines()
fun main(args: Array<String>) = input
    .sorted()
    .let(::parseLog)
    .let { input ->
        print("""
            --- Day 4: Repose Record ---

            Part one: What is the ID of the guard you chose multiplied by the minute you chose?
            ${input[0].shifts[0].minutesAsleep()}

            """.trimIndent()
        )
    }

fun parseLog(input: List<String>): List<Guard> {
    val guards = mutableListOf<Guard>()

    input.forEach { entry: String ->
        val (month, day, minute, id) = Regex("""^\[\d+-(\d+)-(\d+) \d+:(\d+)\D*?(?:(\d{2})|${'$'})""")
            .find(entry)
            .let { it ?: throw IllegalArgumentException("Cannot parse input \"$input\"") }
            .destructured
            .toList()
            .map(String::toIntOrNull)

        if (id != null) {
            val guard: Guard = if (guards.find { it.id == id } != null) guards
                .find { it.id == id }!!
                .also { guards.remove(it) }
            else Guard(id)
            guard.shifts.add(Shift(month!!, day!!))
            guards.add(guard)
        } else {
            guards.last().shifts.last().events.add(
                Event(minute!!, Event.Type.fromString(entry))
            )
        }
    }

    return guards
}

data class Guard(val id: Int, val shifts: MutableList<Shift> = mutableListOf())

data class Shift(val month: Int, val day: Int, val events: MutableList<Event> = mutableListOf()) {
    fun minutesAsleep(): Int = (events.filter { it.type == Event.Type.FallAsleep }.zip(events.filter { it.type == Event.Type.WakeUp })).map { (fallAsleep, wakeUp) ->
        wakeUp.minute - fallAsleep.minute
    }.sum()


}

data class Event(val minute: Int, val type: Type) {
    enum class Type { WakeUp, FallAsleep, BeginShift;

        companion object Factory {
            fun fromString(input: String): Type = when {
                input.contains("wakes up") -> WakeUp
                input.contains("falls asleep") -> FallAsleep
                else -> BeginShift
            }
        }
    }
}

fun partOne(input: List<String>): Int = 0
