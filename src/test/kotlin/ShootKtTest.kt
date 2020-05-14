import Status.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ShootKtTest {

    @Test
    fun `should find a hit at B5`() {
        val httpClient = mockk<HttpClient>()
        val urlSlot = slot<String>()
        every { httpClient.get(capture(urlSlot)) } returns """{ "results": ["H","M"] }"""

        val shoot = createShoot(httpClient)

        val result = shoot(listOf(Coordinate('B', 5), Coordinate('C', 6)))

        assertThat(result).containsExactly(
            Result(Coordinate('B', 5), HIT),
            Result(Coordinate('C', 6), MISS)
        )
        assertThat(urlSlot.captured).isEqualTo("https://challenge27.appspot.com/?shots=B5C6")
    }

    @Test
    fun `should map results to status`() {
        val result = mapResults("""{ "results": ["H","S","M"] }""")
        assertThat(result).containsExactly(HIT, SUNK, MISS)
    }
}