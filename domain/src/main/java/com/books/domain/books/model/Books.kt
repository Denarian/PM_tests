package com.books.domain.books.model

data class Book(
    val id: String,
    val title: String?,
    val urls: Map<UrlSize, String?>?,
    val description: String?,
    val publisher: String?,
    val authors: List<String>?,
    val pageCount: Int?
) {
    enum class UrlSize {
        Thumbnail, Small, Medium, Large, ExtraLarge
    }
}