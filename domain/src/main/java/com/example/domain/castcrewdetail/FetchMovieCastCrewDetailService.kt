package com.example.domain.castcrewdetail

import kotlinx.coroutines.flow.Flow

interface FetchMovieCastCrewDetailService {
    suspend fun fetchMovieCastCrewDetails() : Flow<MovieCastCrewDetailData>
}
