package com.example.jetpackcomposedemo.practice

import android.util.Log
import com.example.jetpackcomposedemo.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class Practice {


    /*
    *  enum classes taken only single type for all constant
    * */
    enum class Day(string: String) {
        Monday("Mon"),
        Tuesday("Tues")
    }


    /*
    * sealed classes cant not be instantiated
    * */
    sealed class Car(a : Int) {
        var b = a * a
        class B() : Car(1)
        class C() : Car(2)
    }

    fun car(member: Car) {

        when (member) {
            is Car.B -> Log.e("inside", "Car B")
            is Car.C -> Log.e("inside", "Car C")
        }
    }

    fun MainViewModel.extensionFunctionDemo(marks: Int): Boolean {
        return marks > 90
    }


    // higher-order function definition
    fun higherOrderFunctionDemo(value: Int, square: (Int) -> Int) {
        // invoke regular function using local name
        Log.e("inside", "1")
        Log.e("inside", "value-> $value-> " + square(value))
        Log.e("inside", "2")
    }

    fun square(value: Int): Int {
        Log.e("inside", "3")
        return value * value
    }

    fun lambdaDemo() {
        val modResult = { a: Int, b: Int -> a % b == 0 }
        Log.d("inside", "->" + modResult(6, 2))
    }

    fun createLambdaInsteadOfHigherOrderFunction() {
        Log.d("inside", "->" + 1)
        val output = higherOrderFunctionWithLambdaDemo(4, { a: Int -> a * a })
        val output1 = higherOrderFunctionWithLambdaDemo(4) { a: Int -> a * a }
        Log.d("inside", "output ->" + output)
        Log.d("inside", "output1 ->" + output1)
        Log.d("inside", "output1  output 2 is same")
    }

    fun higherOrderFunctionWithLambdaDemo(a: Int, fn: (Int) -> Int): Int {
        Log.d("inside", "4->" + fn(a))
        return fn(a)
    }

    inline fun inlineFunctionDemo(tempFun: () -> String) {
        Log.d("Inside", "tempfun " + tempFun())
    }

//Extension  function
    /*fun Student.isExcellent(mark: Int): Boolean{
        return mark > 90
    }*/

    fun temp() = runBlocking {


        val total = measureTimeMillis {
            val delay1 = delayFun1()
            val delay2 = delayFun2()
            /*val result1 = delay1.await()
            val result2 = delay2.await()*/

            Log.e("inside", "time taken -> ${delay1 + delay2}")
        }
        Log.e("inside", "done->")
    }

    suspend fun delayFun1(): Long {
        val delayT = 3000L
        delay(delayT)
        Log.e("inside", "delayFun1")
        return delayT
    }

    suspend fun delayFun2(): Long {
        val delayT = 2000L
        delay(delayT)
        Log.e("inside", "delayFun2")
        return delayT
    }
}