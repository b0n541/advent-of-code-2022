fun main() {

    fun String.findMarker(length: Int) = this.indexOf(
        this.windowed(length, 1)
            .first { substring -> substring.toSet().size == length }) + length

    fun part1(input: List<String>): List<Int> {
        return input.map { it.findMarker(4) }
    }

    fun part2(input: List<String>): List<Int> {
        return input.map { it.findMarker(14) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    val input = readInput("Day06")

    val resultTest1 = part1(testInput)
    println("Test 1: $resultTest1")
    check(resultTest1 == listOf(7, 5, 6, 10, 11))

    val resultPart1 = part1(input)
    println("Part 1: $resultPart1")
    check(resultPart1 == listOf(1134))

    val resultTest2 = part2(testInput)
    println("Test 2: $resultTest2")
    check(resultTest2 == listOf(19, 23, 23, 29, 26))

    val resultPart2 = part2(input)
    println("Part 2: $resultPart2")
    check(resultPart2 == listOf(2263))
}
