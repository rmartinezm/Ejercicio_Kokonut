package io.arkstud.ejerciciokokonut.ui.log_in

import io.arkstud.ejerciciokokonut.network.user.UserApiManager
import io.arkstud.ejerciciokokonut.network.user.request.LogInRequest
import io.arkstud.ejerciciokokonut.network.user.response.LogInResponse

class LogInRepository : LogInContract.Repository {

    override fun logInWithEmailAndPassword(
        email: String,
        password: String,
        callback: (response: LogInResponse?) -> Unit
    ) {
        val request = LogInRequest(email, password)
        UserApiManager.logIn(request, callback)
    }


}