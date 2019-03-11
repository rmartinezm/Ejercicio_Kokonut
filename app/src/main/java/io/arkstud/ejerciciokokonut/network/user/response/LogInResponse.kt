package io.arkstud.ejerciciokokonut.network.user.response

/**
 * Response of server when call LogIn
 */
data class LogInResponse(
    var success: Int? = null,
    var message: String? = null,
    var data: Data? = null
)
data class Data(
    var token_type: String? = null,
    var expires_in: Long? = null,
    var access_token: String? = null,
    var refresh_token: String? = null
)