package com.example.jetpackcomposedemo.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposedemo.models.LegalObject
import com.example.jetpackcomposedemo.models.ResponseDemo
import com.example.jetpackcomposedemo.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var _responseDemo = MutableLiveData<ResponseDemo>()
    var responseDemo = _responseDemo

    var _responseMoreDemo = MutableLiveData<ResponseDemo>()
    var responseMoreDemo = _responseMoreDemo

    var selectedLegalObject : LegalObject? = null
    suspend fun getLoginDocuments() {
        viewModelScope.launch {
            Log.e("inside","getLoginDocuments()")
            _responseDemo.value = RetrofitInstance().getLoginDocuments()
        }
    }
    suspend fun getMoreDocuments() {
        viewModelScope.launch {
            Log.e("inside","getMoreDocuments()")
            _responseMoreDemo.value = RetrofitInstance().getData1()
        }
    }
}