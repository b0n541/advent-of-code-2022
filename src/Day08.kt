fun main() {

    fun allTreesToTheLeftAreShorter(trees: Array<IntArray>, row: Int, column: Int): Boolean {
        val treeHeight = trees[row][column]

        for (i in column - 1 downTo 0) {
            if (trees[row][i] >= treeHeight) {
                return false
            }
        }

        return true
    }

    fun allTreesToTheRightAreShorter(trees: Array<IntArray>, row: Int, column: Int): Boolean {
        val treeHeight = trees[row][column]

        for (i in column + 1 until trees[0].size) {
            if (trees[row][i] >= treeHeight) {
                return false
            }
        }

        return true
    }

    fun allTreesToTheTopAreShorter(trees: Array<IntArray>, row: Int, column: Int): Boolean {
        val treeHeight = trees[row][column]

        for (i in row - 1 downTo 0) {
            if (trees[i][column] >= treeHeight) {
                return false
            }
        }

        return true
    }

    fun allTreesToTheBottomAreShorter(trees: Array<IntArray>, row: Int, column: Int): Boolean {
        val treeHeight = trees[row][column]

        for (i in row + 1 until trees.size) {
            if (trees[i][column] >= treeHeight) {
                return false
            }
        }

        return true
    }

    fun part1(input: List<String>): Int {
        val trees =
            input.map { it.toCharArray().map { treeHeight -> treeHeight.digitToInt() }.toIntArray() }.toTypedArray()

        var visibleTreesFromOutside = 0
        for (row in 1 until input.size - 1) {
            for (column in 1 until input.first().length - 1) {
                if (allTreesToTheLeftAreShorter(trees, row, column) ||
                    allTreesToTheRightAreShorter(trees, row, column) ||
                    allTreesToTheTopAreShorter(trees, row, column) ||
                    allTreesToTheBottomAreShorter(trees, row, column)
                ) {
                    visibleTreesFromOutside++
                }
            }
        }

        return visibleTreesFromOutside + 2 * trees.size + 2 * (trees[0].size - 2)
    }

    fun visibleTreesToTheLeft(trees: Array<IntArray>, row: Int, column: Int): Int {
        val treeHeight = trees[row][column]

        var visibleTrees = 0
        for (i in column - 1 downTo 0) {
            visibleTrees++
            if (trees[row][i] >= treeHeight) {
                break
            }
        }

        return visibleTrees
    }

    fun visibleTreesToTheRight(trees: Array<IntArray>, row: Int, column: Int): Int {
        val treeHeight = trees[row][column]

        var visibleTrees = 0
        for (i in column + 1 until trees[0].size) {
            visibleTrees++
            if (trees[row][i] >= treeHeight) {
                break
            }
        }

        return visibleTrees
    }

    fun visibleTreesToTheTop(trees: Array<IntArray>, row: Int, column: Int): Int {
        val treeHeight = trees[row][column]

        var visibleTrees = 0
        for (i in row - 1 downTo 0) {
            visibleTrees++
            if (trees[i][column] >= treeHeight) {
                break
            }
        }

        return visibleTrees
    }

    fun visibleTreesToTheBottom(trees: Array<IntArray>, row: Int, column: Int): Int {
        val treeHeight = trees[row][column]

        var visibleTrees = 0
        for (i in row + 1 until trees.size) {
            visibleTrees++
            if (trees[i][column] >= treeHeight) {
                break
            }
        }

        return visibleTrees
    }

    fun part2(input: List<String>): Int {
        val trees =
            input.map { it.toCharArray().map { treeHeight -> treeHeight.digitToInt() }.toIntArray() }.toTypedArray()

        var maxScenicScore = 0
        for (row in 1 until input.size - 1) {
            for (column in 1 until input.first().length - 1) {
                val visibleTreesLeft = visibleTreesToTheLeft(trees, row, column)
                val visibleTreesRight = visibleTreesToTheRight(trees, row, column)
                val visibleTreesTop = visibleTreesToTheTop(trees, row, column)
                val visibleTreesBottom = visibleTreesToTheBottom(trees, row, column)

                val currentScenicScore = visibleTreesLeft * visibleTreesRight * visibleTreesTop * visibleTreesBottom

                if (currentScenicScore > maxScenicScore) {
                    maxScenicScore = currentScenicScore
                }
            }
        }

        return maxScenicScore
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    val input = readInput("Day08")

    val resultTest1 = part1(testInput)
    println("Test 1: $resultTest1")
    check(resultTest1 == 21)

    val resultPart1 = part1(input)
    println("Part 1: $resultPart1")
    check(resultPart1 == 1789)

    val resultTest2 = part2(testInput)
    println("Test 2: $resultTest2")
    check(resultTest2 == 8)

    val resultPart2 = part2(input)
    println("Part 2: $resultPart2")
//    check(resultPart2 == 859)
}
