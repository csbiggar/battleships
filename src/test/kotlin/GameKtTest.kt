import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class GameKtTest {
    @Nested
    inner class MakeAGuess {
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

            val newResults = makeAGuess(oldResults, Coordinate('C', 3), mockShoot)

            assertThat(newResults).isEqualTo(mockShootResponse)
            assertThat(shootSlot.captured).containsExactly(
                Coordinate('A', 1),
                Coordinate('B', 2),
                Coordinate('C', 3)
            )
        }
    }

    @Nested
    inner class IterateSquares {
        val a1 = Coordinate('A', 1)
        val b1 = Coordinate('B', 1)
        val c1 = Coordinate('C', 1)

        @Test
        fun `if no squares have been guessed it starts at the first one`() {
            val board = listOf(a1, b1, c1)
            assertThat(findNextGuess(emptyList(), board)).isEqualTo(a1)
        }

        @Test
        fun `if first square has been guessed it guesses the second one`() {
            val board = listOf(a1, b1, c1)
            val result = findNextGuess(listOf(Result(a1, mockk())), board)
            assertThat(result).isEqualTo(b1)
        }

        @Test
        fun `if all squares have already been guessed it blows up`() {
            val board = listOf(a1, b1, c1)
            val previousResults = listOf(
                Result(a1, mockk()),
                Result(b1, mockk()),
                Result(c1, mockk())
            )
            assertThrows<IllegalArgumentException> { findNextGuess(previousResults, board) }
        }
    }
}