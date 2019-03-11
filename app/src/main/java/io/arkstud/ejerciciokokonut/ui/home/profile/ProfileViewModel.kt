package io.arkstud.ejerciciokokonut.ui.home.profile

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.arkstud.ejerciciokokonut.R
import io.arkstud.ejerciciokokonut.model.Route
import io.arkstud.ejerciciokokonut.model.entity.User
import io.arkstud.ejerciciokokonut.model.manager.ResourcesManager
import io.arkstud.ejerciciokokonut.model.manager.SharedPreferencesManager
import io.arkstud.ejerciciokokonut.router.MyRouter

class ProfileViewModel(
    private val router: MyRouter,
    private val repository: ProfileContract.Repository
) : ViewModel(), ProfileContract.ViewModel {

    private val _user: MutableLiveData<User> = MutableLiveData()
    private val _loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    private val _message: MutableLiveData<String?> = MutableLiveData()
    val user: LiveData<User> get() = _user
    val loading: LiveData<Boolean> get() = _loading
    val message: LiveData<String?> get() = _message

    /**
     *
     */
    override fun logOut() {
        SharedPreferencesManager.putString(
            ResourcesManager.getString(R.string.access_token),
            null
        )
        router.navigateTo(Route.LOGIN, true)
    }

    /**
     *
     */
    override fun loadProfile() {
        _loading.value = true
        val accessToken = SharedPreferencesManager.getString(
            ResourcesManager.getString(R.string.access_token),
            ""
        )!!
        repository.getUserProfile(accessToken){
            _loading.value = false
            _user.value = it?.data
        }
    }

}