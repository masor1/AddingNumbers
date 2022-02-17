package com.masorone.addingnumbers.domain.usecase

import com.masorone.addingnumbers.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(maxSumValue: Int) =
        repository.generateQuestion(COUNT_OF_OPTIONS, maxSumValue)

    private companion object {

        const val COUNT_OF_OPTIONS = 6
    }
}