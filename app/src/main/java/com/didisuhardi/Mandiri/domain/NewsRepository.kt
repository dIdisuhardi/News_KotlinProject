package com.didisuhardi.Mandiri.domain

import androidx.paging.PagingData
import com.didisuhardi.Mandiri.common.UIState
import com.didisuhardi.Mandiri.data.remote.model.ArticlesItem
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getHeadline(): Flow<UIState<List<ArticlesItem>>>
    fun getAllNews(): Flow<UIState<List<ArticlesItem>>>
    fun getLoadMoreNews(): Flow<PagingData<ArticlesItem>>
    fun getSearchNews(query: String?): Flow<PagingData<ArticlesItem>>
}