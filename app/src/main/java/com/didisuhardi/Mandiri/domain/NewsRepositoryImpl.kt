package com.didisuhardi.Mandiri.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.didisuhardi.Mandiri.common.UIState
import com.didisuhardi.Mandiri.data.remote.api.NewsApi
import com.didisuhardi.Mandiri.data.remote.model.ArticlesItem
import com.didisuhardi.Mandiri.data.remote.model.NewsErrorModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {
    override fun getHeadline(): Flow<UIState<List<ArticlesItem>>> = flow {
        emit(UIState.Loading())
        try {
            val news = newsApi.getHeadline()
            if (news.isSuccessful) {
                emit(UIState.Success(news.body()?.articles ?: emptyList()))
            } else {
                val json = Gson().fromJson(news.errorBody()?.string(), NewsErrorModel::class.java)
                throw Exception("${json.code} : ${json.message}")
            }
        } catch (e: Exception) {
            emit(UIState.Error(null, e.message.toString()))
        }
    }

    override fun getAllNews(): Flow<UIState<List<ArticlesItem>>> = flow {
        emit(UIState.Loading())
        try {
            val news = newsApi.getHeadline(pageSize = 10, page = 2)
            if (news.isSuccessful) {
                emit(UIState.Success(news.body()?.articles ?: emptyList()))
            } else {
                val json = Gson().fromJson(news.errorBody()?.string(), NewsErrorModel::class.java)
                throw Exception("${json.code} : ${json.message}")
            }
        } catch (e: Exception) {
            emit(UIState.Error(null, e.message.toString()))
        }
    }

    override fun getLoadMoreNews(): Flow<PagingData<ArticlesItem>> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { NewsHeadlinePagingSource(newsApi) }
    ).flow

    override fun getSearchNews(query: String?): Flow<PagingData<ArticlesItem>> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { NewsSearchPagingSource(newsApi, query) }
    ).flow
}