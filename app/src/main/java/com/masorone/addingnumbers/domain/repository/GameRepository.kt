package com.masorone.addingnumbers.domain.repository

import com.masorone.addingnumbers.domain.entity.GameSettings
import com.masorone.addingnumbers.domain.entity.Level
import com.masorone.addingnumbers.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOption: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}