package com.books.app.presentation.di

import com.books.app.rx.AndroidSchedulerProvider
import com.books.core.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class IoModule {
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = AndroidSchedulerProvider()
}