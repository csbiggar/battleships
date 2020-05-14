import com.github.salomonbrys.kotson.get
import com.google.gson.JsonParser

typealias Shoot = (List<Coordinate>) -> Results

data class Coordinate(val x: Char, val y: Int)

enum class Status {
    HIT, MISS, SUNK
}

fun createShoot(httpClient: HttpClient): Shoot {
    return fun(coordinates: List<Coordinate>): List<Result> {
        val url = coordinates.map {
            "${it.x}${it.y}"
        }.joinToString("", "https://challenge27.appspot.com/?shots=")

        val statuses = mapResults(httpClient.get(url))

        return coordinates
            .zip(statuses)
            .map { (coordinate, status) -> Result(coordinate, status) }
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