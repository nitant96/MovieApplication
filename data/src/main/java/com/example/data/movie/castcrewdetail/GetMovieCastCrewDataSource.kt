package com.example.data.movie.castcrewdetail

import com.example.data.BaseRemoteDataSource
import com.example.data.MovieApiService
import com.example.data.calladapter.error.ErrorMapper
import com.example.data.movie.moviedetail.MovieDetailResponseMapper
import com.example.domain.DispatcherProvider
import com.example.domain.castcrewdetail.FetchMovieCastCrewDetailService
import com.example.domain.castcrewdetail.MovieCastCrewDetailData
import com.example.domain.moviedetails.FetchMovieDetailService
import com.example.domain.resultOrError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieCastCrewDataSource @Inject constructor(
    private val remoteService: MovieApiService,
    errorMapper: ErrorMapper,
    private val dispatcherProvider: DispatcherProvider,
    private val domainDataMapper: MovieCastCrewResponseMapper
) : BaseRemoteDataSource(errorMapper,dispatcherProvider), FetchMovieCastCrewDetailService
{
    override suspend fun fetchMovieCastCrewDetails(): Flow<MovieCastCrewDetailData> {
        return flow {
            emit(
                getTransformedResult(
                    remoteService.getMovieCastCrewDetails("0799fb14a63d4ffe0ff4496b6bd30cfe","en-US"),domainDataMapper
                )
            )
        }.flowOn(dispatcherProvider.io).resultOrError()
    }

}