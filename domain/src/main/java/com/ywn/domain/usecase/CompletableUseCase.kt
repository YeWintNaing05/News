package com.ywn.domain.usecase

import com.ywn.domain.executor.PostExecutionThread
import com.ywn.domain.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<Action>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    fun execute(action: Action): Completable {
        return executeInternal(action).subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
    }

    protected abstract fun executeInternal(action: Action): Completable
}