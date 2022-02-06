package com.ml.controller.response

data class PageResponse<E>(
    var items: List<E>,
    var currentPage: Int,
    var totalItems: Long,
    var totalPages: Int
)