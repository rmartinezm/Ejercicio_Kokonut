package io.arkstud.ejerciciokokonut.ui.log_in

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.arkstud.ejerciciokokonut.R
import io.arkstud.ejerciciokokonut.model.Route
import io.arkstud.ejerciciokokonut.model.manager.ResourcesManager
import io.arkstud.ejerciciokokonut.model.manager.SharedPreferencesManager
import io.arkstud.ejerciciokokonut.router.MyRouter

class LogInViewModel(
    private val repository: LogInContract.Repository,
    private val router: MyRouter
) : ViewModel(), LogInContract.ViewModel {

    private val _loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    private val _message: MutableLiveData<String?> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading
    val message: LiveData<String?> get() = _message

    /**
     *
     * @param email
     * @param password
     */
    override fun logIn(email: String, password: String) {
        if(loading.value!!) return
        _loading.value = true
        repository.logInWithEmailAndPassword(email, password){
            _loading.value = false
            if(it == null)
                _message.value = ResourcesManager.getString(R.string.log_in_error)
            else {
                SharedPreferencesManager.putString(
                    ResourcesManager.getString(R.string.access_token),
                    it.data?.access_token
                )
                router.navigateTo(Route.HOME, true)
            }
        }
    }

}