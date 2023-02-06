package com.example.data.movie.moviedetail

import com.example.data.MovieApiService
import com.example.data.BaseRemoteDataSource
import com.example.data.calladapter.error.ErrorMapper
import com.example.domain.DispatcherProvider
import com.example.domain.moviedetails.FetchMovieDetailService
import com.example.domain.moviedetails.MovieDetailData
import com.example.domain.resultOrError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

//Data Source to execute the corresponding API Service using retrofit Client and Dispatcher,
// along with Data Mapper to map the Response data into Local Data for UI consumption
class GetMovieDetailDataSource @Inject constructor(
    private val remoteService: MovieApiService,
    errorMapper: ErrorMapper,
    private val dispatcherProvider: DispatcherProvider,
    private val domainDataMapper: MovieDetailResponseMapper
    ) : BaseRemoteDataSource(errorMapper,dispatcherProvider), FetchMovieDetailService
{
    override suspend fun fetchMovieDetails(): Flow<MovieDetailData> {
        return flow {
            emit(
                getTransformedResult(
                    remoteService.getMovieDetails("0799fb14a63d4ffe0ff4496b6bd30cfe","en-US","1"),domainDataMapper
                )
            )
        }.flowOn(dispatcherProvider.io).resultOrError()
    }

}