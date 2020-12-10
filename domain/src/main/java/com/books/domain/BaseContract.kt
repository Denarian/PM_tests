package com.books.domain

class BaseContract {
    interface Presenter<V : View> {
        fun attachView(view: V)
        fun detach()
    }

    interface View
}