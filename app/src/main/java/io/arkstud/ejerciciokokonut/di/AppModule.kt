package io.arkstud.ejerciciokokonut.di

import io.arkstud.ejerciciokokonut.network.article.ArticleApiService
import io.arkstud.ejerciciokokonut.network.createNetworkClient
import io.arkstud.ejerciciokokonut.network.user.UserApiService
import io.arkstud.ejerciciokokonut.router.MyRouter
import io.arkstud.ejerciciokokonut.ui.article_detail.ArticleDetailViewModel
import io.arkstud.ejerciciokokonut.ui.home.articles.ArticlesContract
import io.arkstud.ejerciciokokonut.ui.home.articles.ArticlesRepository
import io.arkstud.ejerciciokokonut.ui.home.articles.ArticlesViewModel
import io.arkstud.ejerciciokokonut.ui.home.profile.ProfileContract
import io.arkstud.ejerciciokokonut.ui.home.profile.ProfileRepository
import io.arkstud.ejerciciokokonut.ui.home.profile.ProfileViewModel
import io.arkstud.ejerciciokokonut.ui.log_in.LogInContract
import io.arkstud.ejerciciokokonut.ui.log_in.LogInRepository
import io.arkstud.ejerciciokokonut.ui.log_in.LogInViewModel
import io.arkstud.ejerciciokokonut.ui.splash_screen.SplashScreenContract
import io.arkstud.ejerciciokokonut.ui.splash_screen.SplashScreenRepository
import io.arkstud.ejerciciokokonut.ui.splash_screen.SplashScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

/* Retrofit init and creation of Managers */
private val retrofit: Retrofit = createNetworkClient()
private val USER_API: UserApiService = retrofit.create(UserApiService::class.java)
private val ARTICLE_API: ArticleApiService = retrofit.create(ArticleApiService::class.java)

/* Module of ViewModel abstractions */
val viewModelModule = module {
    viewModel { SplashScreenViewModel(get(), get()) }
    viewModel { LogInViewModel(get(), get()) }
    viewModel { ArticlesViewModel(get()) }
    viewModel { ArticleDetailViewModel() }
    viewModel { ProfileViewModel(get(), get()) }
}

/* Module of Repositories */
val repositoryModule = module {
    single<SplashScreenContract.Repository> { SplashScreenRepository() }
    single<LogInContract.Repository> { LogInRepository() }
    single<ArticlesContract.Repository> { ArticlesRepository() }
    single<ProfileContract.Repository> { ProfileRepository() }
}

/* Module of Retrofit managers */
val networkModule = module {
    single { USER_API }
    single { ARTICLE_API }
}

/* Module for manage routes into Application */
val router = module {
    single { MyRouter(androidContext()) }
}