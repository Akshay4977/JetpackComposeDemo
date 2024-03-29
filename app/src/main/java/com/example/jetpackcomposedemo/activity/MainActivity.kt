package com.example.jetpackcomposedemo.activity


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposedemo.viewmodel.MainViewModel
import com.example.jetpackcomposedemo.practice.Practice
import com.example.jetpackcomposedemo.models.ResponseDemo
import com.example.jetpackcomposedemo.retrofit.RetrofitInstance
import kotlinx.coroutines.delay


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
            response = RetrofitInstance().getData1()
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
