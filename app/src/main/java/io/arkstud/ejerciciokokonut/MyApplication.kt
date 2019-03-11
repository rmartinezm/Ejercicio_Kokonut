package io.arkstud.ejerciciokokonut

import android.app.Application
import io.arkstud.ejerciciokokonut.di.networkModule
import io.arkstud.ejerciciokokonut.di.repositoryModule
import io.arkstud.ejerciciokokonut.di.router
import io.arkstud.ejerciciokokonut.di.viewModelModule
import io.arkstud.ejerciciokokonut.model.manager.ResourcesManager
import io.arkstud.ejerciciokokonut.model.manager.SharedPreferencesManager
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ResourcesManager.initialize(applicationContext)
        SharedPreferencesManager.initialize(applicationContext, BuildConfig.APPLICATION_ID)
        startKoin(this, listOf(viewModelModule, repositoryModule, networkModule, router))
    }

}