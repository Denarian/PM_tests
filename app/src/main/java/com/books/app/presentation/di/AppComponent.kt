package com.books.app.presentation.di

import android.content.Context
import com.books.app.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        IoModule::class,
        ActivityBuilder::class
    ]
)
@Singleton
interface AppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(app: Context): Builder

        fun build(): AppComponent
    }
}