package com.books.app.presentation.details

import android.os.Parcelable
import com.books.domain.books.model.Book
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParcelableBook(
    val id: String,
    val title: String?,
    val urls: Map<Book.UrlSize, String?>?,
    val description: String?,
    val publisher: String?,
    val authors: List<String>?,
    val pageCount: Int?
) : Parcelable {
    companion object {
        fun from(book: Book) = ParcelableBook(
            book.id,
            book.title,
            book.urls,
            book.description,
            book.publisher,
            book.authors,
            book.pageCount
        )
    }

    fun bestImageUrl(): String? =
        this.urls?.values?.filterNotNull()?.last()

}
