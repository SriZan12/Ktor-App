package com.example.ktorapp.resource


sealed class Resource<out T> {
    data class Success<out T>(val result: T) : Resource<T>()
    object Loading : Resource<Nothing>()
    data class Error<T>(val errorMessage: Exception) : Resource<T>()
}
