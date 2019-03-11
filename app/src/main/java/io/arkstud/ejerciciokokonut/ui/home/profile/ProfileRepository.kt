package io.arkstud.ejerciciokokonut.ui.home.profile

import io.arkstud.ejerciciokokonut.network.user.UserApiManager
import io.arkstud.ejerciciokokonut.network.user.response.UserProfileResponse

class ProfileRepository : ProfileContract.Repository {

    override fun getUserProfile(accessToken: String, callback: (response: UserProfileResponse?) -> Unit) {
        UserApiManager.userProfile(accessToken, callback)
    }

}