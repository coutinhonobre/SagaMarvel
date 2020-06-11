package com.github.coutinhonobre.sagamarvel.presentation

import android.content.Context
import android.content.SharedPreferences
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

    private lateinit var sharedPreference: SharedPreferences

    private var movieList: MutableList<Movie> = mutableListOf()

    private var desc = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreference =  getSharedPreferences("FILTRO", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putBoolean("melhor",true)
        editor.commit()

       desc = sharedPreference.getBoolean("melhor",false)

       moviesViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MovieViewModel::class.java)

       moviesViewModel.buscarMovies()

       moviesViewModel.mensagem.observe(this, Observer {
           if (it.tipo == TipoMensagem.ERROR){
               visualizarFlipper(2)
               textViewMainMoviesError.text = it.descricao
               moviesViewModel.getMoviesBD(desc)
           }else if (it.tipo == TipoMensagem.SUCCESS){
               swipeRefreshMainMovies.isRefreshing = false
           }
       })

        moviesViewModel.getMoviesBD(desc).observe(this, Observer {
            if(it.size > 0) visualizarFlipper(1)
            if (it.size != movieList.size){
                movieList = it
                recyclerView()
            }
        })

        swipeRefreshMainMovies.setOnRefreshListener {
            moviesViewModel.buscarMovies()
        }

    }

    private fun visualizarFlipper(displayedChild: Int) {
        viewFlipperMainMovies.displayedChild = displayedChild
        swipeRefreshMainMovies.isRefreshing = false
    }

    private fun recyclerView() {
        with(recyclerViewMainMovies){
            layoutManager =
                GridLayoutManager(this@MainActivity, 2)
            adapter = MovieAdapter(movieList, moviesViewModel) { movie ->
                val intent =
                    movie.id?.let {
                        MovieDetailActivity.getStartIntent(this@MainActivity,
                            it, movie.title)
                    }
                this@MainActivity.startActivity(intent)
            }
        }
    }
}