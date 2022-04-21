package com.eugene.calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class MainViewModel : ViewModel() {
    private val stringBuilder = StringBuilder()

    private var _result = MutableSharedFlow<StringBuilder>(1, 0, BufferOverflow.DROP_OLDEST)
    val result get() = _result.asSharedFlow()

    fun addButtonNumber(textOnButton: String) {
        _result.tryEmit(stringBuilder.append(textOnButton))
    }

    fun addButtonPoint(point: String) {
        if (stringBuilder.contains(point)) return
        if (stringBuilder.isEmpty()) {
            _result.tryEmit(stringBuilder.append("0$point"))
        } else {
            _result.tryEmit(stringBuilder.append(point))
        }
    }


}