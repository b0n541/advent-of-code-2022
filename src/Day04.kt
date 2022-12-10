fun main() {

    fun IntRange.fullyContains(other: IntRange): Boolean {
        for (value in other) {
            if (value !in this) {
                return false
            }
        }
        return true
    }

    fun IntRange.overlaps(other: IntRange): Boolean {
        for (value in other) {
            if (value in this) {
                return true
            }
        }
        return false
    }

    fun String.toIntRanges() = this.split(",")
        .map { numberStrings ->
            numberStrings
                .split("-")
                .map { it.toInt() }
        }
        .map { numbers -> numbers[0]..numbers[1] }

    fun part1(input: List<String>): Int {
        return input
            .map { it.toIntRanges() }
            .count { it[0].fullyContains(it[1]) || it[1].fullyContains(it[0]) }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.toIntRanges() }
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
