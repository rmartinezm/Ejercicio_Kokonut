package io.arkstud.ejerciciokokonut.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/* OkHttpClient to manage the requests time */
private val client = OkHttpClient.Builder()
    .connectTimeout(100, TimeUnit.SECONDS)
    .readTimeout(100, TimeUnit.SECONDS)
    .build()
/* GsonBuilder to responses of server */
private val gson = GsonBuilder()
    .setLenient()
    .create()
/* Retrofit instance */
private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(ApiUtil.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

/**
 * Obtains a retrofit instance
 * @return [Retrofit]
 */
fun createNetworkClient() : Retrofit = retrofit