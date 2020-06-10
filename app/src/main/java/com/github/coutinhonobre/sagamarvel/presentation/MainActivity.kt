package com.github.coutinhonobre.sagamarvel.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.coutinhonobre.sagamarvel.R
import com.github.coutinhonobre.sagamarvel.data.model.Movie
import com.github.coutinhonobre.sagamarvel.data.model.TipoMensagem
import com.github.coutinhonobre.sagamarvel.presentation.movies.MovieAdapter
import com.github.coutinhonobre.sagamarvel.presentation.movies.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var moviesViewModel: MovieViewModel

    private lateinit var movieList: MutableList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       moviesViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MovieViewModel::class.java)

       moviesViewModel.buscarMovies()

       moviesViewModel.mensagem.observe(this, Observer {
           if (it.tipo == TipoMensagem.ERROR){
               viewFlipperMainMovies.displayedChild = 2
               swipeRefreshMainMovies.isRefreshing = false
               textViewMainMoviesError.text = it.descricao
           }else if (it.tipo == TipoMensagem.SUCCESS){
               swipeRefreshMainMovies.isRefreshing = false
           }
       })

        moviesViewModel.getMoviesBD().observe(this, Observer {
            viewFlipperMainMovies.displayedChild = 1
            swipeRefreshMainMovies.isRefreshing = false
            movieList = it
            recyclerView()
        })

        swipeRefreshMainMovies.setOnRefreshListener {
            moviesViewModel.buscarMovies()
        }

    }

    private fun recyclerView() {
        recyclerViewMainMovies.layoutManager =
            GridLayoutManager(this, 2);
        var moviesAdapter = MovieAdapter(movieList, moviesViewModel)
        recyclerViewMainMovies.adapter = moviesAdapter
        moviesAdapter.notifyDataSetChanged()
    }
}