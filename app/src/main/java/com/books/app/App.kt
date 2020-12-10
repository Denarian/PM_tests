package com.books.app

import android.app.Application
import com.books.app.presentation.di.AppComponent
import com.books.app.presentation.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().appContext(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }


    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}