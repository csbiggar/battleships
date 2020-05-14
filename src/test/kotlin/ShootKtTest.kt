import Status.*
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ShootKtTest {

    @Test
    fun `should find a hit at B5`() {
        val httpClient = mockk<HttpClient>()
        every { httpClient.get(any()) } returns """{ "results": ["H","M"] }"""

        val shoot = createShoot(httpClient)

        val result = shoot(listOf(Coordinate("B", "5"), Coordinate("C", "6")))

        assertThat(result).containsExactly(
            Result(Coordinate("B", "5"), HIT),
            Result(Coordinate("C", "6"), MISS)
        )
    }

    @Test
    fun `should map results to status`() {
        val result = mapResults("""{ "results": ["H","S","M"] }""")
        assertThat(result).containsExactly(HIT, SUNK, MISS)
    }
}