package com.example.jetpackcomposedemo.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcomposedemo.R
import com.example.jetpackcomposedemo.models.ResponseDemo
import com.example.jetpackcomposedemo.retrofit.RetrofitInstance
import com.example.jetpackcomposedemo.viewmodel.LoginViewModel
import kotlinx.coroutines.delay


class LoginActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SimpleTextFieldExample1(loginViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextFieldExample1(loginViewModel: LoginViewModel) {

    var userNameText by remember { mutableStateOf("") }
    var userNameLabel by remember { mutableStateOf("email") }

    var passwordText by remember { mutableStateOf("") }
    var passwordLabel by remember { mutableStateOf("password") }

    var loginButtonLabel by remember { mutableStateOf("Login") }

    var frontierLogoImageUserUrl by remember { mutableStateOf("") }

    var context = LocalContext.current

    var response by remember {
        mutableStateOf<ResponseDemo?>(null)
    }

    LaunchedEffect(key1 = loginViewModel.responseDemo) {
        loginViewModel.getLoginDocuments()
    }
    Log.e("inside", "-->" + loginViewModel.responseDemo.value?.result?.get(0)?.loginButtonType)

    LaunchedEffect(true) {
        try {
            delay(3000)
            response = RetrofitInstance().getLoginDocuments()
            val documents = response!!.result
            userNameLabel = documents.get(0).userNameFieldType
            passwordLabel = documents.get(0).passwordFieldType
            loginButtonLabel = documents.get(0).loginButtonTitle

            frontierLogoImageUserUrl = documents.get(0).bannerImage.asset.finalUrl

            Log.e("inside", "temp->" + documents.get(0).bannerImage.asset.finalUrl)
            Log.e("inside", "" + frontierLogoImageUserUrl)
        } catch (e: Exception) {
            Log.e("inside", "exception")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.mainbackground)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            if (!frontierLogoImageUserUrl.isEmpty() && frontierLogoImageUserUrl != null) {
                Image(
                    painter = rememberAsyncImagePainter(frontierLogoImageUserUrl),
                    contentDescription = "option image",
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(5.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = R.mipmap.frontier_logo),
                    contentDescription = "option image",
                    Modifier
                        .width(300.dp)
                        .height(100.dp)
                        .padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                value = userNameText,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    cursorColor = colorResource(id = R.color.primarycolor),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    textColor = Color.Black
                ),
                onValueChange = { newText -> userNameText = newText },
                label = { Text(userNameLabel) },
                keyboardOptions = if (userNameLabel.equals("email")) {
                    KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email
                    )
                } else if (userNameLabel.equals("number")) {
                    KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                } else {
                    KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text
                    )
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                value = passwordText,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    cursorColor = colorResource(id = R.color.primarycolor),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    textColor = Color.Black
                ),
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = { newText -> passwordText = newText },
                label = { Text(passwordLabel) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(modifier = Modifier
                .width(250.dp)
                .height(45.dp)
                .clip(RoundedCornerShape(5.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.primarycolor)
                ),
                onClick = {
                    if (validateInput(userNameText, passwordText)) {
                        userNameText = ""
                        passwordText = ""
                        val intent = Intent(context, FrontierMoreActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(
                            context, "Invalid credentials", Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                Text(text = loginButtonLabel, color = colorResource(id = R.color.white))
            }
        }
    }
}

fun validateInput(userName: String, password: String): Boolean {
    if (userName.equals("user")) {
        return userName.equals(password)
    } else {
        return false
    }
}
