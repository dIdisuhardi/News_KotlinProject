package com.didisuhardi.Mandiri.domain

import androidx.paging.PagingData
import com.didisuhardi.Mandiri.common.UIState
import com.didisuhardi.Mandiri.data.remote.model.ArticlesItem
import kotlinx.coroutines.flow.Flow

class NewsUseCase(
    private val newsRepository: NewsRepository
) {
    fun executeGetHeadline(): Flow<UIState<List<ArticlesItem>>> = newsRepository.getHeadline()
    fun executeGetAllNews(): Flow<UIState<List<ArticlesItem>>> = newsRepository.getAllNews()
    fun executeGetLoadMore(): Flow<PagingData<ArticlesItem>> = newsRepository.getLoadMoreNews()
    fun executeGetSearchNews(query: String?): Flow<PagingData<ArticlesItem>> = newsRepository.getSearchNews(query)
}