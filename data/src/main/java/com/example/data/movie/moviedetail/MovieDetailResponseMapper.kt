package com.example.data.movie.moviedetail

import com.example.domain.DataMapper
import com.example.domain.moviedetails.MovieDetailData
import javax.inject.Inject

class MovieDetailResponseMapper @Inject constructor() : DataMapper<MovieDetailResponse,MovieDetailData>
{
    override fun mapTo(data: MovieDetailResponse): MovieDetailData {
        val listOfGenres = arrayListOf<MovieDetailData.Genres>()

        for(item in data.genres){
           listOfGenres.add(MovieDetailData.Genres(
               id = item.id,
               name = item.name
           ))
        }

        val languageList = arrayListOf<String>()

        for(currentLanguage in data.spokenLanguages){
            currentLanguage.name?.let {
                languageList.add(it)
            }
        }

        return MovieDetailData(
            data.adult,
            title = data.title,
            overview = data.overview,
            posterPath = data.posterPath,
            backdropPath = data.backdropPath,
            tagline = data.tagline,
            vote = data.voteAverage,
            voteCount = data.voteCount,
            genres = listOfGenres,
            spokenLanguages = languageList
        )
    }
}