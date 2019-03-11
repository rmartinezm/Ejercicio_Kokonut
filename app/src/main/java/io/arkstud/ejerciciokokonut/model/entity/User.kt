package io.arkstud.ejerciciokokonut.model.entity

data class User(
    var id_user: String? = null,
    var name: String? = null,
    var last_name: String? = null,
    var email: String? = null,
    var image: String? = null,
    var fk_user_type: Number? = null,
    var fk_user_status: Number? = null,
    var user_app_id: String? = null
)