package com.example.jetpackcomposedemo.activity


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcomposedemo.R
import com.example.jetpackcomposedemo.models.CardObject
import com.example.jetpackcomposedemo.models.LegalObject
import com.example.jetpackcomposedemo.models.ResponseDemo
import com.example.jetpackcomposedemo.retrofit.RetrofitInstance
import com.example.jetpackcomposedemo.viewmodel.LoginViewModel

class FrontierMoreActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FrontierMoreComposable(loginViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrontierMoreComposable(loginViewModel: LoginViewModel) {
    MainView(loginViewModel)
}

@Composable
fun MainView(loginViewModel: LoginViewModel) {
    var response by remember {
        mutableStateOf<ResponseDemo?>(null)
    }

    var imageSettingUrl by remember { mutableStateOf("") }
    var imageUserUrl by remember { mutableStateOf("") }
    var screenTitle by remember { mutableStateOf("More Option") }
    var couponUnitText by remember { mutableStateOf("") }
    var context = LocalContext.current

    var list by remember {
        mutableStateOf(
            mutableStateListOf(
                LegalObject(
                    "", null, null, "Terms and conditions", "Terms and conditions"
                )
            )
        )
    }

    var cardList by remember {
        mutableStateOf(
            mutableStateListOf(
                CardObject(
                    "",
                    "https://plus.unsplash.com/premium_photo-1661281282296-3fa2a9f73dbe?q=80&w=3571&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    null,
                    "Deals"
                )
            )
        )
    }

    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(isLoading) {
        if (isLoading) {
            loginViewModel.getMoreDocuments()
        } else {
            loginViewModel.getMoreDocuments()
        }
    }

    LaunchedEffect(key1 = loginViewModel.responseMoreDemo) {
        loginViewModel.getMoreDocuments()
    }

    if (loginViewModel.responseMoreDemo.value != null) {
        val result = loginViewModel.responseMoreDemo.value?.result?.get(0)
        screenTitle = result!!.titleText
        couponUnitText = result.couponUnitText
        list = result.legalObject.toMutableStateList()
        cardList = result.cardObject.toMutableStateList()

        imageSettingUrl = result.settingIcon.asset.finalUrl
        imageUserUrl = result.userIcon.asset.finalUrl
    } else {
        LaunchedEffect(true) {
            try {
                response = RetrofitInstance().getData1()
                val documents = response!!.result
                screenTitle = documents.get(0).titleText
                couponUnitText = documents.get(0).couponUnitText
                list = documents.get(0).legalObject.toMutableStateList()
                cardList = documents.get(0).cardObject.toMutableStateList()

                imageSettingUrl = documents.get(0).settingIcon.asset.finalUrl
                imageUserUrl = documents.get(0).userIcon.asset.finalUrl

            } catch (e: Exception) {
                Log.e("inside", "exception")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.mainbackground))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.07F)
                .background(colorResource(R.color.toolbarbackground))
                .padding(8.dp, 0.dp, 8.dp, 0.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                contentAlignment = Alignment.TopStart
            ) {

                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (!imageUserUrl.isEmpty() && imageUserUrl != null) {
                        Image(painter = rememberAsyncImagePainter(imageUserUrl),
                            contentDescription = "ic user",
                            Modifier
                                .clickable {
                                    Toast
                                        .makeText(
                                            context, "Profile selected..", Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                                .height(20.dp)
                                .width(20.dp))
                    } else {
                        Image(painter = painterResource(id = R.drawable.baseline_person_outline_24),
                            contentDescription = "ic user",
                            Modifier
                                .clickable {
                                    Toast
                                        .makeText(
                                            context, "Profile selected..", Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                                .height(20.dp)
                                .width(20.dp))
                    }

                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .padding(5.dp, 0.dp, 0.dp, 0.dp)
                    ) {

                        Text(
                            text = "John",
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.primarycolor)
                        )

                        Text(
                            text = "0 " + couponUnitText,
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp,
                            color = colorResource(id = R.color.primarycolor)
                        )
                    }

                    Image(painter = painterResource(id = R.drawable.baseline_refresh_24),
                        contentDescription = "ic user",
                        Modifier
                            .clickable {
                                isLoading = !isLoading
                                Toast
                                    .makeText(
                                        context, "Refreshing...", Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                            .height(25.dp)
                            .width(25.dp)
                            .padding(5.dp, 0.dp, 0.dp, 0.dp))
                }
            }

            Text(
                text = screenTitle,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Box(
                contentAlignment = Alignment.TopEnd
            ) {
                if (imageSettingUrl != null && !imageSettingUrl.isEmpty()) {
                    Image(painter = rememberAsyncImagePainter(imageSettingUrl),
                        contentDescription = "ic launcher",
                        Modifier
                            .clickable {
                                Toast
                                    .makeText(
                                        context, "Setting selected..", Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                            .height(35.dp)
                            .width(35.dp))
                } else {
                    Image(painter = painterResource(id = R.drawable.frontier_more_setting_icon),
                        contentDescription = "ic launcher",
                        Modifier
                            .clickable {
                                Toast
                                    .makeText(
                                        context, "Setting selected..", Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                            .height(35.dp)
                            .width(35.dp))
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.93F),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            gridView(LocalContext.current, cardList)

            Column(
                modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Legal", modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp))

                LegalSection(LocalContext.current, list, loginViewModel)
            }
        }
    }
}

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun gridView(context: Context, list: List<CardObject>) {

    LazyVerticalGrid(
        GridCells.Fixed(2), modifier = Modifier.padding(8.dp)
    ) {

        items(list.size) {

            Card(
                onClick = {
                    Toast.makeText(
                        context, list[it].title + " selected..", Toast.LENGTH_SHORT
                    ).show()
                },
                shape = RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp),
                modifier = Modifier.padding(8.dp),

                ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),

                    ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Image(
                            painter = rememberAsyncImagePainter(list[it].cardUrl),
                            contentDescription = "option image",
                            Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(5.dp)
                        )

                        Spacer(modifier = Modifier.height(9.dp))

                        Row(
                            modifier = Modifier
                                .wrapContentHeight()
                                .wrapContentWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (list[it].cardIcon != null) {
                                if (list[it].cardIcon.asset.finalUrl != null) {
                                    Image(
                                        painter = rememberAsyncImagePainter(list[it].cardIcon.asset.finalUrl),
                                        contentDescription = "ic launcher",
                                        Modifier
                                            .height(20.dp)
                                            .width(20.dp)
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .wrapContentWidth()
                                    .padding(5.dp, 0.dp, 0.dp, 0.dp)
                            ) {

                                Text(
                                    text = list[it].title,
                                    textAlign = TextAlign.Center,
                                    fontSize = 14.sp,
                                    color = colorResource(id = R.color.black)
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LegalSection(context: Context, list: List<LegalObject>, loginViewModel: LoginViewModel) {

    LazyColumn {

        items(list) { item ->
            if (item.title != null) {

                Row(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .height(40.dp)
                        .clickable {
                            if (loginViewModel.selectedLegalObject?.title.equals(item.title)) {
                                loginViewModel.selectedLegalObject = null
                            } else {
                                loginViewModel.selectedLegalObject = item
                            }
                        }, verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(8.dp))
                    if (item.legalIcon != null) {
                        Image(
                            painter = rememberAsyncImagePainter(item.legalIcon.asset.finalUrl),
                            contentDescription = "" + item.title,
                            Modifier
                                .height(20.dp)
                                .width(20.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_privacy_tip_24),
                            contentDescription = "" + item.title,
                            Modifier
                                .height(20.dp)
                                .width(20.dp)
                        )
                    }
                    Text(
                        text = item.title, modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp)
                    )
                    Box(
                        contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()
                    ) {
                        if (item.nextIcon != null) {
                            Image(
                                painter = rememberAsyncImagePainter(item.nextIcon.asset.finalUrl),
                                contentDescription = "" + item.title,
                                Modifier
                                    .height(25.dp)
                                    .width(25.dp)
                                    .padding(0.dp, 0.dp, 8.dp, 0.dp)
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_privacy_tip_24),
                                contentDescription = "" + item.title,
                                Modifier
                                    .height(25.dp)
                                    .width(25.dp)
                                    .padding(0.dp, 0.dp, 8.dp, 0.dp)
                            )
                        }
                    }
                }
                if (loginViewModel.selectedLegalObject?.title.equals(item.title)) {
                    Row(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = item.legalDetails,
                            modifier = Modifier
                                .padding(8.dp, 8.dp, 8.dp, 8.dp)
                                .verticalScroll(rememberScrollState()),
                            fontSize = 12.sp
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(1.dp))
                }

                Spacer(modifier = Modifier.height(1.dp))
            }
        }
    }
}
