package com.obennouna.imedia24.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object IMedia24APIAdapter {
    val apiClient: IMedia24API = Retrofit.Builder()
        .baseUrl("https://www.hse24.de")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IMedia24API::class.java)
}