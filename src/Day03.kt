fun main() {
    /**
     * --- Day 3: Gear Ratios ---
     *
     * You and the Elf eventually reach a gondola lift station; he says the gondola lift will take you up to the water source, but this is as far as he can bring you. You go inside.
     *
     * It doesn't take long to find the gondolas, but there seems to be a problem: they're not moving.
     *
     * "Aaah!"
     *
     * You turn around to see a slightly-greasy Elf with a wrench and a look of surprise. "Sorry, I wasn't expecting anyone! The gondola lift isn't working right now; it'll still be a while before I can fix it." You offer to help.
     *
     * The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one. If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.
     *
     * The engine schematic (your puzzle input) consists of a visual representation of the engine. There are lots of numbers and symbols you don't really understand, but apparently any number adjacent to a symbol, even diagonally, is a "part number" and should be included in your sum. (Periods (.) do not count as a symbol.)
     *
     * Here is an example engine schematic:
     *
     * 467..114..
     * ...*......
     * ..35..633.
     * ......#...
     * 617*......
     * .....+.58.
     * ..592.....
     * ......755.
     * ...$.*....
     * .664.598..
     *
     * In this schematic, two numbers are not part numbers because they are not adjacent to a symbol: 114 (top right) and 58 (middle right). Every other number is adjacent to a symbol and so is a part number; their sum is 4361.
     *
     * Of course, the actual engine schematic is much larger. What is the sum of all of the part numbers in the engine schematic?
     */
    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input.withIndex()) {
            for (match in Regex("""\d+""").findAll(line.value)) {
                var seen = false
                for (y in line.index - 1..line.index + 1) {
                    if (seen) {
                        break
                    }
                    for (x in match.range.first() - 1..match.range.last() + 1) {
                        if (x < 0 || y < 0 || y > input.size - 1 || x > input[y].length - 1) {
                            continue
                        }
                        if (!input[y][x].isDigit() && input[y][x] != '.') {
                            seen = true
                            sum += match.value.toIntOrNull() ?: 0
                            break
                        }
                    }
                }
            }
        }
        return sum
    }

    /**
     * --- Part Two ---
     *
     * The engineer finds the missing part and installs it in the engine! As the engine springs to life, you jump in the closest gondola, finally ready to ascend to the water source.
     *
     * You don't seem to be going very fast, though. Maybe something is still wrong? Fortunately, the gondola has a phone labeled "help", so you pick it up and the engineer answers.
     *
     * Before you can explain the situation, she suggests that you look out the window. There stands the engineer, holding a phone in one hand and waving with the other. You're going so slowly that you haven't even left the station. You exit the gondola.
     *
     * The missing part wasn't the only issue - one of the gears in the engine is wrong. A gear is any * symbol that is adjacent to exactly two part numbers. Its gear ratio is the result of multiplying those two numbers together.
     *
     * This time, you need to find the gear ratio of every gear and add them all up so that the engineer can figure out which gear needs to be replaced.
     *
     * Consider the same engine schematic again:
     *
     * 467..114..
     * ...*......
     * ..35..633.
     * ......#...
     * 617*......
     * .....+.58.
     * ..592.....
     * ......755.
     * ...$.*....
     * .664.598..
     *
     * In this schematic, there are two gears. The first is in the top left; it has part numbers 467 and 35, so its gear ratio is 16345. The second gear is in the lower right; its gear ratio is 451490. (The * adjacent to 617 is not a gear because it is only adjacent to one part number.) Adding up all of the gear ratios produces 467835.
     *
     * What is the sum of all of the gear ratios in your engine schematic?
     *
     */
    fun part2(input: List<String>): Int {
        var sum = 0
        for (line in input.withIndex()) {
            for (character in line.value.withIndex()) {
                if (character.value == '*') {
                    val nums = arrayListOf<Int>()
                    for (y in line.index - 1..line.index + 1) {
                        val seen = ArrayList<Int>()
                        for (x in character.index - 1..character.index + 1) {
                            if (seen.contains(x)) {
                                continue
                            }
                            if (input[y][x].isDigit()) {
                                seen.add(x)
                                val chars = arrayListOf(input[y][x])
                                var xOffset = -1
                                while (x + xOffset >= 0 && input[y][x + xOffset].isDigit()) {
                                    chars.add(0, input[y][x + xOffset])
                                    seen.add(x + xOffset)
                                    xOffset -= 1
                                }
                                xOffset = 1
                                while (x + xOffset < input[y].length && input[y][x + xOffset].isDigit()) {
                                    chars.add(input[y][x + xOffset])
                                    seen.add(x + xOffset)
                                    xOffset += 1
                                }
                                val joined = chars.joinToString("").toIntOrNull() ?: 0
                                nums.add(joined)
                            }
                        }
                    }
                    if (nums.size == 2) {
                        sum += (nums[0] * nums[1])
                    }
                }
            }
        }
        return sum
    }

    val testInput1 = readInputLines("Day03_test_part1")
    check(part1(testInput1) == 4361)

    val testInput2 = readInputLines("Day03_test_part2")
    check(part2(testInput2) == 467835)

    val input = readInputLines("Day03")
    part1(input).println()
    part2(input).println()
}
