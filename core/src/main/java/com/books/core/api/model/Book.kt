package com.books.core.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QueryReponse(
    val kind: String,
    val items: List<Book>?,
    val totalItems: Int
)

@JsonClass(generateAdapter = true)
data class Book(
    val kind: String,
    val id: String,
    val etag: String,
    val volumeInfo: Volume
)

@JsonClass(generateAdapter = true)
data class Volume(
    val title: String?,
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val pageCount: Int?,
    val authors: List<String>?,
    val imageLinks: ImageLinks?
)

@JsonClass(generateAdapter = true)
data class ImageLinks(
    val smallThumbnail: String?,
    val thumbnail: String?,
    val small: String?,
    val medium: String?,
    val large: String?,
    val extraLarge: String?
)