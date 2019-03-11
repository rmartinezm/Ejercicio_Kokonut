package io.arkstud.ejerciciokokonut.ui.log_in

import io.arkstud.ejerciciokokonut.network.user.response.LogInResponse

interface LogInContract {

    interface ViewModel {
        fun logIn(email: String, password: String)
    }

    interface Repository {
        fun logInWithEmailAndPassword(email: String, password: String, callback: (response: LogInResponse?) -> Unit)
    }

}