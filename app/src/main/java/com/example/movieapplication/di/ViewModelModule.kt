package com.example.movieapplication.di

import com.example.data.movie.castcrewdetail.GetMovieCastCrewDataSource
import com.example.data.movie.moviedetail.GetMovieDetailDataSource
import com.example.domain.GetMovieCastCrewDetailUseCase
import com.example.domain.GetMovieCastCrewDetailUseCaseImpl
import com.example.domain.GetMovieDetailUseCase
import com.example.domain.GetMovieDetailUseCaseImpl
import com.example.domain.castcrewdetail.FetchMovieCastCrewDetailService
import com.example.domain.moviedetails.FetchMovieDetailService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ViewModelModule {

    @Binds
    fun providesMovieDetailUseCase(getMovieDetailUseCaseImpl : GetMovieDetailUseCaseImpl) : GetMovieDetailUseCase

    @Binds
    fun bindsMovieDetailService(service:GetMovieDetailDataSource) : FetchMovieDetailService

    @Binds
    fun bindsMovieCastCrewDetailService(service:GetMovieCastCrewDataSource) : FetchMovieCastCrewDetailService

    @Binds
    fun providesMovieCastCrewDetailUseCase(getMovieCastCrewDetailUseCaseImpl : GetMovieCastCrewDetailUseCaseImpl) : GetMovieCastCrewDetailUseCase

}