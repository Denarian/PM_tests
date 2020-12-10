package com.books.domain.books

import com.books.core.api.BooksApi
import com.books.core.repository.BooksRepository
import com.books.core.rx.SchedulerProvider
import com.books.domain.BasePresenter

class BooksPresenter(schedulerProvider: SchedulerProvider, private val booksRepository: BooksRepository) :
    BasePresenter<BooksContract.View>(schedulerProvider), BooksContract.Presenter {

    override fun search(query: CharSequence, startIndex: Int) {
        booksRepository.search(query, startIndex)
            .observeOn(schedulerProvider.main())
            .subscribeOn(schedulerProvider.background())
            .compose { ApiBooksToDomainTransformer().apply(it) }
            .doOnSubscribe { view?.progress(true) }
            .subscribe({
                view?.apply {
                    display(it, startIndex == 0)
                    progress(false)
                }
            }, {
                view?.apply {
                    error(it.message ?: "Error")
                    progress(false)
                }
            }).let {
                disposables.add(it)
            }
    }
}