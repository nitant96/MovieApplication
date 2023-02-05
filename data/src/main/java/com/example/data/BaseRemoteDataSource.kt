package com.example.data

import com.github.michaelbull.result.mapEither
import com.example.data.calladapter.error.ErrorMapper
import com.example.domain.DataMapper
import com.example.domain.DispatcherProvider
import com.example.domain.DomainResult
import kotlinx.coroutines.withContext
import kotlin.reflect.KFunction1


abstract class BaseRemoteDataSource(private val errorMapper: ErrorMapper, private val dispatcherProvider: DispatcherProvider) {

    protected suspend fun <T, R> getTransformedResult(call: ApiResult<T>, transformer: DataMapper<T, R>): DomainResult<R> {
        return call.toDomainResult(transformer::mapTo)
    }

    private suspend fun <T, R> ApiResult<T>.toDomainResult(transformer: KFunction1<@ParameterName(name = "data") T, R>): DomainResult<R> {
        return withContext(dispatcherProvider.computation) {
            mapEither(transformer, errorMapper::mapToDomainError)
        }
    }
}

