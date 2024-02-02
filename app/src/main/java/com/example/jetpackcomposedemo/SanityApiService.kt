package com.example.jetpackcomposedemo

import retrofit2.http.GET

interface SanityApiService {
        @GET("production?query=*%5B_type+%3D%3D+%27login%27%5D%7B%0A++_type%2CpasswordFieldType%2CuserNameFieldType%0A%7D")
        suspend fun getDocuments(): ResponseDemo

}