package com.didisuhardi.Mandiri.presentation.load_more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import com.didisuhardi.Mandiri.data.remote.model.ArticlesItem
import com.didisuhardi.Mandiri.domain.NewsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LoadMoreViewModel @Inject constructor(
    newsUseCase: NewsUseCase
) : ViewModel() {
    val getLoadMore: Flow<PagingData<ArticlesItem>> = newsUseCase.executeGetLoadMore().cachedIn(viewModelScope)
}