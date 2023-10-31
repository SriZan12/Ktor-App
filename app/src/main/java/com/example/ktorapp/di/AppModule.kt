package com.example.ktorapp.di

import com.example.ktorapp.remote.ktor.MyAppHttpClient
import com.example.ktorapp.remote.ktor.PostService
import com.example.ktorapp.remote.ktor.PostServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesHttpClient(httpClient: MyAppHttpClient): HttpClient = httpClient.getHttpClient()

    @Provides
    fun providesPostService(postServiceImpl: PostServiceImpl): PostService = postServiceImpl


}