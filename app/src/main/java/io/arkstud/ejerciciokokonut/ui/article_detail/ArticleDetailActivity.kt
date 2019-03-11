package io.arkstud.ejerciciokokonut.ui.article_detail

import android.arch.lifecycle.Observer
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import io.arkstud.ejerciciokokonut.R
import io.arkstud.ejerciciokokonut.model.configuration.GlideApp
import io.arkstud.ejerciciokokonut.model.entity.Article
import io.arkstud.ejerciciokokonut.ui.common.BaseView
import kotlinx.android.synthetic.main.activity_article_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class ArticleDetailActivity : AppCompatActivity(), BaseView {

    private val articleDetailViewModel: ArticleDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        val article = intent.getSerializableExtra("article") as Article?
        observeViewModel()
        articleDetailViewModel.setArticle(article!!)
        articleDetailViewModel.loadFavoriteValue()
        setArticleInformation(article)
    }

    fun fabAddToFav(view: View){
        articleDetailViewModel.changeFavoriteValue()
    }

    private fun setArticleInformation(article: Article) {
        tvTitle?.text = article.title
        tvBody?.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(article.body, Html.FROM_HTML_MODE_COMPACT)
        else
            Html.fromHtml(article.body)
        GlideApp.with(this)
            .load(article.image_url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.ic_no_image)
            .error(R.drawable.ic_no_image)
            .centerCrop()
            .into(ivImage)
    }

    private fun observeViewModel(){
        articleDetailViewModel.message.observe(this, messageObserver(this))
        articleDetailViewModel.isFavorite.observe(this, Observer {
            fabAddToFav.setImageResource(if(it == true)
                R.drawable.ic_heart_fill
            else
                R.drawable.ic_heart_border
            )
        })
    }


}
