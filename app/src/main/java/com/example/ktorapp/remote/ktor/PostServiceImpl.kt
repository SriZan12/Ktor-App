package com.example.ktorapp.remote.ktor

import com.example.ktorapp.remote.ktor.model.PostRequest
import com.example.ktorapp.remote.ktor.model.PostResponse
import com.example.ktorapp.resource.Resource
import com.example.ktorapp.utils.HttpRoutes.POST
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class PostServiceImpl @Inject constructor(
    private val client: HttpClient
) : PostService {
    override suspend fun getPosts(): Resource<List<PostResponse>> {
        return try {
            Resource.Success(
                client.get {
                }.body()
            )
        } catch (exception: RedirectResponseException) {
            Resource.Error(exception)
        } catch (exception: ServerResponseException) {
            Resource.Error(exception)
        } catch (exception: ClientRequestException) {
            Resource.Error(exception)
        } catch (exception: Exception) {
            Resource.Error(exception)
        }
    }

    override suspend fun createPost(postRequest: PostRequest): Resource<PostResponse> {

        return try {
            val response = client.post {
//                url(POST)
                setBody(postRequest)
//                contentType(ContentType.Application.Json)
            }.body<PostResponse>()

            Resource.Success(response)
        } catch (exception: Exception) {
            Resource.Error(exception)
        }

    }
}