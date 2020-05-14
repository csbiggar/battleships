import Status.*
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Results {

    @Test
    fun `a list of results with less than 18 "sunk"s is not complete`() {
        val results = List(17) { Result(mockk(), SUNK) }

        assertThat(results.isComplete()).isFalse()
    }

    @Test
    fun `a list of results with 18 "sunk"s is complete`() {
        val results = List(18) { Result(mockk(), SUNK) }

        assertThat(results.isComplete()).isTrue()
    }
}