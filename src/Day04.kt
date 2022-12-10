fun main() {

    fun String.toPair(): Pair<Int, Int> {
        val numbers = this.split("-")
        return Pair(numbers[0].toInt(), numbers[1].toInt())
    }

    fun IntRange.fullyContains(other: IntRange): Boolean {
        for (i in other) {
            if (i !in this) {
                return false
            }
        }
        return true
    }

    fun IntRange.overlaps(other: IntRange): Boolean {
        for (i in other) {
            if (i in this) {
                return true
            }
        }
        return false
    }

    fun toIntRange(it: String) = it.split(",")
        .map { it.toPair() }
        .map { it.first..it.second }

    fun part1(input: List<String>): Int {
        return input
            .map { toIntRange(it) }
            .count { it[0].fullyContains(it[1]) || it[1].fullyContains(it[0]) }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { toIntRange(it) }
            .count { it[0].overlaps(it[1]) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    val input = readInput("Day04")

    val resultTest1 = part1(testInput)
    println("Test 1: $resultTest1")
    check(resultTest1 == 2)

    val resultPart1 = part1(input)
    println("Part 1: $resultPart1")
    check(resultPart1 == 498)

    val resultTest2 = part2(testInput)
    println("Test 2: $resultTest2")
    check(resultTest2 == 4)

    val resultPart2 = part2(input)
    println("Part 2: $resultPart2")
    check(resultPart2 == 859)
}
