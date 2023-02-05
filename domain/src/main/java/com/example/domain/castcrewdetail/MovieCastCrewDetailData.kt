package com.example.domain.castcrewdetail


data class MovieCastCrewDetailData(
    val id:Int ?= null,
    var cast: ArrayList<Cast> = arrayListOf(),
    val crew: ArrayList<Crew> = arrayListOf(),
) {
    data class Cast(
        val name:String?=null,
        val character:String?=null,
        val imagePath:String?=null
    )

    data class Crew(
        val name:String?=null,
        val job:String?=null,
        val imagePath:String?=null
    )
}
