package com.books.core.network

import com.books.core.api.BooksApi
import com.books.core.api.model.Book
import com.books.core.repository.BooksRepository
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class BooksRemoteRepository(
        private val apiKey: String,
        okHttpClient: OkHttpClient
) : BooksRepository {

    private val client = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BooksApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(BooksApi::class.java)


    override fun search(
            query: CharSequence,
            startIndex: Int,
            maxResults: Int
    ): Single<List<Book>> {
        return client.searchBooks(query, startIndex, maxResults, apiKey).map {
            it.items ?: listOf()
        }
    }
}