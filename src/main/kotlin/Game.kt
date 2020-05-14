const val NUMBER_OF_BOAT_SQUARES = 18

val BOARD = listOf(
    Coordinate('A', 1)
)

fun createRow(letter: Char) = (0..9).toList().map { Coordinate(letter, it) }
fun createBoard() = ('A'..'J').toList().flatMap { createRow(it) }

tailrec fun doTheThing(previousResults: Results, board: List<Coordinate>): Results {
    println(previousResults.size)
    val nextGuess = findNextGuess(previousResults, board)
    val newResults = makeAGuess(previousResults, nextGuess, createShoot(HttpClient()))

    return if (newResults.isComplete()) {
        newResults
    } else {
        doTheThing(newResults, board)
    }
}

fun findNextGuess(previousResults: Results, board: List<Coordinate>): Coordinate {
    val remainingSquares = board - previousResults.map { it.coordinate }
    require(remainingSquares.isNotEmpty()) { "Something went wrong :(" }

    return remainingSquares.first()
}

fun makeAGuess(
    previousResults: Results,
    nextGuess: Coordinate,
    shoot: Shoot
): Results {
    val previousCoordinates = previousResults.map { it.coordinate }
    return shoot(previousCoordinates + nextGuess)
}

fun main() {
    val board = createBoard()
//    println(board)
    println(doTheThing(emptyList(), board))
}