package com.eugene.calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class MainViewModel : ViewModel() {
    private val stringBuilder = StringBuilder()
    private lateinit var sign: String

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

    fun addButtonSign(sign: String) {
        if (stringBuilder.isEmpty()) return
        this.sign = sign

        if (stringBuilder.endsWith('.')) {
            _result.tryEmit(stringBuilder.append("0$sign"))
            return
        }

        val onlyNumAndPoint = Regex("\\d")
        val lastCharIndex = stringBuilder.lastIndex
        if (onlyNumAndPoint.find(stringBuilder, lastCharIndex) != null) {
            _result.tryEmit(stringBuilder.append(sign))
            return
        }

        if (stringBuilder.endsWith(sign)) {
            this.sign = sign
            _result.tryEmit(
                stringBuilder.replace(
                    lastCharIndex,
                    stringBuilder.length,
                    sign
                )
            )
            return
        }
    }

    //region CalculateResult
    fun buttonEquals() {
        if (!::sign.isInitialized || stringBuilder.endsWith(sign)) return

        val firstNum = stringBuilder.substring(0, stringBuilder.indexOf(sign))
        val secondNum = stringBuilder.substring(stringBuilder.indexOf(sign)+1)
        calculateResult(firstNum.toDouble(), secondNum.toDouble())
    }

    private fun calculateResult(firstNum: Double, secondNum: Double) {
        when (sign) {
            "/" -> checkBacklog(firstNum / secondNum)
            "*" -> checkBacklog(firstNum * secondNum)
            "-" -> checkBacklog(firstNum - secondNum)
            "+" -> checkBacklog(firstNum + secondNum)
        }
    }

    private fun checkBacklog(result: Double) {
        stringBuilder.clear()
        if (result % 1 == 0.0) _result.tryEmit(stringBuilder.append(result.toInt()))
        else _result.tryEmit(stringBuilder.append(result))
    }

    //endregion
}