package com.books.domain.details

import com.books.core.rx.SchedulerProvider
import com.books.domain.BasePresenter

class DetailsPresenter(schedulerProvider: SchedulerProvider) : BasePresenter<DetailsContract.View>(schedulerProvider), DetailsContract.Presenter