package com.example.jetpackcomposedemo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    val countDownTimer = flow<Int> {
        val startValue = 10
        var currentVal = startValue
        emit(currentVal)
        while (currentVal > 0) {
            delay(1000)
            currentVal--
            emit(currentVal)
        }
    }

    private val _stateFlow = MutableStateFlow(0)
     val stateFlow = _stateFlow.asStateFlow()

    companion object{
        const val name : String ="aks"
        fun getName(){

        }
    }

    init {
        //stateAndSharedFLow()
        //collectFlow()
    }

    fun incrementCounter(){
        _stateFlow.value = stateFlow.value + 1
    }
    fun game(){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                launch {
                        delay(5000L)
                        Log.e("inside","here1")
                    }

                launch {
                    delay(2000L)
                    Log.e("inside","here2")
                }

                launch {
                    delay(500L)
                    Log.e("inside","here3")
                }
            }
        }
    }


    private fun stateAndSharedFLow() {

    }

    private fun temp() {

        val flow1 = flow<Int> {
            emit(1)
            delay(500L)
            emit(2)
        }

        val flow2 = flow<Int> {
            emit(1)
            delay(500L)
            emit(2)
        }

        viewModelScope.launch() {
            flow1.flatMapConcat { value ->

                flow {
                    emit(value + 1)
                    delay(500L)
                    emit(value + 2)
                }
            }.collect { value ->
                Log.e("inside", "value-> " + value)
            }
        }
    }

    private fun collectFlow(vararg a: String) {

        viewModelScope.launch {
            countDownTimer
                .filter { time -> time % 2 == 0 }
                .map { time -> time * time }
                .onEach { time ->
                    Log.d("inside", "->" + time)
                }
                .collect { time ->
                    //Log.d("inside", "->" + time)
                }

            countDownTimer.map { time -> time * time }
                .collect { time ->
                    Log.d("inside", "->" + time)
                }


            // terminal flow operator
            val count = countDownTimer.count {
                it % 2 == 0

            }
            Log.d("inside", "->" + count)


            // terminal flow operator
            val count1 = countDownTimer.reduce { accumulator, value ->
                accumulator + value
            }
            Log.d("inside", "->" + count1)


            // terminal flow operator
            val fold = countDownTimer.fold(10) { acc: Int, value: Int ->
                acc + value
            }
            Log.d("inside", "->" + fold)
        }
    }
}