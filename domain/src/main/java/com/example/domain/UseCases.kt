package com.example.domain

import com.example.domain.castcrewdetail.FetchMovieCastCrewDetailService
import com.example.domain.castcrewdetail.MovieCastCrewDetailData
import com.example.domain.moviedetails.FetchMovieDetailService
import com.example.domain.moviedetails.MovieDetailData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMovieDetailUseCase : UseCase<Unit, Flow<MovieDetailData>>
class GetMovieDetailUseCaseImpl @Inject constructor(
    private val fetchMovieDetailService: FetchMovieDetailService
) : GetMovieDetailUseCase {
    override suspend fun execute(params: Unit): Flow<MovieDetailData> {
        return fetchMovieDetailService.fetchMovieDetails()
    }
}

interface GetMovieCastCrewDetailUseCase : UseCase<Unit, Flow<MovieCastCrewDetailData>>
class GetMovieCastCrewDetailUseCaseImpl @Inject constructor(
    private val fetchMovieDetailCastCrewService: FetchMovieCastCrewDetailService
) : GetMovieCastCrewDetailUseCase {
    override suspend fun execute(params: Unit): Flow<MovieCastCrewDetailData> {
        return fetchMovieDetailCastCrewService.fetchMovieCastCrewDetails()
    }
}