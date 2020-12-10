package com.books.core.api

import com.books.core.api.model.QueryReponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApi {

    @GET("v1/volumes")
    fun searchBooks(
        @Query("q") query: CharSequence,
        @Query("startIndex") startIndex: Int = 0,
        @Query("maxResults") maxResults: Int = 40,
        @Query("key") key: String = "AIzaSyDukijLb3bD4IOTVGqAb9w2ZG5sdC7foBo"
    ): Single<QueryReponse>

    companion object {
        const val BASE_URL = "https://www.googleapis.com/books/"
    }
}