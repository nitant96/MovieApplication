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

@HiltViewModel
class MovieInfoViewModel @Inject constructor(
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val movieCastCrewDetailUseCase: GetMovieCastCrewDetailUseCase
) : ViewModel()
{
    init {
        getMovieDetails()
        getMovieCastCrewDetails()
    }

    private val  _movieDetailData = MutableSharedFlow<MovieDetailData>()
    val movieDetailData = _movieDetailData.asSharedFlow()
    private fun getMovieDetails(){
        viewModelScope.launch {
            movieDetailUseCase.execute(Unit).catch {  }.collect{
                //After receiving data, push it to the observer
                _movieDetailData.emit(it)
            }
        }
    }

    private val  _movieCastCrewDetailData = MutableSharedFlow<MovieCastCrewDetailData>()
    val movieCastCrewDetailData = _movieCastCrewDetailData.asSharedFlow()
    private fun getMovieCastCrewDetails(){
        viewModelScope.launch {
            movieCastCrewDetailUseCase.execute(Unit).catch {  }.collect{
                //After receiving data, push it to the observer
                _movieCastCrewDetailData.emit(it)
            }
        }
    }


}