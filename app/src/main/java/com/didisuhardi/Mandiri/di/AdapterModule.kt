package com.didisuhardi.Mandiri.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import com.didisuhardi.Mandiri.presentation.home.HomeAllNewsAdapter
import com.didisuhardi.Mandiri.presentation.home.HomeSearchAdapter
import com.didisuhardi.Mandiri.presentation.home.HomeHeadlineAdapter
import com.didisuhardi.Mandiri.presentation.load_more.LoadMoreAdapter

@Module
@InstallIn(ActivityComponent::class)
object AdapterModule {
    @Provides
    fun provideHeadline(): HomeHeadlineAdapter {
        return HomeHeadlineAdapter()
    }

    @Provides
    fun provideSearch(): HomeSearchAdapter {
        return HomeSearchAdapter()
    }

    @Provides
    fun provideAllNews(): HomeAllNewsAdapter {
        return HomeAllNewsAdapter()
    }

    @Provides
    fun provideLoadMoer(): LoadMoreAdapter {
        return LoadMoreAdapter()
    }
}