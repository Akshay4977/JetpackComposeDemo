package com.example.jetpackcomposedemo.retrofit

import com.example.jetpackcomposedemo.models.ResponseDemo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://kq4riuh0.api.sanity.io/v1/data/query/")
            .addConverterFactory(GsonConverterFactory.create()).build()

    }
    suspend fun getData1(): ResponseDemo {
        val sanityApiService = getRetrofit().create(SanityApiService::class.java)
        return withContext(Dispatchers.IO) {
            sanityApiService.getMoreDocuments()
        }
    }
    suspend fun getLoginDocuments(): ResponseDemo {

        val sanityApiService = getRetrofit().create(SanityApiService::class.java)
        return withContext(Dispatchers.IO) {
            sanityApiService.getLoginDocuments()
        }
    }

}