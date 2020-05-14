fun makeAGuess(previousResults: Results, guess: Coordinate, shoot: Shoot): Results {
    val previousCoordinates = previousResults.map { it.coordinate }
    return shoot(previousCoordinates + guess)
}