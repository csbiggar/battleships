import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameKtTest {
    @Test
    fun `it makes a guess with the previous coordinates and the new one`() {
        val mockShoot = mockk<Shoot>()
        val mockShootResponse = mockk<List<Result>>()
        val shootSlot = slot<List<Coordinate>>()
        every { mockShoot(capture(shootSlot)) } returns mockShootResponse

        val oldResults = listOf(
            Result(Coordinate('A', 1), mockk()),
            Result(Coordinate('B', 2), mockk())
        )

        val coordinate = Coordinate('C', 3)

        val newResults = makeAGuess(oldResults, coordinate, mockShoot)

        assertThat(newResults).isEqualTo(mockShootResponse)
        assertThat(shootSlot.captured).containsExactly(
            Coordinate('A', 1),
            Coordinate('B', 2),
            Coordinate('C', 3)
        )
    }
}