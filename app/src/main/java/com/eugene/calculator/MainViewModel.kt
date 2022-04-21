package com.eugene.calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class MainViewModel : ViewModel() {
    private val stringBuilder = StringBuilder()

    private var _result = MutableSharedFlow<StringBuilder>(1, 0, BufferOverflow.DROP_OLDEST)
    val result get() = _result.asSharedFlow()

    fun addButtonClick(textOnButton: String) {
        _result.tryEmit(stringBuilder.append(textOnButton))
    }
}