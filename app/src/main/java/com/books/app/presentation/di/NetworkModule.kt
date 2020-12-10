package com.books.app.presentation.di

import android.content.Context
import com.books.R
import com.books.core.network.BooksRemoteRepository
import com.books.core.repository.BooksRepository
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
            context: Context
    ): OkHttpClient = OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, (5 * 1024 * 1024)))
            .build()

    @Provides
    fun provideBooksRepository(context: Context, okHttpClient: OkHttpClient): BooksRepository =
            BooksRemoteRepository(context.getString(R.string.google_books_api_key), okHttpClient)
}