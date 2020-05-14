
import khttp.get
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class HttpTest {

    @Test
    @Disabled("hits a real endpoint")
    fun `should hit http endpoint`() {
        val response = get("https://challenge27.appspot.com/?shots=B3")

        assertThat(response.statusCode).isEqualTo(200)
        assertThat(response.jsonObject).isEqualTo("")
    }
}