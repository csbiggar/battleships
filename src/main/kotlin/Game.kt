

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