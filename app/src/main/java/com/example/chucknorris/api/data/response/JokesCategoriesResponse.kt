package com.example.chucknorris.api.data.response

import java.io.Serializable
data class JokesCategoriesResponse(
    val categories: List<String>
) : Serializable
