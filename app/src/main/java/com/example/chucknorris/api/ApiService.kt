package com.example.chucknorris.api

import com.example.chucknorris.api.data.response.JokesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    object CR {
        const val ENDPOINT_random = "/random"
    }

    @GET(CR.ENDPOINT_random)
    suspend fun getRandom(): Response<JokesResponse>

}