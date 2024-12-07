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
                if (char == '.') {
                    continue
                }
                if (char in arrayOf('F', '-', '7', '|', 'J', 'L', 'S')) {
                    if (char !in allowed.getOrDefault(Pair(previous, Pair(xDiff, yDiff)), emptyList())) {
                        continue
                    }
                    steps++
                    visited[Pair(x, y)] = true
                    x = newX
                    y = newY
                    char.println()
                    previous = char
                    break
                }
            }
            if (x == xStart && y == yStart) {
                break
            }
        }
        return (steps + 1) / 2
    }

    val testInput = readInputLines("Day10_test")
    check(part1(testInput) == 4)

    val input = readInputLines("Day10")
    part1(input).println()
}