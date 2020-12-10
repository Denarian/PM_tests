package com.books.app.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.books.R
import com.books.app.BaseActivity
import com.books.domain.books.model.Book
import com.books.domain.details.DetailsContract
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*
import java.lang.IllegalStateException

class DetailsActivity : BaseActivity<DetailsContract.Presenter>(), DetailsContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val book = if (intent.hasExtra(KEY_BOOK)) {
            intent.extras?.getParcelable<ParcelableBook>(KEY_BOOK)
        } else {
            throw IllegalStateException("Book object is needed for this activity")
        }

        Glide.with(this).load(book!!.bestImageUrl()).into(cover)
        bookTitle.text = book.title
        description.text = book.description
        authors.text = book.authors.toString()

        presenter.attachView(this)
    }

    companion object {
        const val KEY_BOOK = "KEY_BOOK"

        fun getIntent(context: Context, book: Book): Intent =
            Intent(context, DetailsActivity::class.java).apply { putExtra(KEY_BOOK, ParcelableBook.from(book)) }
    }
}