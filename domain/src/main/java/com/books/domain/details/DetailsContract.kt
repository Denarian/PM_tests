package com.books.domain.details

import com.books.domain.BaseContract

interface DetailsContract {
    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View>
}