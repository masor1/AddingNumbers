package com.masorone.addingnumbers.data.repository

import com.masorone.addingnumbers.domain.entity.GameSettings
import com.masorone.addingnumbers.domain.entity.Level
import com.masorone.addingnumbers.domain.entity.Question
import com.masorone.addingnumbers.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_VISIBLE_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOption: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_VISIBLE_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        val from = max(rightAnswer - countOfOption, MIN_VISIBLE_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOption)
        while (options.size < countOfOption) {
            options.add(Random.nextInt(from, to))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(
                    10,
                    3,
                    50,
                    8
                )
            }
            Level.EASY -> {
                GameSettings(
                    10,
                    10,
                    70,
                    45
                )
            }
            Level.MEDIUM -> {
                GameSettings(
                    20,
                    20,
                    80,
                    60
                )
            }
            Level.HARD -> {
                GameSettings(
                    50,
                    30,
                    90,
                    120
                )
            }
        }
    }
}