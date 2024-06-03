package com.didisuhardi.Mandiri.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.didisuhardi.Mandiri.data.remote.api.NewsApi
import com.didisuhardi.Mandiri.domain.NewsRepository
import com.didisuhardi.Mandiri.domain.NewsRepositoryImpl
import com.didisuhardi.Mandiri.domain.NewsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Singleton
    @Provides
    fun provideNewsUseCase(newsRepository: NewsRepository): NewsUseCase {
        return NewsUseCase(newsRepository)
    }
}