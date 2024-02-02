package com.example.jetpackcomposedemo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Temp {
    suspend fun getData(): ResponseDemo {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://kq4riuh0.api.sanity.io/v1/data/query/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val sanityApiService = retrofit.create(SanityApiService::class.java)

        return withContext(Dispatchers.IO) {
            sanityApiService.getDocuments()
        }
    }
}