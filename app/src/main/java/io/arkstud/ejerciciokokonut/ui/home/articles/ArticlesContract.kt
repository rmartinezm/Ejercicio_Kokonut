package io.arkstud.ejerciciokokonut.ui.home.articles

import io.arkstud.ejerciciokokonut.network.article.response.AllPostResponse

interface ArticlesContract {

    interface ViewModel {
        fun loadAllArticles()
    }

    interface Repository {
        fun getAllArticles(callback: (response: AllPostResponse?) -> Unit)
    }

}