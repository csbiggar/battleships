import com.github.salomonbrys.kotson.get
import com.google.gson.JsonParser

typealias Shoot = (List<Coordinate>) -> List<Result>

data class Coordinate(val x: String, val y: String)
data class Result(val coordinate: Coordinate, val status: Status)
enum class Status {
    HIT, MISS, SUNK
}

fun createShoot(httpClient: HttpClient): Shoot {
    return fun(coordinates: List<Coordinate>): List<Result> {
        val result = httpClient.get("")
        return coordinates.map { Result(it, Status.HIT) }
    }
}

fun mapResults(result: String): List<Status> {
    val body = JsonParser().parse(result)
    return body["results"].asJsonArray
        .map {
            when (it.asString) {
                "H" -> Status.HIT
                "M" -> Status.MISS
                "S" -> Status.SUNK
                else -> throw IllegalArgumentException("What's this? $it")
            }
        }
}


class HttpClient {
    fun get(urlToGet: String): String {
        return khttp.get(urlToGet).text
    }
}