import HandShape.*
import Result.*

//A for Rock
//B for Paper
//C for Scissors

//X for Rock
//Y for Paper
//Z for Scissors

//Rock defeats Scissors
//Scissors defeats Paper
//Paper defeats Rock

//A Y =>
//B X
//C Z
//
//1 for Rock, 2 for Paper, and 3 for Scissors
//0 if you lost, 3 if the round was a draw, and 6 if you won

class RockPaperScissors {

    fun calculateScore(result: Result, handShape: HandShape): Int {
        var score = 0
        score += when (result) {
            LOST -> 0
            DRAW -> 3
            WON -> 6
        }
        score += when (handShape) {
            ROCK -> 1
            PAPER -> 2
            SCISSOR -> 3
        }
        return score
    }

    fun calculateResult(opponentHand: HandShape, myHand: HandShape): Result {
        when (opponentHand) {
            ROCK -> return when (myHand) {
                ROCK -> DRAW
                PAPER -> WON
                SCISSOR -> LOST
            }
            PAPER -> return when (myHand) {
                ROCK -> LOST
                PAPER -> DRAW
                SCISSOR -> WON
            }
            SCISSOR -> return when (myHand) {
                ROCK -> WON
                PAPER -> LOST
                SCISSOR -> DRAW
            }
        }
    }

}

enum class Result {
    LOST, DRAW, WON
}

enum class HandShape {
    ROCK, PAPER, SCISSOR
}