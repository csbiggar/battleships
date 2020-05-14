typealias Shoot = (List<Coordinate>) -> List<Result>

data class Coordinate(val x: String, val y: String)
data class Result(val coordinate: Coordinate, val status: Status)
enum class Status {
    HIT, MISS, SUNK
}

fun createShoot(httpClient: HttpClient): Shoot {
    return fun(coordinates: List<Coordinate>): List<Result> {
        return coordinates.map { Result(it, Status.HIT) }
    }
}


class HttpClient {
    fun get(urlToGet: String): String {
        return khttp.get(urlToGet).text
    }
}