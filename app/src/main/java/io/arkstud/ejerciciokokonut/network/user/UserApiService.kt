package io.arkstud.ejerciciokokonut.network.user

import io.arkstud.ejerciciokokonut.network.user.request.LogInRequest
import io.arkstud.ejerciciokokonut.network.user.response.LogInResponse
import io.arkstud.ejerciciokokonut.network.user.response.UserProfileResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApiService {

    @POST("login")
    fun logIn(@Body request: LogInRequest): Call<LogInResponse>

    @GET("user/profile")
    fun userProfile(@Header("Authorization") accessToken: String): Call<UserProfileResponse>

}