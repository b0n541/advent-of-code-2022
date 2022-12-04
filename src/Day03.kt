fun main() {

    fun part1(input: List<String>): Int {
        return input.map { line ->
            listOf(
                line.substring(0, line.length / 2).toCharArray().toSet(),
                line.substring(line.length / 2).toCharArray().toSet()
            )
        }.map { subStrings -> subStrings[0].intersect(subStrings[1]) }
            .map { duplicatedChars -> duplicatedChars.first() }
            .map { char -> char.code }
            .sumOf { code ->
                when (code) {
                    in 97..122 -> code - 96
                    in 65..90 -> code - 64 + 26
                    else -> error("Unknown code $code")
                }
            }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3)
            .map { chunk ->
                listOf(
                    chunk[0].toCharArray().toSet(),
                    chunk[1].toCharArray().toSet(),
                    chunk[2].toCharArray().toSet(),
                )
            }.map { chunkSets -> chunkSets[0].intersect(chunkSets[1]).intersect(chunkSets[2]) }
            .map { duplicatedChars -> duplicatedChars.first() }
            .map { char -> char.code }
            .sumOf { code ->
                when (code) {
                    in 97..122 -> code - 96
                    in 65..90 -> code - 64 + 26
                    else -> error("Unknown code $code")
                }
            }
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
