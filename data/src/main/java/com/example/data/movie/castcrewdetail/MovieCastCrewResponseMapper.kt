package com.example.data.movie.castcrewdetail

import com.example.domain.DataMapper
import com.example.domain.castcrewdetail.MovieCastCrewDetailData
import javax.inject.Inject

class MovieCastCrewResponseMapper @Inject constructor() : DataMapper<MovieCastCrewDetailResponse, MovieCastCrewDetailData>
{
    override fun mapTo(data: MovieCastCrewDetailResponse): MovieCastCrewDetailData {
        val listOfCast = arrayListOf<MovieCastCrewDetailData.Cast>()

        for(item in data.cast){
            listOfCast.add(
                MovieCastCrewDetailData.Cast(
                    name = item.name,
                    character = item.character,
                    imagePath = item.profilePath
                )
            )
        }

        val crewList = arrayListOf<MovieCastCrewDetailData.Crew>()

        for(item in data.crew){
           crewList.add(
               MovieCastCrewDetailData.Crew(
                   name = item.name,
                   job = item.job,
                   imagePath = item.profilePath
               )
           )
        }

        return MovieCastCrewDetailData(
            data.id,
            cast = listOfCast,
            crew = crewList
        )
    }
}