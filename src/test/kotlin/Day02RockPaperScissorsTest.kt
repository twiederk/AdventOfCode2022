import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day02RockPaperScissorsTest {

    @Test
    fun calculateScore_WonPaper_Result8() {

        // act
        val score = RockPaperScissors().calculateScore(Result.WON, HandShape.PAPER)

        // assert
        assertThat(score).isEqualTo(8)
    }

    @Test
    fun calculateScore_LostRock_1() {

        // act
        val score = RockPaperScissors().calculateScore(Result.LOST, HandShape.ROCK)

        // assert
        assertThat(score).isEqualTo(1)
    }

    @Test
    fun calculateScore_DrawScissors_6() {

        // act
        val score = RockPaperScissors().calculateScore(Result.DRAW, HandShape.SCISSOR)

        // assert
        assertThat(score).isEqualTo(6)
    }

}
