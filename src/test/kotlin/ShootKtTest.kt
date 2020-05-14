import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class ShootKtTest {

    @Test
    fun `something`() {
        val httpClient = mockk<HttpClient>()
        val shoot = createShoot(httpClient)

    }
}