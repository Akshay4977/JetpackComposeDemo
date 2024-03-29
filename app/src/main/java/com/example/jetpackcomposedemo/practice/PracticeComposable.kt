package com.example.jetpackcomposedemo.practice

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.random.Random


@Composable
fun timerWithFlow(time: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = time,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun CircularProgressBarDemo(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 20.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 2.dp,
    animDuration: Int = 2000,
    animDelay: Int = 0
) {
    var animatePlayed by remember {
        mutableStateOf(false)
    }

    val curPercentage = animateFloatAsState(
        targetValue = if (animatePlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animatePlayed = true
    }


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = Color.Red,
                -90f,
                360 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(text = (curPercentage.value * number).toInt().toString())
    }
}


@Composable
fun AnimationDemo() {
    var sizeState by remember {
        mutableStateOf(200.dp)
    }

    val size by animateDpAsState(
        targetValue = sizeState,
        tween(durationMillis = 3000, delayMillis = 300)
    )

    val size1 by animateDpAsState(
        targetValue = sizeState,
        spring(Spring.DampingRatioHighBouncy)
    )

    val size2 by animateDpAsState(targetValue = sizeState,
        keyframes {
            durationMillis = 3000
            sizeState at 0 with LinearEasing
            sizeState * 1.0f at 1000 with FastOutLinearInEasing
            sizeState * 2.0f at 2000
        })

    val infiniteTrans = rememberInfiniteTransition()
    val color by infiniteTrans.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Blue,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),
            repeatMode = RepeatMode.Reverse
        )
    )



    Button(
        modifier = Modifier
            .size(size2)
            .background(color),
        onClick = {
            sizeState += 50.dp
        },
    ) {
        Text(text = "click here")
        Log.d("inside", "->")
    }


}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DerivedStateDemo() {
    var counter by remember {
        mutableStateOf(0)
    }

    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch { }

    val text1 by remember {
        derivedStateOf {
            "this is counter $counter"
        }
    }

    Button(
        modifier = Modifier.size(200.dp),
        onClick = {
            counter++
        },
    ) {
        Text(text = "click here")
        Log.d("inside", "->" + text1)
    }

}


@Composable
fun SideEffectDemo() {
    SideEffect {
        Log.d("inside", "sideEffectDemo")
    }
}

@Composable
fun rememberScope(count: String) {
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {

        Button(modifier = Modifier
            .width(200.dp)
            .height(50.dp),
            onClick = {

            }
        ) {
            Text(text = "counter:  $count")
        }
    }
}

@Composable
fun MyImageView(painter: Painter) {
    Card(
        modifier = Modifier.size(200.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun ColorBoxStateDemo(modifier: Modifier = Modifier) {
    val color = remember {
        mutableStateOf(Color.Yellow)
    }
    Box(modifier = modifier
        .background(color.value)
        .clickable {
            color.value = Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat(),
                1f
            )
        })
}

@Composable
fun ColorBox1(modifier: Modifier = Modifier) {
    val color = remember {
        mutableStateOf(Color.Yellow)
    }

    Box(modifier = modifier
        .background(color.value)
        .clickable {
            color.value = Color(
                Random.nextFloat(),
                Random.nextFloat(),
                1f
            )
        })
}

@Composable
fun listDemo() {
    val scrollState = rememberScrollState()
    LazyColumn {
        itemsIndexed(
            listOf("is", "this", "way", "to", "learn")
        ) { index, item ->
            Text(
                text = "$item",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

        }
    }
}

