package io.arkstud.ejerciciokokonut.ui.splash_screen

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Handler
import io.arkstud.ejerciciokokonut.R
import io.arkstud.ejerciciokokonut.model.Route
import io.arkstud.ejerciciokokonut.model.manager.ResourcesManager
import io.arkstud.ejerciciokokonut.model.manager.SharedPreferencesManager
import io.arkstud.ejerciciokokonut.router.MyRouter

class SplashScreenViewModel(
    private val repository: SplashScreenContract.Repository,
    private val myRouter: MyRouter
) : ViewModel(), SplashScreenContract.ViewModel {

    /* Mutable Live Data */
    private val _loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    /* Exposed Live Data */
    val loading: LiveData<Boolean> get() = _loading

    /**
     *
     */
    override fun validateUserLogged() {
        _loading.value = true
        Handler().postDelayed({
            myRouter.navigateTo(
                if(SharedPreferencesManager.getString(ResourcesManager.getString(R.string.access_token)) == null)
                    Route.LOGIN
                else
                    Route.HOME,
                true
            )
        }, 1200)
    }

}