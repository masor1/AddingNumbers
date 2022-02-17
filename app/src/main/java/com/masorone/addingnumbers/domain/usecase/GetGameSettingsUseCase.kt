package com.masorone.addingnumbers.domain.usecase

import com.masorone.addingnumbers.domain.entity.Level
import com.masorone.addingnumbers.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level) = repository.getGameSettings(level)
}