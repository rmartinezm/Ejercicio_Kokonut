package io.arkstud.ejerciciokokonut.network.article

import android.util.Log
import io.arkstud.ejerciciokokonut.network.article.response.AllPostResponse
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ArticleApiManager : KoinComponent {

    private val service: ArticleApiService by inject()

    /**
     *
     * @param callback
     */
    fun allPost(callback: (response: AllPostResponse?) -> Unit){
        val call = service.allPost()
        call.enqueue(object : Callback<AllPostResponse> {
            override fun onFailure(call: Call<AllPostResponse>?, t: Throwable?) {
                Log.e("ArticleApiManager", t?.message)
                callback(null)
            }
            override fun onResponse(call: Call<AllPostResponse>?, response: Response<AllPostResponse>?) {
                val allPostResponse: AllPostResponse = response?.body()!!
                callback(allPostResponse)
            }
        })
    }
}