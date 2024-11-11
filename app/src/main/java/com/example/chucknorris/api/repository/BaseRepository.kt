package com.example.chucknorris.api.repository

import com.example.chucknorris.api.Resource
import com.example.chucknorris.api.RetrofitClient
import com.example.chucknorris.api.data.response.JokesCategoriesResponse
import com.example.chucknorris.api.data.response.JokesResponse
import retrofit2.HttpException

class BaseRepository {
    val service = RetrofitClient.makeRetrofitChckNorris()

    suspend fun getJoke(): Resource<JokesResponse> {
        return try {
            val serviceResponse = service.getRandom()
            if (serviceResponse.isSuccessful) {
                Resource.success(serviceResponse.body())
            } else {
                throw HttpException(serviceResponse)
            }
        } catch (e: Exception) {
            Resource.error(e.message + "", null)
        }
    }
    suspend fun getCategories(): Resource<JokesCategoriesResponse> {
        return try {
            val serviceResponse = service.getCategories()
            if (serviceResponse.isSuccessful) {
                Resource.success(serviceResponse.body())
            } else {
                throw HttpException(serviceResponse)
            }
        } catch (e: Exception) {
            Resource.error(e.message + "", null)
        }
    }
    suspend fun getRandomCategory(category: String): Resource<JokesResponse> {
        return try {
            val serviceResponse = service.getRandomCategory(category)
            if (serviceResponse.isSuccessful) {
                Resource.success(serviceResponse.body())
            } else {
                throw HttpException(serviceResponse)
            }
        } catch (e: Exception) {
            Resource.error(e.message ?: "Unknown error", null)
        }
    }

}