package com.books.domain.books

import com.books.domain.BaseContract
import com.books.domain.books.model.Book

interface BooksContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun search(query: CharSequence, startIndex: Int = 0)
    }

    interface View : BaseContract.View {
        fun display(books: List<Book>, newQuery: Boolean = false)
        fun progress(isProgress: Boolean)
        fun error(msg: String)
    }
}