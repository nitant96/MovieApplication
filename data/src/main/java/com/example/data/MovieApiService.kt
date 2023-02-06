package com.example.data

import com.example.data.movie.castcrewdetail.MovieCastCrewDetailResponse
import com.example.data.movie.moviedetail.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("3/movie/453395")
    suspend fun getMovieDetails(@Query("api_key") apiKey : String,@Query("language") language:String,@Query("page") page:String) : ApiResult<MovieDetailResponse>

    @GET("3/movie/453395/credits")
    suspend fun getMovieCastCrewDetails(@Query("api_key") apiKey : String,@Query("language") language:String) : ApiResult<MovieCastCrewDetailResponse>
}