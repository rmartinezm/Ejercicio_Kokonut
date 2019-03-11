package io.arkstud.ejerciciokokonut.ui.home.articles

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.arkstud.ejerciciokokonut.model.entity.Article

class ArticlesViewModel(
    private val repository: ArticlesContract.Repository
) : ViewModel(), ArticlesContract.ViewModel {

    private val _loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    private val _message: MutableLiveData<String?> = MutableLiveData()
    private val _articles: MutableLiveData<List<Article>?> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading
    val message: LiveData<String?> get() = _message
    val articles: LiveData<List<Article>?> get() = _articles

    override fun loadAllArticles() {
        _loading.value = true
        repository.getAllArticles {
            _loading.value = false
            _articles.value = it?.data?.data
        }
    }


}