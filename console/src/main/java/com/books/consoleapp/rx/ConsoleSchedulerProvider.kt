package com.books.consoleapp.rx

import com.books.core.rx.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class ConsoleSchedulerProvider : SchedulerProvider {
    override fun background(): Scheduler = Schedulers.trampoline()

    override fun main(): Scheduler = Schedulers.trampoline()
}