package com.books.domain

import com.books.core.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseContract.View>(val schedulerProvider: SchedulerProvider) :
    BaseContract.Presenter<V> {

    val disposables = CompositeDisposable()

    var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detach() {
        disposables.clear()
        this.view = null
    }
}