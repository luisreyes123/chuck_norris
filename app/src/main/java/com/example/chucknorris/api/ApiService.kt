package com.example.chucknorris.api

import com.example.chucknorris.api.data.response.JokesCategoriesResponse
import com.example.chucknorris.api.data.response.JokesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    object CR {
        const val ENDPOINT_RANDOM = "random"
        const val ENDPOINT_CATEGORIES = "categories"
        const val ENDPOINT_RANDOM_CATEGORIES = "random"
    }

    @GET(CR.ENDPOINT_RANDOM)
    suspend fun getRandom(): Response<JokesResponse>
    @GET(CR.ENDPOINT_CATEGORIES)
    suspend fun getCategories(): Response<JokesCategoriesResponse>
    @GET(CR.ENDPOINT_RANDOM_CATEGORIES)
    suspend fun getRandomCategory(@Query("category") category: String? = null): Response<JokesResponse>

}