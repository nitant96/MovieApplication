package com.example.movieapplication.di

import com.example.domain.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {

    override val main: CoroutineDispatcher = Dispatchers.Main

    override val io: CoroutineDispatcher = Dispatchers.IO

    override val computation: CoroutineDispatcher = Dispatchers.Default

}