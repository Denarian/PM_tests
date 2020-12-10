package com.books.app.presentation.books

import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.books.R
import com.books.app.BaseActivity
import com.books.app.presentation.details.DetailsActivity
import com.books.app.presentation.extension.visible
import com.books.domain.books.BooksContract
import com.books.domain.books.model.Book
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import com.jakewharton.rxbinding3.recyclerview.scrollStateChanges
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_books.*
import java.util.concurrent.TimeUnit

class BooksActivity : BaseActivity<BooksContract.Presenter>(), BooksContract.View {

    private val booksAdapter: BooksAdapter =
        BooksAdapter(this, BooksAdapter.DiffCallback)

    private val books = mutableListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        Glide.with(this).load(R.drawable.patrick_tomasso_books).centerInside().into(toolbarImage)

        recycler.apply {
            layoutManager = LinearLayoutManager(this@BooksActivity)
            adapter = booksAdapter
        }

        booksAdapter.clickObservable.subscribe {
            startActivity(DetailsActivity.getIntent(this@BooksActivity, it))
        }.let { disposables.add(it) }

        searchView.queryTextChanges()
            .skip(1)
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.isNotEmpty() }
            .subscribe {
                presenter.search(it)
            }.let { disposables.add(it) }

        recycler.scrollStateChanges().subscribe {
            if (it == RecyclerView.SCROLL_STATE_IDLE
                && !recycler.canScrollVertically(1)
                && booksAdapter.itemCount > 0
            ) {
                presenter.search(searchView.query, booksAdapter.itemCount)
            }
        }.let { disposables.add(it) }

        presenter.attachView(this)
    }

    override fun display(books: List<Book>, newQuery: Boolean) {
        if (books.isEmpty()) error(getString(R.string.no_results))
        if (newQuery) {
            this.books.apply {
                clear()
                addAll(books)
            }
        } else {
            this.books.addAll(books)
        }
        booksAdapter.submitList(this.books)
        booksAdapter.notifyDataSetChanged()
    }

    override fun progress(isProgress: Boolean) {
        progress.visible = isProgress
    }

    override fun error(msg: String) {
        Snackbar.make(rootContainer, msg, Snackbar.LENGTH_SHORT).show()
    }
}
