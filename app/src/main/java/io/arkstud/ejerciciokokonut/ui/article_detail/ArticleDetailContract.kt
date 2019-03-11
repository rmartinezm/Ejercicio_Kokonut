package io.arkstud.ejerciciokokonut.ui.article_detail
import io.arkstud.ejerciciokokonut.model.entity.Article

interface ArticleDetailContract {

    interface ViewModel {
        fun setArticle(article: Article)
        fun loadFavoriteValue()
        fun changeFavoriteValue()
    }

}