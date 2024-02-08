package com.example.jetpackcomposedemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposedemo.models.ResponseDemo
import com.example.jetpackcomposedemo.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var _responseDemo = MutableLiveData<ResponseDemo>()
    var responseDemo = _responseDemo

    suspend fun getLoginDocuments() {
        viewModelScope.launch {
            _responseDemo.value = RetrofitInstance().getLoginDocuments()
        }
    }
}