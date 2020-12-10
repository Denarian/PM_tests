package com.books.app.presentation.di

import com.books.app.presentation.books.BooksActivity
import com.books.app.presentation.details.DetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [BooksModule::class])
    abstract fun BooksActivity(): BooksActivity


    @ContributesAndroidInjector(modules = [DetailsModule::class])
    abstract fun DetailsActivity(): DetailsActivity
}