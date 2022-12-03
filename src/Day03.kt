fun main() {

    fun part1(input: List<String>): Int {
        return input.size
    }

//    fun part2(input: List<String>): Int {
//        return input.size
//    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
//    check(part2(testInput) == 45000)

    val input = readInput("Day02")
    println("Part 1: " + part1(input))
//    check(part1(input) == 1)
//    println("Part 2: " + part2(input))
//    check(part2(input) == 1)
}
