package com.didisuhardi.Mandiri.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import com.didisuhardi.Mandiri.common.UIState
import com.didisuhardi.Mandiri.data.remote.model.ArticlesItem
import com.didisuhardi.Mandiri.domain.NewsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {
    private var _getSearchNews = MutableStateFlow<PagingData<ArticlesItem>>(PagingData.empty())

    val getHeadline: Flow<UIState<List<ArticlesItem>>> = newsUseCase.executeGetHeadline()
    val getAllNews: Flow<UIState<List<ArticlesItem>>> = newsUseCase.executeGetAllNews()
    val getSearchNews: StateFlow<PagingData<ArticlesItem>> = _getSearchNews

    fun getSearchNews(query: String?) {
        viewModelScope.launch {
            newsUseCase.executeGetSearchNews(query).cachedIn(this).collect {
                _getSearchNews.value = it
            }
        }
    }
}