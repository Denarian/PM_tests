package com.books.domain.books

import com.books.domain.books.model.Book
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import com.books.core.api.model.Book as ApiBook

class ApiBooksToDomainTransformer : SingleTransformer<List<ApiBook>, List<Book>> {
    override fun apply(upstream: Single<List<ApiBook>>): SingleSource<List<Book>> {
        return upstream.map { convert(it) }
    }

    private fun convert(response: List<ApiBook>): List<Book> {
        return response.let { books ->
            books.map {
                Book(
                        id = it.id,
                        authors = it.volumeInfo.authors,
                        description = it.volumeInfo.description,
                        pageCount = it.volumeInfo.pageCount,
                        publisher = it.volumeInfo.publisher,
                        title = it.volumeInfo.title,
                        urls = mapOf(
                                Book.UrlSize.Thumbnail to it.volumeInfo.imageLinks?.thumbnail,
                                Book.UrlSize.Medium to it.volumeInfo.imageLinks?.medium,
                                Book.UrlSize.Large to it.volumeInfo.imageLinks?.large,
                                Book.UrlSize.ExtraLarge to it.volumeInfo.imageLinks?.extraLarge
                        )
                )
            }
        }
    }
}