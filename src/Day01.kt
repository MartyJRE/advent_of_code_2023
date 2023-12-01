fun String.calibration(): Int {
    val firstIndex = indexOfFirst { it.isDigit() }
    val lastIndex = indexOfLast { it.isDigit() }
    if (firstIndex == -1 || lastIndex == -1) {
        // did not find either one of the digits
        return 0
    }
    // the indices can overlap, when there's only one digit, this is ok and desired
    return "${toCharArray()[firstIndex]}${toCharArray()[lastIndex]}".toIntOrNull() ?: 0
}

fun main() {
    /**
     * --- Day 1: Trebuchet?! ---
     *
     * Something is wrong with global snow production, and you've been selected to take a look. The Elves have even given you a map; on it, they've used stars to mark the top fifty locations that are likely to be having problems.
     *
     * You've been doing this long enough to know that to restore snow operations, you need to check all fifty stars by December 25th.
     *
     * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
     *
     * You try to ask why they can't just use a weather machine ("not powerful enough") and where they're even sending you ("the sky") and why your map looks mostly blank ("you sure ask a lot of questions") and hang on did you just say the sky ("of course, where do you think snow comes from") when you realize that the Elves are already loading you into a trebuchet ("please hold still, we need to strap you in").
     *
     * As they're making the final adjustments, they discover that their calibration document (your puzzle input) has been amended by a very young Elf who was apparently just excited to show off her art skills. Consequently, the Elves are having trouble reading the values on the document.
     *
     * The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration value that the Elves now need to recover. On each line, the calibration value can be found by combining the first digit and the last digit (in that order) to form a single two-digit number.
     *
     * For example:
     *
     * 1abc2
     * pqr3stu8vwx
     * a1b2c3d4e5f
     * treb7uchet
     *
     * In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.
     *
     * Consider your entire calibration document. What is the sum of all of the calibration values?
     */
    fun part1(input: List<String>): Int {
        var total = 0
        val iterator = input.iterator()
        while (iterator.hasNext()) {
            val currentLine = iterator.next()
            total += currentLine.calibration()
        }
        return total
    }

    /**
     * --- Part Two ---
     *
     * Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".
     *
     * Equipped with this new information, you now need to find the real first and last digit on each line. For example:
     *
     * two1nine
     * eightwothree
     * abcone2threexyz
     * xtwone3four
     * 4nineeightseven2
     * zoneight234
     * 7pqrstsixteen
     *
     * In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.
     *
     * What is the sum of all of the calibration values?
     */
    fun part2(input: List<String>): Int {
        val digitMap = mapOf(
                // "zero" to 0, // Don't include 0, it wasn't in the description
                "one" to 1,
                "two" to 2,
                "three" to 3,
                "four" to 4,
                "five" to 5,
                "six" to 6,
                "seven" to 7,
                "eight" to 8,
                "nine" to 9
        )
        var total = 0
        val iterator = input.iterator()
        while (iterator.hasNext()) {
            var currentLine = iterator.next()
            // walk the string left to right
            var idx = 0
            while (idx <= currentLine.length) {
                // cut of the left part
                var firstHalf = currentLine.substring(0, idx)
                // try to replace the number text with digit for each one
                for ((str, digit) in digitMap) {
                    /**
                     * In order to deal with overlapping digits, such as `twone` (2, 1),
                     * replace the found string digit with the corresponding integer digit
                     * and a slice of the string digit without the first character, to
                     * ensure that we do not match it again, but can use its remaining
                     * characters to match in later iterations.
                     *
                     * Example:
                     *
                     * Input: `twone`
                     *  | twone
                     * t | wone
                     * tw | one
                     * two | ne - matched two, modify the original string: 2wone and reset the pointer to 0
                     *  | 2wone
                     * 2 | wone
                     * 2w | one
                     * 2wo | ne
                     * 2won | e
                     * 2wone |  - matched one, modify the original string: 2w1ne and reset the pointer to 0
                     *  | 2w1ne
                     * 2 | w1ne
                     * 2w | 1ne
                     * 2w1 | ne
                     * 2w1n | e
                     * 2w1ne |
                     * End of iteration
                     * Matched (2, 1)
                     *
                     * If I only replaced the string digit with its integer value, I would get the following:
                     *
                     * Input: `twone`
                     *  | twone
                     * t | wone
                     * tw | one
                     * two | ne - matched two, modify the original string: 2ne and reset the pointer to 0
                     *  | 2ne
                     * 2 | ne
                     * 2n | e
                     * 2ne |
                     * End of iteration
                     * Matched (2)
                     */
                    firstHalf = firstHalf.replaceFirst(str, digit.toString() + str.substring(1, str.length))
                }
                // keep the right half
                val secondHalf = currentLine.substring(idx, currentLine.length)
                // in case they changed, combine them and reiterate
                if (currentLine != firstHalf + secondHalf) {
                    currentLine = firstHalf + secondHalf
                    idx = 0
                } else
                // in case they stayed the same just advance
                {
                    idx += 1
                }
            }
            total += currentLine.calibration()
        }
        return total
    }

    /*
     * For example:
     *
     * 1abc2
     * pqr3stu8vwx
     * a1b2c3d4e5f
     * treb7uchet
     *
     * In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.
     */
    val testInput1 = readInput("Day01_test_part1")
    check(part1(testInput1) == 142)
    /**
     * Equipped with this new information, you now need to find the real first and last digit on each line. For example:
     *
     * two1nine
     * eightwothree
     * abcone2threexyz
     * xtwone3four
     * 4nineeightseven2
     * zoneight234
     * 7pqrstsixteen
     *
     * In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.
     */
    val testInput2 = readInput("Day01_test_part2")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    "Result of part 1:\nSum of edge digits: ${part1(input)}\n".println()
    "Result of part 2:\nSum of edge digits: ${part2(input)}\n".println()
}
