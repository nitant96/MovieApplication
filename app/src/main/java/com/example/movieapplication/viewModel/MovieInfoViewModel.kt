package com.example.movieapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetMovieCastCrewDetailUseCase
import com.example.domain.GetMovieDetailUseCase
import com.example.domain.castcrewdetail.MovieCastCrewDetailData
import com.example.domain.moviedetails.MovieDetailData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
//ViewModel uses Hilt DI to inject Usecases for calling and getting the data through API.
@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val movieCastCrewDetailUseCase: GetMovieCastCrewDetailUseCase
) : ViewModel()
{

    //Movie cast/crew list (Initialized empty to avoid further error scope)
    var movieCastList : ArrayList<MovieCastCrewDetailData.Cast> ?= null
    var movieCrewList : ArrayList<MovieCastCrewDetailData.Crew> ?= null

    //used to fetch Movie data and emit it through SharedFlow.
    private val  _movieDetailData = MutableSharedFlow<MovieDetailData>()
    val movieDetailData = _movieDetailData.asSharedFlow()
    fun getMovieDetails(){
        viewModelScope.launch {
            movieDetailUseCase.execute(Unit).catch {  }.collect{
                //After receiving data, push it to the observer
                _movieDetailData.emit(it)
            }
        }
    }


    //used to fetch Movie's cast/Crew data and emit it through SharedFlow.
    private val  _movieCastCrewDetailData = MutableSharedFlow<MovieCastCrewDetailData>()
    val movieCastCrewDetailData = _movieCastCrewDetailData.asSharedFlow()
    fun getMovieCastCrewDetails(){
        viewModelScope.launch {
            movieCastCrewDetailUseCase.execute(Unit).catch {  }.collect{
                //After receiving data, push it to the observer
                if(it.cast.isNotEmpty()){
                    movieCastList= it.cast
                }
                if(it.crew.isNotEmpty()){
                    movieCrewList=it.crew
                }
                _movieCastCrewDetailData.emit(it)
            }
        }
    }


}