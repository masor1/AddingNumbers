package com.masorone.addingnumbers.presentation.fragment.game

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.masorone.addingnumbers.domain.entity.Level
import java.lang.RuntimeException

class GameViewModelFactory(
    private val level: Level,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java))
            return GameViewModel(level, application) as T
        else
            throw RuntimeException("Unknown view model class: $modelClass")
    }
}