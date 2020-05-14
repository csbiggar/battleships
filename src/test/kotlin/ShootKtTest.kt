import Status.HIT
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ShootKtTest {

    @Test
    fun `something`() {
        val httpClient = mockk<HttpClient>()
        every { httpClient.get(any()) } returns """{ "results": ["H"] }"""

        val shoot = createShoot(httpClient)

        val result = shoot(listOf(Coordinate("B", "5")))

        assertThat(result).containsExactly(Result(Coordinate("B", "5"), HIT))
    }
}