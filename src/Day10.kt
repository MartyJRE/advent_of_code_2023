fun main() {
    fun part1(input: List<String>): Int {
        val directions = listOf(
            Pair(-1, 0),
            Pair(1, 0),
            Pair(0, -1),
            Pair(0, 1)
        )
        // 'F', '-', '7', '|', 'J', 'L', 'S'
        var allowed = mapOf(
            Pair('S', Pair(-1, 0)) to listOf('F', '-', '7', '|', 'J', 'L', 'S'),
            Pair('S', Pair(1, 0)) to listOf('F', '-', '7', '|', 'J', 'L', 'S'),
            Pair('S', Pair(0, -1)) to listOf('F', '-', '7', '|', 'J', 'L', 'S'),
            Pair('S', Pair(0, 1)) to listOf('F', '-', '7', '|', 'J', 'L', 'S'),
            Pair('F', Pair(-1, 0)) to emptyList(),
            Pair('F', Pair(1, 0)) to listOf('-', '7', 'J', 'S'),
            Pair('F', Pair(0, -1)) to emptyList(),
            Pair('F', Pair(0, 1)) to listOf('|', 'J', 'L', 'S'),
            Pair('-', Pair(-1, 0)) to listOf('F', '-', 'L', 'S'),
            Pair('-', Pair(1, 0)) to listOf('-', '7', 'J', 'S'),
            Pair('-', Pair(0, -1)) to emptyList(),
            Pair('-', Pair(0, 1)) to emptyList(),
            Pair('7', Pair(-1, 0)) to listOf('F', '-', 'L', 'S'),
            Pair('7', Pair(1, 0)) to emptyList(),
            Pair('7', Pair(0, -1)) to emptyList(),
            Pair('7', Pair(0, 1)) to listOf('|', 'J', 'L', 'S'),
            Pair('|', Pair(-1, 0)) to emptyList(),
            Pair('|', Pair(1, 0)) to emptyList(),
            Pair('|', Pair(0, -1)) to listOf('F', '|', '7', 'S'),
            Pair('|', Pair(0, 1)) to listOf('|', 'J', 'L', 'S'),
            Pair('J', Pair(-1, 0)) to listOf('F', '-', 'L', 'S'),
            Pair('J', Pair(1, 0)) to emptyList(),
            Pair('J', Pair(0, -1)) to listOf('F', '7', '|', 'S'),
            Pair('J', Pair(0, 1)) to emptyList(),
            Pair('L', Pair(-1, 0)) to emptyList(),
            Pair('L', Pair(1, 0)) to listOf('-', '7', 'J', 'S'),
            Pair('L', Pair(0, -1)) to listOf('F', '7', '|', 'S'),
            Pair('L', Pair(0, 1)) to emptyList(),
        )
        val visited = mutableMapOf<Pair<Int, Int>, Boolean>()
        val yStart = input.indexOfFirst { it.contains('S') }
        val xStart = input[yStart].indexOf('S')
        var x = xStart
        var y = yStart
        var steps = 0
        var previous = 'S'
        while (true) {
            for ((xDiff, yDiff) in directions) {
                var newX = x + xDiff
                var newY = y + yDiff
                if (newX < 0 || newX >= input.first().length || newY < 0 || newY >= input.size) {
                    continue
                }
                if (visited.contains(Pair(newX, newY))) {
                    if (steps > 1 && newX == xStart && newY == yStart) {
                        x = newX
                        y = newY
                        break
                    }
                    continue
                }
                val char = input[newY][newX]
                if (char !in allowed.getOrDefault(Pair(previous, Pair(xDiff, yDiff)), emptyList())) {
                    continue
                }
                steps++
                visited[Pair(x, y)] = true
                x = newX
                y = newY
                previous = char
                break
            }
            if (x == xStart && y == yStart) {
                break
            }
        }
        return (steps + 1) / 2
    }

    fun part2(input: List<String>): Int {
        val directions = listOf(
            Pair(-1, 0),
            Pair(1, 0),
            Pair(0, -1),
            Pair(0, 1)
        )
        // 'F', '-', '7', '|', 'J', 'L', 'S'
        var allowed = mapOf(
            Pair('S', Pair(-1, 0)) to listOf('F', '-', '7', '|', 'J', 'L', 'S'),
            Pair('S', Pair(1, 0)) to listOf('F', '-', '7', '|', 'J', 'L', 'S'),
            Pair('S', Pair(0, -1)) to listOf('F', '-', '7', '|', 'J', 'L', 'S'),
            Pair('S', Pair(0, 1)) to listOf('F', '-', '7', '|', 'J', 'L', 'S'),
            Pair('F', Pair(-1, 0)) to emptyList(),
            Pair('F', Pair(1, 0)) to listOf('-', '7', 'J', 'S'),
            Pair('F', Pair(0, -1)) to emptyList(),
            Pair('F', Pair(0, 1)) to listOf('|', 'J', 'L', 'S'),
            Pair('-', Pair(-1, 0)) to listOf('F', '-', 'L', 'S'),
            Pair('-', Pair(1, 0)) to listOf('-', '7', 'J', 'S'),
            Pair('-', Pair(0, -1)) to emptyList(),
            Pair('-', Pair(0, 1)) to emptyList(),
            Pair('7', Pair(-1, 0)) to listOf('F', '-', 'L', 'S'),
            Pair('7', Pair(1, 0)) to emptyList(),
            Pair('7', Pair(0, -1)) to emptyList(),
            Pair('7', Pair(0, 1)) to listOf('|', 'J', 'L', 'S'),
            Pair('|', Pair(-1, 0)) to emptyList(),
            Pair('|', Pair(1, 0)) to emptyList(),
            Pair('|', Pair(0, -1)) to listOf('F', '|', '7', 'S'),
            Pair('|', Pair(0, 1)) to listOf('|', 'J', 'L', 'S'),
            Pair('J', Pair(-1, 0)) to listOf('F', '-', 'L', 'S'),
            Pair('J', Pair(1, 0)) to emptyList(),
            Pair('J', Pair(0, -1)) to listOf('F', '7', '|', 'S'),
            Pair('J', Pair(0, 1)) to emptyList(),
            Pair('L', Pair(-1, 0)) to emptyList(),
            Pair('L', Pair(1, 0)) to listOf('-', '7', 'J', 'S'),
            Pair('L', Pair(0, -1)) to listOf('F', '7', '|', 'S'),
            Pair('L', Pair(0, 1)) to emptyList(),
        )
        val visited = mutableMapOf<Pair<Int, Int>, Boolean>()
        val yStart = input.indexOfFirst { it.contains('S') }
        val xStart = input[yStart].indexOf('S')
        var x = xStart
        var y = yStart
        var steps = 0
        var previous = 'S'
        while (true) {
            for ((xDiff, yDiff) in directions) {
                var newX = x + xDiff
                var newY = y + yDiff
                if (newX < 0 || newX >= input.first().length || newY < 0 || newY >= input.size) {
                    continue
                }
                if (visited.contains(Pair(newX, newY))) {
                    if (steps > 1 && newX == xStart && newY == yStart) {
                        x = newX
                        y = newY
                        break
                    }
                    continue
                }
                val char = input[newY][newX]
                if (char !in allowed.getOrDefault(Pair(previous, Pair(xDiff, yDiff)), emptyList())) {
                    continue
                }
                steps++
                visited[Pair(x, y)] = true
                x = newX
                y = newY
                previous = char
                break
            }
            if (x == xStart && y == yStart) {
                break
            }
        }
        var cnt = 0
        for ((lineIdx, line) in input.withIndex()) {
            val lineVisited = mutableListOf<Int>()
            for (idx in line.indices) {
                if (visited.contains(Pair(idx, lineIdx))) {
                    lineVisited.add(idx)
                }
            }
            // find all continuous integer sequences
            // take pairs of them
            // count all `.`s between first.end and second.start
        }
        return cnt
    }

    val testInput1 = readInputLines("Day10_test1")
    check(part1(testInput1) == 4)
    val testInput2 = readInputLines("Day10_test2")
    part2(testInput2).println()
    check(part2(testInput2) == 8)

    val input = readInputLines("Day10")
    part1(input).println()
    part2(input).println()
}