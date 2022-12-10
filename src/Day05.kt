import java.util.*

fun main() {

    fun String.parseLine(stacks: Int): List<Char> {
        var result = ArrayList<Char>()

        for (i in 0 until stacks) {
            result.add(this[4 * i + 1])
        }

        return result
    }

    fun parseInitialStacks(input: List<String>): Map<Int, Deque<Char>> {
        val result = mutableMapOf<Int, Deque<Char>>()

        val stackCount = input.last().replace(" ", "").last().toString().toInt()
        for (i in 1..stackCount) {
            result[i] = ArrayDeque()
        }

        for (row in input.size - 2 downTo 0) {
            val cratesInRow = input[row].parseLine(stackCount)
            for (i in 0 until stackCount) {
                if (cratesInRow[i] != ' ') {
                    result[i + 1]?.push(cratesInRow[i])
                }
            }
        }

        return result
    }

    fun parseMoves(input: List<String>): List<CrateMove> {
        return input.map { it.split(" ") }
            .map { CrateMove(it[1].toInt(), it[3].toInt(), it[5].toInt()) }
    }

    fun parseInput(input: List<String>): Pair<Map<Int, Deque<Char>>, List<CrateMove>> {
        val emptyLineIndex = input.indexOf("")

        return Pair(
            parseInitialStacks(input.subList(0, emptyLineIndex)),
            parseMoves(input.subList(emptyLineIndex + 1, input.size))
        )
    }

    fun pickUpCrates(move: CrateMove, stacks: Map<Int, Deque<Char>>): MutableList<Char> {
        var cratesToMove = mutableListOf<Char>()
        repeat(move.crateCount) {
            cratesToMove.add(stacks[move.fromStack]!!.poll())
        }
        return cratesToMove
    }

    fun moveCrates(stacks: Map<Int, Deque<Char>>, moves: List<CrateMove>, reverseOrder: Boolean = false) {
        moves.forEach { move ->
            run {
                var cratesToMove = pickUpCrates(move, stacks)
                if (reverseOrder) {
                    cratesToMove.reverse()
                }
                for (crate in cratesToMove) {
                    stacks[move.toStack]!!.push(crate)
                }
            }
        }
    }

    fun moveCratesOneByOne(stacks: Map<Int, Deque<Char>>, moves: List<CrateMove>) {
        moveCrates(stacks, moves)
    }

    fun moveCratesAtOnce(stacks: Map<Int, Deque<Char>>, moves: List<CrateMove>) {
        moveCrates(stacks, moves, true)
    }

    fun part1(input: List<String>): String {

        val parsedInput = parseInput(input)

        val stacks = parsedInput.first
        val moves = parsedInput.second

        moveCratesOneByOne(stacks, moves)

        return String(stacks.map { it.value.peek() }.toCharArray())
    }

    fun part2(input: List<String>): String {
        val parsedInput = parseInput(input)

        val stacks = parsedInput.first
        val moves = parsedInput.second

        moveCratesAtOnce(stacks, moves)

        return String(stacks.map { it.value.peek() }.toCharArray())
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    val input = readInput("Day05")

    val resultTest1 = part1(testInput)
    println("Test 1: $resultTest1")
    check(resultTest1 == "CMZ")

    val resultPart1 = part1(input)
    println("Part 1: $resultPart1")
    check(resultPart1 == "QNHWJVJZW")

    val resultTest2 = part2(testInput)
    println("Test 2: $resultTest2")
    check(resultTest2 == "MCD")

    val resultPart2 = part2(input)
    println("Part 2: $resultPart2")
    check(resultPart2 == "BPCZJLFJW")
}

data class CrateMove(val crateCount: Int, val fromStack: Int, val toStack: Int)