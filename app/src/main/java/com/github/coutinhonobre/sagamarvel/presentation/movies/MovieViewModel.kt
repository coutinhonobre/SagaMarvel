package com.github.coutinhonobre.sagamarvel.presentation.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.github.coutinhonobre.sagamarvel.data.model.Movie
import com.github.coutinhonobre.sagamarvel.repository.AppRepository

class MovieViewModel(application: Application): AndroidViewModel(application) {

    private val appRepository = AppRepository(application)

    var mensagem = appRepository.mensagem

    fun buscarMovies() = appRepository.fetchDataFromServerMovies()

    fun getMoviesBD() = appRepository.getMovies()

    fun getMovieTitle(title: String) = appRepository.getUserExists(title)

    fun update(movie: Movie) = appRepository.update(movie)

}