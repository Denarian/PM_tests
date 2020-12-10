package com.books.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.books.domain.BaseContract
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity<P : BaseContract.Presenter<*>> : AppCompatActivity(), BaseContract.View {

    @Inject
    lateinit var presenter: P

    val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        disposables.clear()
        presenter.detach()
        super.onDestroy()
    }

}