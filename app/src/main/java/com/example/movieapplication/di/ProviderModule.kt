package com.example.movieapplication.di

import com.example.domain.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ProviderModule {

    @Provides
    @Singleton
    fun providesDispatcherProvider(dispatcher: DispatcherProviderImpl): DispatcherProvider = dispatcher
}