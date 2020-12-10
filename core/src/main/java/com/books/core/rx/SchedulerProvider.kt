package com.books.core.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun background(): Scheduler
    fun main(): Scheduler
}