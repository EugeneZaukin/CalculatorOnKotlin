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

    fun clearAll() {
        _result.tryEmit(stringBuilder.clear())
    }

    fun deleteChar() {
        if (stringBuilder.isEmpty()) return
        _result.tryEmit(stringBuilder.deleteAt(stringBuilder.length-1))
    }

    fun addButtonSign(sign: CharSequence) {
        if (stringBuilder.isEmpty()) return

        if (stringBuilder.last() == '.') {
            _result.tryEmit(stringBuilder.append("0$sign"))
            return
        }

        val onlyNumAndPoint = Regex("\\d")
        val lastCharIndex = stringBuilder.lastIndex
        if (onlyNumAndPoint.find(stringBuilder, lastCharIndex) != null) {
            _result.tryEmit(stringBuilder.append(sign))
            return
        }

        if (stringBuilder.last() != sign.first()) {
            _result.tryEmit(
                stringBuilder.replace(
                    lastCharIndex,
                    stringBuilder.length,
                    sign.toString()
                )
            )
            return
        }

        buttonEquals()
    }

    //region CalculateResult
    fun buttonEquals() {
        val sign = Regex("[/*+\\-]")
        val numbers = sign.split(stringBuilder)

        val firstNum = numbers.first().toDouble()
        val secondNum = numbers.last().toDouble()
        calculateResult(firstNum, secondNum)
    }

    private fun calculateResult(firstNum: Double, secondNum: Double) {
        when {
            stringBuilder.contains('/') -> { checkBacklog(firstNum / secondNum) }
            stringBuilder.contains('*') -> { checkBacklog(firstNum * secondNum) }
            stringBuilder.contains('-') -> { checkBacklog(firstNum - secondNum) }
            stringBuilder.contains('+') -> { checkBacklog(firstNum + secondNum) }
        }
    }

    private fun checkBacklog(result: Double) {
        stringBuilder.clear()
        if (result % 1 == 0.0) _result.tryEmit(stringBuilder.append(result.toInt()))
        else _result.tryEmit(stringBuilder.append(result))
    }

    //endregion
}