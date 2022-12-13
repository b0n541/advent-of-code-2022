fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
    val input = readInput("Day12")

    val resultTest1 = part1(testInput)
    println("Test 1: $resultTest1")
    check(resultTest1 == 31)

    val resultPart1 = part1(input)
    println("Part 1: $resultPart1")
//    check(resultPart1 == 1789)
//
//    val resultTest2 = part2(testInput)
//    println("Test 2: $resultTest2")
//    check(resultTest2 == 8)
//
//    val resultPart2 = part2(input)
//    println("Part 2: $resultPart2")
//    check(resultPart2 == 859)
}
