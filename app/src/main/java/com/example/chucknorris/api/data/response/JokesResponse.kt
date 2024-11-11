package com.example.chucknorris.api.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JokesResponse(
    val icon_url: String,
    val id: String,
    val url: String,
    val value: String
) : Serializable