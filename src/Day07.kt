val cardMapPart1 = mapOf(
    'A' to 14,
    'K' to 13,
    'Q' to 12,
    'J' to 11,
    'T' to 10,
    '9' to 9,
    '8' to 8,
    '7' to 7,
    '6' to 6,
    '5' to 5,
    '4' to 4,
    '3' to 3,
    '2' to 2,
)

val cardMapPart2 = mapOf(
    'A' to 14,
    'K' to 13,
    'Q' to 12,
    'T' to 10,
    '9' to 9,
    '8' to 8,
    '7' to 7,
    '6' to 6,
    '5' to 5,
    '4' to 4,
    '3' to 3,
    '2' to 2,
    'J' to 1
)

fun main() {
    fun String.fiveOfAKind(): Boolean {
        return groupBy { it }.values.any { it.size == 5 }
    }

    fun String.wildFiveOfAKind(): Boolean {
        for ((key) in cardMapPart2.minus('J')) {
            val replaced = replace('J', key)
            if (replaced.fiveOfAKind()) {
                return true
            }
        }
        return false
    }

    fun String.fourOfAKind(): Boolean {
        return groupBy { it }.values.any { it.size == 4 }
    }

    fun String.wildFourOfAKind(): Boolean {
        for ((key) in cardMapPart2.minus('J')) {
            val replaced = replace('J', key)
            if (replaced.fourOfAKind()) {
                return true
            }
        }
        return false
    }

    fun String.fullHouse(): Boolean {
        val groupValues = groupBy { it }.values
        return groupValues.any { it.size == 3 } && groupValues.any { it.size == 2 }
    }

    fun String.wildFullHouse(): Boolean {
        for ((key) in cardMapPart2.minus('J')) {
            val replaced = replace('J', key)
            if (replaced.fullHouse()) {
                return true
            }
        }
        return false
    }

    fun String.threeOfAKind(): Boolean {
        return groupBy { it }.values.any { it.size == 3 }
    }

    fun String.wildThreeOfAKind(): Boolean {
        for ((key) in cardMapPart2.minus('J')) {
            val replaced = replace('J', key)
            if (replaced.threeOfAKind()) {
                return true
            }
        }
        return false
    }

    fun String.twoPair(): Boolean {
        return groupBy { it }.values.filter { it.size == 2 }.size == 2
    }

    fun String.wildTwoPair(): Boolean {
        for ((key) in cardMapPart2.minus('J')) {
            val replaced = replace('J', key)
            if (replaced.twoPair()) {
                return true
            }
        }
        return false
    }

    fun String.onePair(): Boolean {
        return groupBy { it }.values.filter { it.size == 2 }.size == 1
    }

    fun String.wildOnePair(): Boolean {
        for ((key) in cardMapPart2.minus('J')) {
            val replaced = replace('J', key)
            if (replaced.onePair()) {
                return true
            }
        }
        return false
    }

    /**
     * --- Day 7: Camel Cards ---
     *
     * Your all-expenses-paid trip turns out to be a one-way, five-minute ride in an airship. (At least it's a cool airship!) It drops you off at the edge of a vast desert and descends back to Island Island.
     *
     * "Did you bring the parts?"
     *
     * You turn around to see an Elf completely covered in white clothing, wearing goggles, and riding a large camel.
     *
     * "Did you bring the parts?" she asks again, louder this time. You aren't sure what parts she's looking for; you're here to figure out why the sand stopped.
     *
     * "The parts! For the sand, yes! Come with me; I will show you." She beckons you onto the camel.
     *
     * After riding a bit across the sands of Desert Island, you can see what look like very large rocks covering half of the horizon. The Elf explains that the rocks are all along the part of Desert Island that is directly above Island Island, making it hard to even get there. Normally, they use big machines to move the rocks and filter the sand, but the machines have broken down because Desert Island recently stopped receiving the parts they need to fix the machines.
     *
     * You've already assumed it'll be your job to figure out why the parts stopped when she asks if you can help. You agree automatically.
     *
     * Because the journey will take a few days, she offers to teach you the game of Camel Cards. Camel Cards is sort of similar to poker except it's designed to be easier to play while riding a camel.
     *
     * In Camel Cards, you get a list of hands, and your goal is to order them based on the strength of each hand. A hand consists of five cards labeled one of A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2. The relative strength of each card follows this order, where A is the highest and 2 is the lowest.
     *
     * Every hand is exactly one type. From strongest to weakest, they are:
     *
     *     Five of a kind, where all five cards have the same label: AAAAA
     *     Four of a kind, where four cards have the same label and one card has a different label: AA8AA
     *     Full house, where three cards have the same label, and the remaining two cards share a different label: 23332
     *     Three of a kind, where three cards have the same label, and the remaining two cards are each different from any other card in the hand: TTT98
     *     Two pair, where two cards share one label, two other cards share a second label, and the remaining card has a third label: 23432
     *     One pair, where two cards share one label, and the other three cards have a different label from the pair and each other: A23A4
     *     High card, where all cards' labels are distinct: 23456
     *
     * Hands are primarily ordered based on type; for example, every full house is stronger than any three of a kind.
     *
     * If two hands have the same type, a second ordering rule takes effect. Start by comparing the first card in each hand. If these cards are different, the hand with the stronger first card is considered stronger. If the first card in each hand have the same label, however, then move on to considering the second card in each hand. If they differ, the hand with the higher second card wins; otherwise, continue with the third card in each hand, then the fourth, then the fifth.
     *
     * So, 33332 and 2AAAA are both four of a kind hands, but 33332 is stronger because its first card is stronger. Similarly, 77888 and 77788 are both a full house, but 77888 is stronger because its third card is stronger (and both hands have the same first and second card).
     *
     * To play Camel Cards, you are given a list of hands and their corresponding bid (your puzzle input). For example:
     *
     * 32T3K 765
     * T55J5 684
     * KK677 28
     * KTJJT 220
     * QQQJA 483
     *
     * This example shows five hands; each hand is followed by its bid amount. Each hand wins an amount equal to its bid multiplied by its rank, where the weakest hand gets rank 1, the second-weakest hand gets rank 2, and so on up to the strongest hand. Because there are five hands in this example, the strongest hand will have rank 5 and its bid will be multiplied by 5.
     *
     * So, the first step is to put the hands in order of strength:
     *
     *     32T3K is the only one pair and the other hands are all a stronger type, so it gets rank 1.
     *     KK677 and KTJJT are both two pair. Their first cards both have the same label, but the second card of KK677 is stronger (K vs T), so KTJJT gets rank 2 and KK677 gets rank 3.
     *     T55J5 and QQQJA are both three of a kind. QQQJA has a stronger first card, so it gets rank 5 and T55J5 gets rank 4.
     *
     * Now, you can determine the total winnings of this set of hands by adding up the result of multiplying each hand's bid with its rank (765 * 1 + 220 * 2 + 28 * 3 + 684 * 4 + 483 * 5). So the total winnings in this example are 6440.
     *
     * Find the rank of every hand in your set. What are the total winnings?
     */
    fun part1(input: List<String>): Int {
        val fiveOfAKind = ArrayList<Pair<String, Int>>()
        val fourOfAKind = ArrayList<Pair<String, Int>>()
        val fullHouse = ArrayList<Pair<String, Int>>()
        val threeOfAKind = ArrayList<Pair<String, Int>>()
        val twoPair = ArrayList<Pair<String, Int>>()
        val onePair = ArrayList<Pair<String, Int>>()
        val highCard = ArrayList<Pair<String, Int>>()
        for (line in input) {
            val (hand, bid) = line.split(" ").map { it.trim() }
            val bidValue = bid.toInt()
            if (hand.fiveOfAKind()) {
                fiveOfAKind.add(Pair(hand, bidValue))
                continue
            }
            if (hand.fourOfAKind()) {
                fourOfAKind.add(Pair(hand, bidValue))
                continue
            }
            if (hand.fullHouse()) {
                fullHouse.add(Pair(hand, bidValue))
                continue
            }
            if (hand.threeOfAKind()) {
                threeOfAKind.add(Pair(hand, bidValue))
                continue
            }
            if (hand.twoPair()) {
                twoPair.add(Pair(hand, bidValue))
                continue
            }
            if (hand.onePair()) {
                onePair.add(Pair(hand, bidValue))
                continue
            }
            highCard.add(Pair(hand, bidValue))
        }
        val comparator = Comparator { o1: Pair<String, Int>, o2: Pair<String, Int> ->
            for (idx in 0..4) {
                if (o1.first[idx] != o2.first[idx]) {
                    return@Comparator (cardMapPart1[o1.first[idx]] ?: 0) - (cardMapPart1[o2.first[idx]] ?: 0)
                }
            }
            return@Comparator 0
        }
        for (arrayList in arrayListOf(
            highCard,
            onePair,
            twoPair,
            threeOfAKind,
            fullHouse,
            fourOfAKind,
            fiveOfAKind
        )) {
            arrayList.sortWith(comparator)
        }
        val sorted = highCard + onePair + twoPair + threeOfAKind + fullHouse + fourOfAKind + fiveOfAKind
        return sorted.withIndex().sumOf { it.value.second * (it.index + 1) }
    }

    /**
     * --- Part Two ---
     *
     * To make things a little more interesting, the Elf introduces one additional rule. Now, J cards are jokers - wildcards that can act like whatever card would make the hand the strongest type possible.
     *
     * To balance this, J cards are now the weakest individual cards, weaker even than 2. The other cards stay in the same order: A, K, Q, T, 9, 8, 7, 6, 5, 4, 3, 2, J.
     *
     * J cards can pretend to be whatever card is best for the purpose of determining hand type; for example, QJJQ2 is now considered four of a kind. However, for the purpose of breaking ties between two hands of the same type, J is always treated as J, not the card it's pretending to be: JKKK2 is weaker than QQQQ2 because J is weaker than Q.
     *
     * Now, the above example goes very differently:
     *
     * 32T3K 765
     * T55J5 684
     * KK677 28
     * KTJJT 220
     * QQQJA 483
     *
     *     32T3K is still the only one pair; it doesn't contain any jokers, so its strength doesn't increase.
     *     KK677 is now the only two pair, making it the second-weakest hand.
     *     T55J5, KTJJT, and QQQJA are now all four of a kind! T55J5 gets rank 3, QQQJA gets rank 4, and KTJJT gets rank 5.
     *
     * With the new joker rule, the total winnings in this example are 5905.
     *
     * Using the new joker rule, find the rank of every hand in your set. What are the new total winnings?
     */
    fun part2(input: List<String>): Int {
        val fiveOfAKind = ArrayList<Pair<String, Int>>()
        val fourOfAKind = ArrayList<Pair<String, Int>>()
        val fullHouse = ArrayList<Pair<String, Int>>()
        val threeOfAKind = ArrayList<Pair<String, Int>>()
        val twoPair = ArrayList<Pair<String, Int>>()
        val onePair = ArrayList<Pair<String, Int>>()
        val highCard = ArrayList<Pair<String, Int>>()
        for (line in input) {
            val (hand, bid) = line.split(" ").map { it.trim() }
            val bidValue = bid.toInt()
            if (hand.wildFiveOfAKind()) {
                fiveOfAKind.add(Pair(hand, bidValue))
                continue
            }
            if (hand.wildFourOfAKind()) {
                fourOfAKind.add(Pair(hand, bidValue))
                continue
            }
            if (hand.wildFullHouse()) {
                fullHouse.add(Pair(hand, bidValue))
                continue
            }
            if (hand.wildThreeOfAKind()) {
                threeOfAKind.add(Pair(hand, bidValue))
                continue
            }
            if (hand.wildTwoPair()) {
                twoPair.add(Pair(hand, bidValue))
                continue
            }
            if (hand.wildOnePair()) {
                onePair.add(Pair(hand, bidValue))
                continue
            }
            highCard.add(Pair(hand, bidValue))
        }
        val comparator = Comparator { o1: Pair<String, Int>, o2: Pair<String, Int> ->
            for (idx in 0..4) {
                if (o1.first[idx] != o2.first[idx]) {
                    return@Comparator (cardMapPart2[o1.first[idx]] ?: 0) - (cardMapPart2[o2.first[idx]] ?: 0)
                }
            }
            return@Comparator 0
        }
        for (arrayList in arrayListOf(
            highCard,
            onePair,
            twoPair,
            threeOfAKind,
            fullHouse,
            fourOfAKind,
            fiveOfAKind
        )) {
            arrayList.sortWith(comparator)
        }
        val sorted = highCard + onePair + twoPair + threeOfAKind + fullHouse + fourOfAKind + fiveOfAKind
        return sorted.withIndex().sumOf { it.value.second * (it.index + 1) }
    }

    val testInput1 = readInputLines("Day07_test_part1")
    check(part1(testInput1) == 6440)

    val testInput2 = readInputLines("Day07_test_part2")
    check(part2(testInput2) == 5905)

    val input = readInputLines("Day07")
    "Result of part 1:\nTotal winnings: ${part1(input)}\n".println()
    "Result of part 2:\nTotal winnings: ${part2(input)}\n".println()
}
