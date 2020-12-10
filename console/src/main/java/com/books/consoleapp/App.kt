package com.books.consoleapp

import com.books.consoleapp.rx.ConsoleSchedulerProvider
import com.books.core.network.BooksRemoteRepository
import com.books.domain.books.BooksContract
import com.books.domain.books.BooksPresenter
import okhttp3.OkHttpClient

fun main() {

    val presenter: BooksContract.Presenter = BooksPresenter(
            ConsoleSchedulerProvider(),
            BooksRemoteRepository("AIzaSyDukijLb3bD4IOTVGqAb9w2ZG5sdC7foBo", OkHttpClient())
    )

}