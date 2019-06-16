package com.ywn.remote.network.interceptor

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

object HttpErrorEvent {
    private val eventSubject = PublishSubject.create<Event>()

    fun subscribe(consumer: Consumer<Event>): Disposable {
        return eventSubject.subscribe(consumer)
    }

    internal fun emit(event: Event) {
        eventSubject.onNext(event)
    }

    class Event internal constructor(val message: String, val type: Type) {

        fun isType(type: Type): Boolean {
            return this.type == type
        }
    }

    enum class Type {
        UNAUTHORIZED, SERVER_ERROR
    }
}