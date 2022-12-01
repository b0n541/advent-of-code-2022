fun main() {
    fun part1(input: List<String>): Int {
        var result = 0

        val iterator = input.iterator()

        var currentElfCalories = 0
        while (iterator.hasNext()) {
            val next = iterator.next()

            if (next.isEmpty()) {
                if (result < currentElfCalories) {
                    result = currentElfCalories
                }
                currentElfCalories = 0
            } else {
                currentElfCalories += next.toInt()
            }
        }
        if (result < currentElfCalories) {
            result = currentElfCalories
        }

        return result
    }

    fun part2(input: List<String>): Int {

        val iterator = input.iterator()

        var elfPackages = mutableListOf<Int>()
        var currentElfCalories = 0
        while (iterator.hasNext()) {
            val next = iterator.next()

            if (next.isEmpty()) {
                elfPackages.add(currentElfCalories)
                currentElfCalories = 0
            } else {
                currentElfCalories += next.toInt()
            }
        }
        elfPackages.add(currentElfCalories)

        elfPackages.sortDescending()

        return elfPackages[0] + elfPackages[1] + elfPackages[2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
