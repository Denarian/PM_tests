package com.books.app.presentation.di

import com.books.core.repository.BooksRepository
import com.books.core.rx.SchedulerProvider
import com.books.domain.books.BooksContract
import com.books.domain.books.BooksPresenter
import dagger.Module
import dagger.Provides

@Module
class BooksModule {

    @Provides
    fun provideBooksPresenter(schedulerProvider: SchedulerProvider, booksRepository: BooksRepository): BooksContract.Presenter =
            BooksPresenter(schedulerProvider, booksRepository)
}