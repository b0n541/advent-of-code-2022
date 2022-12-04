fun main() {

    fun parseFirstPlayer(move: String) = when (move) {
        "A" -> Move.ROCK
        "B" -> Move.PAPER
        "C" -> Move.SCISSORS
        else -> null
    }

    fun parseSecondPlayerIndependentMove(move: String) = when (move) {
        "X" -> Move.ROCK
        "Y" -> Move.PAPER
        "Z" -> Move.SCISSORS
        else -> null
    }

    fun parseSecondPlayerFollowingStrategy(strategy: String, move: Move) = when (strategy) {
        "X" -> move.beats()
        "Y" -> move
        "Z" -> move.defeats()
        else -> null
    }

    fun parseMovesIndependently(line: String): Pair<Move?, Move?> {
        val moves = line.split(" ")
        return Pair(parseFirstPlayer(moves[0]), parseSecondPlayerIndependentMove(moves[1]))
    }

    fun parseMovesWithFollowingStrategy(line: String): Pair<Move?, Move?> {
        val moves = line.split(" ")
        val firstPlayerMove = parseFirstPlayer(moves[0])
        return Pair(firstPlayerMove, parseSecondPlayerFollowingStrategy(moves[1], firstPlayerMove!!))
    }

    fun calculateGameOutcome(move: Pair<Move?, Move?>): Int {
        if (move.second!!.beats() == move.first!!) {
            return 6
        }
        if (move.first!! == move.second!!) {
            return 3
        }
        return 0
    }

    fun calculateScore(move: Pair<Move?, Move?>): Int {
        return move.second!!.score + calculateGameOutcome(move)
    }

    fun part1(input: List<String>): Int {
        return input.map { line -> parseMovesIndependently(line) }
            .map { move -> calculateScore(move) }
            .sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { line -> parseMovesWithFollowingStrategy(line) }
            .map { move -> calculateScore(move) }
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val resultTest1 = part1(testInput)
    println("Test 1: $resultTest1")
    check(resultTest1 == 15)
    val resultTest2 = part2(testInput)
    println("Test 2: $resultTest2")
    check(resultTest2 == 12)

    val input = readInput("Day02")
    val resultPart1 = part1(input)
    println("Part 1: $resultPart1")
    check(resultPart1 == 14297)
    val resultPart2 = part2(input)
    println("Part 2: $resultPart2")
    //check(resultPart2 == 14297)
}

enum class Move(val score: Int) {
    ROCK(1) {
        override fun beats() = SCISSORS
        override fun defeats() = PAPER
    },
    PAPER(2) {
        override fun beats() = ROCK
        override fun defeats() = SCISSORS
    },
    SCISSORS(3) {
        override fun beats() = PAPER
        override fun defeats() = ROCK
    };

    abstract fun beats(): Move
    abstract fun defeats(): Move
}
