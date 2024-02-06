package com.example.jetpackcomposedemo.activity


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.jetpackcomposedemo.models.Item
import com.example.jetpackcomposedemo.models.ItemLegal
import com.example.jetpackcomposedemo.models.ResponseDemo
import com.example.jetpackcomposedemo.retrofit.RetrofitInstance
import kotlinx.coroutines.delay

class FrontierMoreActivity : ComponentActivity() {

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FrontierMoreComposable()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrontierMoreComposable() {
    var response by remember {
        mutableStateOf<ResponseDemo?>(null)
    }


    var imageSettingUrl by remember { mutableStateOf("") }
    var label by remember { mutableStateOf("More Option") }
    var context = LocalContext.current
    LaunchedEffect(true) {
        try {
            delay(3000)
            response = RetrofitInstance().getData()
            val documents = response!!.result
            label = documents.get(0).titleText
            Log.e("inside", "->" + label)
            Log.e("inside", "->" + documents.get(0).settingIcon.asset.ref)
            val string: String = documents.get(0).settingIcon.asset.ref
            val stringArray: List<String> = string.split("-")
            Log.e(
                "inside",
                "-> " + "https://cdn.sanity.io/images/kq4riuh0/production/" + stringArray[1].toString() + "-96x96.png"
            )
            imageSettingUrl =
                "https://cdn.sanity.io/images/kq4riuh0/production/" + stringArray[1].toString() + "-96x96.png"


        } catch (e: Exception) {
            Log.e("inside", "exception")
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
                    Image(
                        painter = painterResource(id = R.drawable.baseline_person_outline_24),
                        contentDescription = "ic user",
                        Modifier
                            .clickable {
                                Toast
                                    .makeText(
                                        context,
                                        "Profile selected..",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                            .height(20.dp)
                            .width(20.dp)
                    )

                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .padding(5.dp, 0.dp, 0.dp, 0.dp)
                    ) {

                        Text(
                            text = "Akshay",
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.primarycolor)
                        )

                        Text(
                            text = "0 Miles",
                            textAlign = TextAlign.Center,
                            fontSize = 10.sp,
                            color = colorResource(id = R.color.primarycolor)
                        )
                    }
                }
            }

            Text(
                text = label,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )

            Box(
                contentAlignment = Alignment.TopEnd
            ) {

                if (imageSettingUrl != null && !imageSettingUrl.isEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(imageSettingUrl),
                        contentDescription = "ic launcher",
                        Modifier
                            .clickable {
                                Toast
                                    .makeText(
                                        context,
                                        "Setting selected..",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                            .height(35.dp)
                            .width(35.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.frontier_more_setting_icon),
                        contentDescription = "ic launcher",
                        Modifier
                            .clickable {
                                Toast
                                    .makeText(
                                        context,
                                        "Setting selected..",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                            .height(35.dp)
                            .width(35.dp)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.93F),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            gridView(LocalContext.current)

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Legal", modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp))

                LegalSection(LocalContext.current)
            }
        }
    }
}

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun gridView(context: Context) {
    lateinit var dummyData: List<Item>
    dummyData = ArrayList<Item>()

    dummyData = dummyData + Item(
        "Deals",
        "https://plus.unsplash.com/premium_photo-1661281282296-3fa2a9f73dbe?q=80&w=3571&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        R.drawable.baseline_local_offer_24
    )
    dummyData = dummyData + Item(
        "Book Hotel",
        "https://images.pexels.com/photos/262048/pexels-photo-262048.jpeg?auto=compress&cs=tinysrgb&w=800",
        R.drawable.baseline_hotel_24
    )
    dummyData = dummyData + Item(
        "Inflight Menu",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtAv2SoxqMqSO-6ZMeNuFcb1qvnnrpV_vQtjA177z1q1XOj2qTkyCHur0X2PXkvY5RhYw&usqp=CAU",
        R.drawable.baseline_fastfood_24
    )

    LazyVerticalGrid(
        GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp)
    ) {

        items(dummyData.size) {

            Card(
                onClick = {
                    Toast.makeText(
                        context,
                        dummyData[it].name + " selected..",
                        Toast.LENGTH_SHORT
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
                            painter = rememberAsyncImagePainter(dummyData[it].itemImage),
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
                            Image(
                                painter = painterResource(id = dummyData[it].icon),
                                contentDescription = "ic launcher",
                                Modifier
                                    .height(20.dp)
                                    .width(20.dp)
                            )

                            Column(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .wrapContentWidth()
                                    .padding(5.dp, 0.dp, 0.dp, 0.dp)
                            ) {

                                Text(
                                    text = dummyData[it].name,
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
fun LegalSection(context: Context) {
    lateinit var dummyLegalSectionData: List<ItemLegal>
    dummyLegalSectionData = ArrayList<ItemLegal>()

    dummyLegalSectionData = dummyLegalSectionData + ItemLegal(
        "Terms and conditions",
        R.drawable.baseline_keyboard_arrow_right_24,
        R.drawable.baseline_insert_drive_file_24
    )

    dummyLegalSectionData = dummyLegalSectionData + ItemLegal(
        "Privacy Policy",
        R.drawable.baseline_keyboard_arrow_right_24,
        R.drawable.baseline_privacy_tip_24
    )

    dummyLegalSectionData = dummyLegalSectionData + ItemLegal(
        "California Privacy Policy",
        R.drawable.baseline_keyboard_arrow_right_24,
        R.drawable.baseline_privacy_tip_24
    )

    LazyColumn() {

        items(dummyLegalSectionData) { item ->

            Row(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(40.dp)
                    .clickable {

                        Toast
                            .makeText(
                                context,
                                item.name + " selected..",
                                Toast.LENGTH_SHORT
                            )
                            .show()

                    },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = item.icon),
                    contentDescription = "" + item.name,
                    Modifier
                        .height(20.dp)
                        .width(20.dp)
                )
                Text(
                    text = item.name,
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp)
                )
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = item.iconNext),
                        contentDescription = "" + item.name,
                        Modifier
                            .height(20.dp)
                            .width(20.dp)
                            .padding(0.dp, 0.dp, 8.dp, 0.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(1.dp))
        }
    }
}
