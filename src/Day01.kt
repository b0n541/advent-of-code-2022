fun main() {

    fun sumCaloriesPerElf(input: List<String>): List<Int> {
        var result = mutableListOf<Int>()
        var currentElfCalories = 0

        input.forEach { line ->
            if (line.isNotEmpty()) {
                currentElfCalories += line.toInt()
            } else {
                result.add(currentElfCalories)
                currentElfCalories = 0
            }
        }
        result.add(currentElfCalories)

        return result
    }

    fun part1(input: List<String>): Int {
        return sumCaloriesPerElf(input).max()
    }

    fun part2(input: List<String>): Int {
        return sumCaloriesPerElf(input).sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println("Part 1: " + part1(input))
    check(part1(input) == 71502)
    println("Part 2: " + part2(input))
    check(part2(input) == 208191)
}
