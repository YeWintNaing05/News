package com.ywn.domain.usecase.top_headlines

import com.ywn.domain.executor.PostExecutionThread
import com.ywn.domain.executor.ThreadExecutor
import com.ywn.domain.model.News
import com.ywn.domain.repository.TopHeadlinesRepository
import com.ywn.domain.usecase.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetHeadlinesBySourceUseCase
@Inject constructor(
    private val topHeadlinesRepository: TopHeadlinesRepository,
    postExecutionThread: PostExecutionThread,
    threadExecutor: ThreadExecutor
) : UseCase<GetHeadlinesBySourceUseCase.Action, News>(threadExecutor, postExecutionThread) {


    data class Action(val sources: String, val apiKey: String)

    override fun execute(action: Action): Observable<News> =
        topHeadlinesRepository.getHeadlinesWithSpecificSource(action.sources, action.apiKey).toObservable()


    override fun progress(): News = News.progress()

    override fun error(throwable: Throwable): News = News.error(throwable)
}