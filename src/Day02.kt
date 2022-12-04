fun main() {

    fun parsePlayerGesture(move: String) = when (move) {
        "A", "X" -> Move.ROCK
        "B", "Y" -> Move.PAPER
        "C", "Z" -> Move.SCISSORS
        else -> error("Unknown gesture $move")
    }

    fun findMoveFollowingStrategy(strategy: String, firstPlayerMove: Move) = when (strategy) {
        "X" -> firstPlayerMove.beats()
        "Y" -> firstPlayerMove
        "Z" -> firstPlayerMove.beatenBy()
        else -> error("Unknown strategy $strategy")
    }

    fun parseMovesIndependently(line: String): Pair<Move, Move> {
        val moves = line.split(" ")
        return Pair(parsePlayerGesture(moves[0]), parsePlayerGesture(moves[1]))
    }

    fun parseMovesWithFollowingStrategy(line: String): Pair<Move, Move> {
        val moves = line.split(" ")
        val firstPlayerMove = parsePlayerGesture(moves[0])
        return Pair(firstPlayerMove, findMoveFollowingStrategy(moves[1], firstPlayerMove))
    }

    fun calculateGameOutcome(move: Pair<Move, Move>): Int {
        if (move.second.beats() == move.first) {
            return 6
        }
        if (move.first == move.second) {
            return 3
        }
        return 0
    }

    fun calculateScore(move: Pair<Move, Move>): Int {
        return move.second.score + calculateGameOutcome(move)
    }

    fun part1(input: List<String>): Int {
        return input.map { line -> parseMovesIndependently(line) }
            .sumOf { move -> calculateScore(move) }
    }

    fun part2(input: List<String>): Int {
        return input.map { line -> parseMovesWithFollowingStrategy(line) }
            .sumOf { move -> calculateScore(move) }
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
        override fun beatenBy() = PAPER
    },
    PAPER(2) {
        override fun beats() = ROCK
        override fun beatenBy() = SCISSORS
    },
    SCISSORS(3) {
        override fun beats() = PAPER
        override fun beatenBy() = ROCK
    };

    abstract fun beats(): Move
    abstract fun beatenBy(): Move
}
