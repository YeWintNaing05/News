package com.ywn.domain.usecase

import com.ywn.domain.executor.PostExecutionThread
import com.ywn.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers

abstract class UseCase<Action, Result>(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) :
    ObservableTransformer<Action, Result> {

    override fun apply(actions: Observable<Action>): ObservableSource<Result> {
        return actions.flatMap(this::execute)
            .onErrorReturn(this::error)
            .subscribeOn(Schedulers.from(threadExecutor))
            .startWith(progress())
            .observeOn(postExecutionThread.scheduler)
    }

    /**
     * Execute the action
     * @param action Action that triggers the use case operation
     * @return A stream of Result model that represents the fact the operation has succeeded
     */
    abstract fun execute(action: Action): Observable<Result>

    /**
     *
     * @return Result model that represents the fact that the operation is in progress
     */
    abstract fun progress(): Result

    /**
     *
     * @param throwable Exception that wraps the reason for failure and will be handled in `ModelMapper` of app module
     * @return Result model that represents the fact that the operation has failed
     */
    abstract fun error(throwable: Throwable): Result
}