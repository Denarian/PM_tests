package com.books.domain

import com.books.core.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

object TestSchedulerProvider : SchedulerProvider {
    override fun background(): Scheduler = Schedulers.trampoline()
    override fun main(): Scheduler = Schedulers.trampoline()
}