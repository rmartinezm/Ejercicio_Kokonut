package io.arkstud.ejerciciokokonut.network.article

import io.arkstud.ejerciciokokonut.network.article.response.AllPostResponse
import retrofit2.Call
import retrofit2.http.GET

interface ArticleApiService {

    @GET("post/all")
    fun allPost(): Call<AllPostResponse>

}