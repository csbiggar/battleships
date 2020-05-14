import Status.SUNK

typealias Results = List<Result>

data class Result(val coordinate: Coordinate, val status: Status)

const val NUMBER_OF_BOAT_SQUARES = 18

fun Results.isComplete(): Boolean = this.filter { it.status == SUNK }.size == NUMBER_OF_BOAT_SQUARES