package io.arkstud.ejerciciokokonut.router

import android.content.Context
import android.content.Intent
import io.arkstud.ejerciciokokonut.model.Route
import io.arkstud.ejerciciokokonut.model.entity.Article
import io.arkstud.ejerciciokokonut.ui.article_detail.ArticleDetailActivity
import io.arkstud.ejerciciokokonut.ui.home.HomeActivity
import io.arkstud.ejerciciokokonut.ui.log_in.LogInActivity
import io.arkstud.ejerciciokokonut.ui.splash_screen.SplashScreenActivity

class MyRouter(private val context: Context) {

    /**
     *
     * @param route
     * @param clearTop
     */
    fun navigateTo(route: Route, clearTop: Boolean = false){
        val intent = Intent(context, when(route){
            Route.SPLASH -> SplashScreenActivity::class.java
            Route.LOGIN -> LogInActivity::class.java
            Route.HOME -> HomeActivity::class.java
            Route.ARTICLE_DETAIL -> ArticleDetailActivity::class.java
        })
        if(clearTop)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    /**
     *
     * @param article
     */
    fun navigateToArticleDetail(article: Article){
        val intent = Intent(context, ArticleDetailActivity::class.java)
        intent.putExtra("article", article)
        context.startActivity(intent)
    }

}