package io.arkstud.ejerciciokokonut.network.user.response

import io.arkstud.ejerciciokokonut.model.entity.User

data class UserProfileResponse(
    var success: Int? = null,
    var message: String? = null,
    var data: User? = null
)