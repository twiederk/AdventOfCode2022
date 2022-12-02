import HandShape.*
import Result.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class Day02RockPaperScissorsTest {

    @Test
    fun calculateScore_WonPaper_8() {

        // act
        val score = RockPaperScissors().calculateScore(WON, PAPER)

        // assert
        assertThat(score).isEqualTo(8)
    }

    @Test
    fun calculateScore_LostRock_1() {

        // act
        val score = RockPaperScissors().calculateScore(LOST, ROCK)

        // assert
        assertThat(score).isEqualTo(1)
    }

    @Test
    fun calculateScore_DrawScissors_6() {

        // act
        val score = RockPaperScissors().calculateScore(DRAW, SCISSOR)

        // assert
        assertThat(score).isEqualTo(6)
    }

    @Test
    fun calculateResult_RockScissor_Lost() {
        // arrange

        // act
        val result = RockPaperScissors().calculateResult(ROCK, SCISSOR)

        // assert
        assertThat(result).isEqualTo(LOST)
    }

    @Test
    fun calculateResult_ScissorPaper_Lost() {
        // arrange

        // act
        val result = RockPaperScissors().calculateResult(SCISSOR, PAPER)

        // assert
        assertThat(result).isEqualTo(LOST)
    }

    @Test
    fun calculateResult_PaperRock_Lost() {
        // arrange

        // act
        val result = RockPaperScissors().calculateResult(PAPER, ROCK)

        // assert
        assertThat(result).isEqualTo(LOST)
    }

    @Test
    fun calculateResult_ScissorRock_Won() {
        // arrange

        // act
        val result = RockPaperScissors().calculateResult(SCISSOR, ROCK)

        // assert
        assertThat(result).isEqualTo(WON)
    }

    @Test
    fun calculateResult_PaperScissor_Lost() {
        // arrange

        // act
        val result = RockPaperScissors().calculateResult(PAPER, SCISSOR)

        // assert
        assertThat(result).isEqualTo(WON)
    }

    @Test
    fun calculateResult_RockPaper_Lost() {
        // arrange

        // act
        val result = RockPaperScissors().calculateResult(ROCK, PAPER)

        // assert
        assertThat(result).isEqualTo(WON)
    }

    @Test
    fun calculateResult_RockRock_Draw() {
        // arrange

        // act
        val result = RockPaperScissors().calculateResult(ROCK, ROCK)

        // assert
        assertThat(result).isEqualTo(DRAW)
    }

    @Test
    fun calculateResult_PaperPaper_Draw() {
        // arrange

        // act
        val result = RockPaperScissors().calculateResult(PAPER, PAPER)

        // assert
        assertThat(result).isEqualTo(DRAW)
    }

    @Test
    fun calculateResult_ScissorScissor_Draw() {
        // arrange

        // act
        val result = RockPaperScissors().calculateResult(SCISSOR, SCISSOR)

        // assert
        assertThat(result).isEqualTo(DRAW)
    }

    @Test
    fun decryptInput_sampleInput_decryptedData() {
        // arrange
        val encryptedInput = listOf("A Y", "B X", "C Z")
        val rockPaperScissors = RockPaperScissors()

        // act
        val gameInput = rockPaperScissors.decryptInput(encryptedInput) { _, char -> rockPaperScissors.decryptMe(char) }

        // assert
        assertThat(gameInput).contains(
            Pair(ROCK, PAPER),
            Pair(PAPER, ROCK),
            Pair(SCISSOR, SCISSOR)
        )

    }

    @Test
    fun play_exampleInput_scoreIs15() {
        // arrange
        val strategyGuide = listOf("A Y", "B X", "C Z")
        val rockPaperScissors = RockPaperScissors()

        // act
        val score = RockPaperScissors().play(strategyGuide) { _, char -> rockPaperScissors.decryptMe(char) }

        // assert
        assertThat(score).isEqualTo(15)

    }

    @Test
    fun play_part2_scoreIs12() {
        // arrange
        val strategyGuide = listOf("A Y", "B X", "C Z")
        val rockPaperScissors = RockPaperScissors()

        // act
        val score = RockPaperScissors().play(strategyGuide) { handShape, char ->  rockPaperScissors.matchingHandShape(handShape, char) }

        // assert
        assertThat(score).isEqualTo(12)

    }

    @Test
    fun loadData_exampleData_loadedAsString() {

        // act
        val encryptedData = RockPaperScissors().loadData(Path("src", "test", "resources", "Day02_TestData.txt"))

        // assert
        assertThat(encryptedData).hasSize(3)
        assertThat(encryptedData[0]).isEqualTo("A Y")
        assertThat(encryptedData[1]).isEqualTo("B X")
        assertThat(encryptedData[2]).isEqualTo("C Z")
    }

    @Test
    fun decryptPart2_RockDraw_Rock() {

        // act
        val handShape = RockPaperScissors().matchingHandShape(ROCK, 'Y')

        // assert
        assertThat(handShape).isEqualTo(ROCK)
    }

    @Test
    fun decryptPart2_PaperLost_Rock() {

        // act
        val handShape = RockPaperScissors().matchingHandShape(PAPER, 'X')

        // assert
        assertThat(handShape).isEqualTo(ROCK)
    }

    @Test
    fun decryptPart2_ScissorWon_Scissor() {

        // act
        val handShape = RockPaperScissors().matchingHandShape(SCISSOR, 'Z')

        // assert
        assertThat(handShape).isEqualTo(ROCK)
    }


}
