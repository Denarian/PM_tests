package com.books.app.presentation.books

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.books.R
import com.books.domain.books.model.Book
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class BooksAdapter(private val context: Context, diffCallback: DiffUtil.ItemCallback<Book>) :
    ListAdapter<Book, BooksAdapter.ViewHolder>(diffCallback) {

    private val disposables = CompositeDisposable()

    val clickObservable: Subject<Book> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.element_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = getItem(position)

        holder.itemView.clicks().subscribe {
            clickObservable.onNext(book)
        }.let { disposables.add(it) }

        holder.title.text = book.title
        if (book.urls?.get(Book.UrlSize.Thumbnail) != null) {
            Glide.with(context)
                .load(book.urls!![Book.UrlSize.Thumbnail])
                .into(holder.image)
        } else {
            Glide.with(context).clear(holder.image)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.bookTitle)
        val image: ImageView = view.findViewById(R.id.image)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        disposables.clear()
    }

    object DiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem == newItem
    }
}