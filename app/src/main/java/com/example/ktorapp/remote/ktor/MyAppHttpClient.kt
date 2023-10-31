package com.example.ktorapp.remote.ktor

import android.util.Log
import com.example.ktorapp.utils.HttpRoutes.POST
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject
import kotlin.math.log

class MyAppHttpClient @Inject constructor() {

    fun getHttpClient(): HttpClient = HttpClient(Android) {

//        Connection Timeout -> For How long the connection stays active with the server.
        engine {
            connectTimeout = 30000 // In millis
            socketTimeout = 30000
        }

//        Json Serialization/Deserialization
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

//        Logging --> To Log our HTTP Calls
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
//                    Log the message with the framework of your choice
                }

            }

            level = LogLevel.ALL
        }

//        To also be able to log the responses

        install(ResponseObserver) {
            onResponse {
                // Log the response with the logging framework of your choice
                Log.d("POSTRESPONSE", "getHttpClient: $it")
            }
        }

//        DefaultRequest Configuration
        install(DefaultRequest) {
            url(POST)
            header(HttpHeaders.ContentType,ContentType.Application.Json)
        }
    }
}