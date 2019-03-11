package io.arkstud.ejerciciokokonut.network.user

import android.util.Log
import io.arkstud.ejerciciokokonut.network.user.request.LogInRequest
import io.arkstud.ejerciciokokonut.network.user.response.LogInResponse
import io.arkstud.ejerciciokokonut.network.user.response.UserProfileResponse
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserApiManager : KoinComponent {

    private val service: UserApiService by inject()

    /**
     *
     * @param request
     * @param callback
     */
    fun logIn(request: LogInRequest, callback: (response: LogInResponse?) -> Unit){
        val call = service.logIn(request)
        call.enqueue(object : Callback<LogInResponse> {
            override fun onFailure(call: Call<LogInResponse>?, t: Throwable?) {
                Log.e("logIn", t?.message)
                callback(null)
            }
            override fun onResponse(call: Call<LogInResponse>?, response: Response<LogInResponse>?) {
                val logInResponse: LogInResponse = response?.body()!!
                callback(logInResponse)
            }
        })
    }

    /**
     *
     * @param accessToken
     * @param callback
     */
    fun userProfile(accessToken: String, callback: (response: UserProfileResponse?) -> Unit){
        val call = service.userProfile("Bearer $accessToken")
        call.enqueue(object : Callback<UserProfileResponse> {
            override fun onFailure(call: Call<UserProfileResponse>?, t: Throwable?) {
                Log.e("userProfile", t?.message)
                callback(null)
            }
            override fun onResponse(call: Call<UserProfileResponse>?, response: Response<UserProfileResponse>?) {
                val userProfileResponse: UserProfileResponse = response?.body() ?: return
                callback(userProfileResponse)
            }
        })
    }

}