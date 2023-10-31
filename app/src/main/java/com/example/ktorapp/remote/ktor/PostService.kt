package com.example.ktorapp.remote.ktor

import com.example.ktorapp.remote.ktor.model.PostRequest
import com.example.ktorapp.remote.ktor.model.PostResponse
import com.example.ktorapp.resource.Resource

interface PostService {
    suspend fun getPosts(): Resource<List<PostResponse>>

    suspend fun createPost(postRequest: PostRequest): Resource<PostResponse>?
}