package com.example.domain.moviedetails

import com.example.domain.moviedetails.MovieDetailData
import kotlinx.coroutines.flow.Flow

interface FetchMovieDetailService {
    suspend fun fetchMovieDetails() : Flow<MovieDetailData>
}