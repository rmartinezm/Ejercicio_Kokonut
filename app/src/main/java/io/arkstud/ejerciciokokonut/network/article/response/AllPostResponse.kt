package io.arkstud.ejerciciokokonut.network.article.response

import io.arkstud.ejerciciokokonut.model.entity.Article

data class AllPostResponse(
    var success: Int? = null,
    var message: String? = null,
    var data: Data? = null
)
data class Data(
    var current_page: Int? = null,
    var first_page_url: String? = null,
    var from: Int? = null,
    var last_page: Int? = null,
    var last_page_url: String? = null,
    var next_page_url: String? = null,
    var path: String? = null,
    var per_page: Int? = null,
    var prev_page_url: String? = null,
    var to: Int? = null,
    var total: Int? = null,
    var data: List<Article>? = null
)