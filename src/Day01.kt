fun main() {

    fun sumCaloriesPerElve(input: List<String>): List<Int> {
        var result = mutableListOf<Int>()
        var currentElveCalories = 0
        
        input.forEach { line ->
            if (line.isNotEmpty()) {
                currentElveCalories += line.toInt()
            } else {
                result.add(currentElveCalories)
                currentElveCalories = 0
            }
        }
        result.add(currentElveCalories)

        return result
    }

    fun part1(input: List<String>): Int {
        return sumCaloriesPerElve(input).max()
    }

    fun part2(input: List<String>): Int {
        return sumCaloriesPerElve(input).sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
