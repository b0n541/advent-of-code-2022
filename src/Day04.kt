fun main() {

    fun stringsToCharSets(strings: List<String>) = strings.map { it.toSet() }

    fun intersectAll(charSets: List<Set<Char>>) = charSets.reduce { first, second -> first intersect second }.first()

    fun priority(char: Char) = when (char.code) {
        in 97..122 -> char.code - 96 // lower case characters
        in 65..90 -> char.code - 64 + 26 // upper case characters
        else -> error("Unknown code $char.code")
    }

    fun part1(input: List<String>): Int {
        return input.map {
            stringsToCharSets(
                listOf(
                    it.substring(0, it.length / 2),
                    it.substring(it.length / 2)
                )
            )
        }
            .map { intersectAll(it) }
            .sumOf { priority(it) }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3)
            .map { stringsToCharSets(it) }
            .map { intersectAll(it) }
            .sumOf { priority(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val input = readInput("Day03")

    val resultTest1 = part1(testInput)
    println("Test 1: $resultTest1")
    check(resultTest1 == 157)

    val resultPart1 = part1(input)
    println("Part 1: $resultPart1")
    check(resultPart1 == 7831)

    val resultTest2 = part2(testInput)
    println("Test 2: $resultTest2")
    check(resultTest2 == 70)

    val resultPart2 = part2(input)
    println("Part 2: $resultPart2")
    check(resultPart2 == 2683)
}
