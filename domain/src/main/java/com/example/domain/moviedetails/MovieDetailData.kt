package com.example.domain.moviedetails

data class MovieDetailData(
    val adult:Boolean ?= false,
    var genres: ArrayList<Genres> = arrayListOf(),
    val title: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val tagline : String?= null,
    val vote:Double?=null,
    val voteCount:Int?=null,
    val spokenLanguages:ArrayList<String> = arrayListOf()
) {
    data class Genres(
        var id: Int? = null,
        var name: String? = null
    )
}