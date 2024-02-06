package com.example.jetpackcomposedemo.retrofit

import com.example.jetpackcomposedemo.models.ResponseDemo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    suspend fun getData(): ResponseDemo {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://kq4riuh0.api.sanity.io/v2022-03-07/data/query/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val sanityApiService = retrofit.create(SanityApiService::class.java)

        return withContext(Dispatchers.IO) {
            sanityApiService.getMoreDocuments()
        }
    }
}