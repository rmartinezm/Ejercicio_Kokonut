package io.arkstud.ejerciciokokonut.ui.article_detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.arkstud.ejerciciokokonut.R
import io.arkstud.ejerciciokokonut.model.entity.Article
import io.arkstud.ejerciciokokonut.model.manager.ResourcesManager
import io.arkstud.ejerciciokokonut.model.manager.SharedPreferencesManager

class ArticleDetailViewModel : ViewModel(), ArticleDetailContract.ViewModel {

    private val key: String = javaClass.name

    private lateinit var article: Article
    private val _message: MutableLiveData<String> = MutableLiveData()
    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val message: LiveData<String> = _message
    val isFavorite: LiveData<Boolean> = _isFavorite

    override fun setArticle(article: Article) {
        this.article = article
    }

    override fun loadFavoriteValue() {
        _isFavorite.value = SharedPreferencesManager.getBoolean("$key${article.id_post}", false)
    }

    override fun changeFavoriteValue() {
        SharedPreferencesManager.putBoolean("$key${article.id_post}", !_isFavorite.value!!)
        _isFavorite.value = !_isFavorite.value!!
        _message.value = ResourcesManager.getString(if(isFavorite.value == true)
            R.string.added_to_favorite
        else
            R.string.removed_from_favorite
        )

    }

}