package com.example.ktorapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KtorApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}