package io.arkstud.ejerciciokokonut.ui.home.profile

import io.arkstud.ejerciciokokonut.network.user.response.UserProfileResponse

interface ProfileContract {

    interface ViewModel {
        fun logOut()
        fun loadProfile()
    }

    interface Repository {
        fun getUserProfile(accessToken: String, callback: (response: UserProfileResponse?) -> Unit)
    }

}