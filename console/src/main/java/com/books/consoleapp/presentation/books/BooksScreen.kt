package com.books.consoleapp.presentation.books

import com.books.domain.books.BooksContract
import com.books.domain.books.model.Book

class BooksScreen(presenter: BooksContract.Presenter) : BooksContract.View {

    init {
        presenter.attachView(this)
    }

    override fun display(books: List<Book>, newQuery: Boolean) {
        books.forEach {
            println(
                    """
                    =======================
                    Title: ${it.title}    
                    Authors: ${it.authors}
                    =======================
                    Description: ${it.description}
                    =======================
                    
                    
                    """.trimIndent()
            )
        }
    }

    override fun progress(isProgress: Boolean) {
        println(if(isProgress) "Loading..." else "Finished")
    }

    override fun error(msg: String) {
        println("Request errored with $msg")
    }

}