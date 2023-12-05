fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput1 = readInputLines("DayIdx_test_part1")
    check(part1(testInput1) == 1)

    val testInput2 = readInputLines("DayIdx_test_part2")
    check(part2(testInput2) == 1)

    val input = readInputLines("DayIdx")
    part1(input).println()
    part2(input).println()
}
