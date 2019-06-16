package com.ywn.featuretopheadlines.viewmodel

import com.ywn.common.base.viewmodel.BaseViewModel
import com.ywn.common.mapper.NewsViewItemMapper
import com.ywn.common.model.NewsViewItem
import com.ywn.domain.model.News
import com.ywn.domain.usecase.top_headlines.GetHeadlinesBySourceUseCase
import com.ywn.domain.usecase.top_headlines.GetTopHeadlinesUseCase
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class TopHeadlinesViewModel
@Inject constructor(
    private val getTopHeadlinesBySourceUseCase: GetHeadlinesBySourceUseCase,
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    private val newsViewItemMapper: NewsViewItemMapper
) : BaseViewModel() {


    private val topHeadlinesSubject: BehaviorSubject<NewsViewItem> = BehaviorSubject.create<NewsViewItem>()
    private val topHeadlinesSubjectWithSource: BehaviorSubject<NewsViewItem> = BehaviorSubject.create<NewsViewItem>()


    fun getTopHeadlines(country: String, api_key: String) {

        add(
            Observable.just(GetTopHeadlinesUseCase.Action(country, api_key))
                .compose(getTopHeadlinesUseCase)
                .map(newsViewItemMapper::map)
                .subscribe(topHeadlinesSubject::onNext) {
                    if (it != null)
                        topHeadlinesSubject.onNext(newsViewItemMapper.map(News.error(it)))
                }
        )


    }

    fun getTopHeadlinesWithSource(source: String, api_key: String) {
        add(
            Observable.just(GetHeadlinesBySourceUseCase.Action(source, api_key))
                .compose(getTopHeadlinesBySourceUseCase)
                .map(newsViewItemMapper::map)
                .subscribe(topHeadlinesSubjectWithSource::onNext) {
                    if (it != null)
                        topHeadlinesSubjectWithSource.onNext(newsViewItemMapper.map(News.error(it)))
                }

        )
    }

    fun streamTopHeadlinesSource(): Observable<NewsViewItem> = topHeadlinesSubjectWithSource

    fun streamTopHeadlines(): Observable<NewsViewItem> = topHeadlinesSubject


}