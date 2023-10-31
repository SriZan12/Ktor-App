package com.example.ktorapp.remote.ktor.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    @SerialName("userId")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("body")
    val body: String
){

}