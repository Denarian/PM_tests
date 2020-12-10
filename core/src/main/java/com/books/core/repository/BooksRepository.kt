package com.books.core.repository

import com.books.core.api.model.Book
import io.reactivex.Single

interface BooksRepository {

    fun search(
            query: CharSequence,
            startIndex: Int = 0,
            maxResults: Int = 40
    ): Single<List<Book>>

}