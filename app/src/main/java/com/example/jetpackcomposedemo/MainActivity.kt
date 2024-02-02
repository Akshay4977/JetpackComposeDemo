package com.example.jetpackcomposedemo


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random
import kotlin.system.measureTimeMillis


class MainActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var result = remember { mutableStateOf("") }
            //CircularProgressBarDemo(percentage = 0.8f, number = 100)

            val viewModel = viewModel<MainViewModel>()
            val time = viewModel.countDownTimer.collectAsState(initial = 10)
            val count = viewModel.stateFlow.collectAsState(initial = 10)

            //timerWithFlow(time.value.toString())
            //rememberScope(count.value.toString())

            //temp()

            val str: String = "abc"
            val temp = str.length ?: -1

            //increment counter to demonstrate state flow
            Column(modifier = Modifier.fillMaxSize()) {

                Button(modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                    onClick = {
                        //viewModel.incrementCounter()
                        //viewModel.game()
                        //higherOrderFunctionDemo(4, ::square)
                        //createLambdaInsteadOfHigherOrderFunction()
                        //inlineFunctionDemo({""})
                        //lambdaDemo()
                        var c = Practice.Car.B()
                        //car(c)


                    }
                ) {
                    Text(text = "counter:  ${count.value}")
                }

                SimpleTextFieldExample("text")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextFieldExample(passwordFieldType: String) {
    var text by remember { mutableStateOf("") }
    var lable by remember { mutableStateOf("text") }

    var response by remember {
        mutableStateOf<ResponseDemo?>(null)
    }

    LaunchedEffect(true) {
        try {
            delay(3000)
            response = Temp().getData()
            val documents = response!!.result
            lable = documents.get(0).passwordFieldType
            Log.e("inside", "->" + lable)
        } catch (e: Exception) {
            Log.e("inside", "exception")
        }
    }

    TextField(
        value = text,
        onValueChange = { newText -> text = newText },
        label = { Text(lable) },
        keyboardOptions =
        if (lable.equals("email")) {
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            )
        } else if (lable.equals("number")) {
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        } else {
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            )
        }
    )
}
