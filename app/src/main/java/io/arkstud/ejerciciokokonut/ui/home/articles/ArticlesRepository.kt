package io.arkstud.ejerciciokokonut.ui.home.articles

import io.arkstud.ejerciciokokonut.network.article.ArticleApiManager
import io.arkstud.ejerciciokokonut.network.article.response.AllPostResponse

class ArticlesRepository : ArticlesContract.Repository {

    override fun getAllArticles(callback: (response: AllPostResponse?) -> Unit) = ArticleApiManager.allPost(callback)

}