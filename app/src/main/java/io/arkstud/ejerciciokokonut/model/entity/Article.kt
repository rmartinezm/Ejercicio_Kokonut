package io.arkstud.ejerciciokokonut.model.entity

import java.io.Serializable

data class Article(
    var id_post: Int? = null,
    var title: String? = null,
    var body: String? = null,
    var image_url: String? = null,
    var slug: String? = null,
    var header: String? = null,
    var footer: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null
) : Serializable