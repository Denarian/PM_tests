package com.books.app.presentation.di

import com.books.core.rx.SchedulerProvider
import com.books.domain.details.DetailsContract
import com.books.domain.details.DetailsPresenter
import dagger.Module
import dagger.Provides

@Module
class DetailsModule {
    @Provides
    fun provideDetailsPresenter(schedulerProvider: SchedulerProvider): DetailsContract.Presenter = DetailsPresenter(schedulerProvider)
}