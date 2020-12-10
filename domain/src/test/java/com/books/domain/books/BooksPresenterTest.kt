package com.books.domain.books

import com.books.core.api.BooksApi
import com.books.core.api.model.QueryReponse
import com.books.domain.TestSchedulerProvider
import com.books.domain.books.model.Book as DomainBook
import com.books.core.api.model.Book
import com.books.core.api.model.ImageLinks
import com.books.core.api.model.Volume
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test

class BooksPresenterTest {

    lateinit var presenter: BooksContract.Presenter

    private val view = DumbView()

    private val emptyUrlMap = mapOf<DomainBook.UrlSize, String?>(
        DomainBook.UrlSize.Thumbnail to null,
        DomainBook.UrlSize.Large to null,
        DomainBook.UrlSize.Medium to null,
        DomainBook.UrlSize.ExtraLarge to null
    )

    val testBookResponse = QueryReponse(
        kind = "kind",
        items = listOf(
            Book(
                id = "id",
                etag = "etag",
                kind = "kind",
                volumeInfo = Volume(
                    title = "Title",
                    publisher = "Publisher",
                    publishedDate = "PublishedDate",
                    description = "Description",
                    pageCount = 100,
                    authors = listOf("Author1", "Author2"),
                    imageLinks = null
                )
            )
        ),
        totalItems = 1
    )

    private val booksApiMock: BooksApi = object : BooksApi {
        override fun searchBooks(
            query: CharSequence,
            startIndex: Int,
            maxResults: Int,
            key: String
        ): Single<QueryReponse> = Single.just(testBookResponse)
    }

    @Test
    fun `Search parse success`() {
        presenter = BooksPresenter(schedulerProvider = TestSchedulerProvider, booksApi = booksApiMock)

        presenter.attachView(view)

        presenter.search("nothing")

        Assert.assertTrue(
            view.books.size == 1 && view.books[0] == DomainBook(
                "id",
                "Title",
                emptyUrlMap,
                "Description",
                "Publisher",
                listOf("Author1", "Author2"),
                100
            )
        )
    }

    class DumbView : BooksContract.View {
        var books: List<DomainBook> = listOf()

        override fun display(books: List<DomainBook>, newQuery: Boolean) {
            this.books = books
        }

        override fun progress(isProgress: Boolean) = Unit
        override fun error(msg: String) = Unit
    }
}