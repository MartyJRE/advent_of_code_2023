fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput1 = readInput("DayIdx_test_part1")
    check(part1(testInput1) == 1)

    val testInput2 = readInput("DayIdx_test_part2")
    check(part1(testInput2) == 1)

    val input = readInput("DayIdx")
    part1(input).println()
    part2(input).println()
}
