package com.ywn.featurearticle.viewmodel

import com.ywn.common.base.viewmodel.BaseViewModel
import com.ywn.common.mapper.NewsViewItemMapper
import com.ywn.common.model.NewsViewItem
import com.ywn.domain.model.News
import com.ywn.domain.usecase.article.GetArticleUseCase
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ArticleViewModel
@Inject constructor(
    private val getArticleUseCase: GetArticleUseCase,
    private val newsViewItemMapper: NewsViewItemMapper
) : BaseViewModel() {


    private val articlesSubject: BehaviorSubject<NewsViewItem> = BehaviorSubject.create<NewsViewItem>()


    fun getArticles(q: String, api_key: String) {
        add(
            Observable.just(GetArticleUseCase.Action(api_key,q))
                .compose(getArticleUseCase)
                .map(newsViewItemMapper::map)
                .subscribe(articlesSubject::onNext) {
                    if(it != null)
                    articlesSubject.onNext(newsViewItemMapper.map(News.error(it)))
                }

        )
    }

    fun streamArticles(): Observable<NewsViewItem> = articlesSubject


}