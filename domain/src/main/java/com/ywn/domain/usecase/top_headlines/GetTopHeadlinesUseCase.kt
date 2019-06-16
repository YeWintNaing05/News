package com.ywn.domain.usecase.top_headlines

import com.ywn.domain.executor.PostExecutionThread
import com.ywn.domain.executor.ThreadExecutor
import com.ywn.domain.model.News
import com.ywn.domain.repository.TopHeadlinesRepository
import com.ywn.domain.usecase.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class
GetTopHeadlinesUseCase
@Inject constructor(
    private val topHeadlinesRepository: TopHeadlinesRepository,
    postExecutionThread: PostExecutionThread,
    threadExecutor: ThreadExecutor
) : UseCase<GetTopHeadlinesUseCase.Action, News>(threadExecutor, postExecutionThread) {

    data class Action(
        val country: String,
        val apiKey: String
    )


    override fun execute(action: Action): Observable<News> {
        return topHeadlinesRepository.getTopNewsHeadlines(action.country, action.apiKey).toObservable()
    }

    override fun progress(): News = News.progress()


    override fun error(throwable: Throwable): News = News.error(throwable)

}