package io.arkstud.ejerciciokokonut.network.user.request


/**
 * Request to log in a user
 */
data class LogInRequest(
    var username: String? = null,
    var password: String? = null
)