package com.ywn.domain.usecase.article

import com.ywn.domain.executor.PostExecutionThread
import com.ywn.domain.executor.ThreadExecutor
import com.ywn.domain.model.News
import com.ywn.domain.repository.ArticleRepository
import com.ywn.domain.usecase.UseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetArticleUseCase
@Inject constructor(
    private val articleRepository: ArticleRepository,
    postExecutionThread: PostExecutionThread,
    threadExecutor: ThreadExecutor
) : UseCase<GetArticleUseCase.Action, News>(threadExecutor, postExecutionThread) {

    override fun execute(action: Action): Observable<News> =
        articleRepository.getNewArticlesWithSpecificTopic(action.q, action.apiKey).toObservable()



    override fun progress(): News = News.progress()

    override fun error(throwable: Throwable): News = News.error(throwable)

    data class Action(val apiKey: String, val q: String)
}