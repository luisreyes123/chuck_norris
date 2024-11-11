package com.example.chucknorris.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    val URL_CHUCKNORRIS = "https://api.chucknorris.io/jokes/"


    private fun makeOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val httpBuilder = OkHttpClient.Builder().apply {
            connectTimeout(180, TimeUnit.SECONDS)
            readTimeout(180, TimeUnit.SECONDS)
            writeTimeout(180, TimeUnit.SECONDS)
            addInterceptor(logging)

        }
        return httpBuilder.build()
    }

    fun makeRetrofitChckNorris(): ApiService {
        return Retrofit.Builder()
            .baseUrl(URL_CHUCKNORRIS)
            .client(makeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

}