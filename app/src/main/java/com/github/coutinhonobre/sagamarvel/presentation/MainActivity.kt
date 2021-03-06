package com.github.coutinhonobre.sagamarvel.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
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

    private var myMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreference = getSharedPreferences("FILTRO", Context.MODE_PRIVATE)


        desc = sharedPreference.getBoolean("melhor", false)

        moviesViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(MovieViewModel::class.java)

        moviesViewModel.buscarMovies()

        moviesViewModel.mensagem.observe(this, Observer {
            if (it.tipo == TipoMensagem.ERROR) {
                if (movieList.size == 0) {
                    visualizarFlipper(2)
                    textViewMainMoviesError.text = it.descricao
                }else{
                    swipeRefreshMainMovies.isRefreshing = false
                    Toast.makeText(this, it.descricao, Toast.LENGTH_LONG).show()
                }
                moviesViewModel.getMoviesBD(desc)
            } else if (it.tipo == TipoMensagem.SUCCESS) {
                swipeRefreshMainMovies.isRefreshing = false
            }
        })

        moviesViewModel.getMoviesBD(desc).observe(this, Observer {
            if (it.size > 0) visualizarFlipper(1)
            if (it.size != movieList.size) {
                movieList = it
                recyclerView()
            }
        })

        swipeRefreshMainMovies.setOnRefreshListener {
            moviesViewModel.buscarMovies()
        }

    }

    private fun salvarPreferencia(value: Boolean) {
        var editor = sharedPreference.edit()
        editor.putBoolean("melhor", value)
        editor.commit()
        desc = value
        ocultarMenu(myMenu)

        moviesViewModel.getMoviesBD(desc).observe(this, Observer {
            if (it.size > 0) visualizarFlipper(1)
            movieList = it
            recyclerView()

        })
    }

    private fun visualizarFlipper(displayedChild: Int) {
        viewFlipperMainMovies.displayedChild = displayedChild
        swipeRefreshMainMovies.isRefreshing = false
    }

    private fun recyclerView() {
        with(recyclerViewMainMovies) {
            layoutManager =
                GridLayoutManager(this@MainActivity, 2)
            adapter = MovieAdapter(movieList, moviesViewModel) { movie ->
                val intent =
                    movie.id?.let {
                        MovieDetailActivity.getStartIntent(
                            this@MainActivity,
                            it, movie.title
                        )
                    }
                this@MainActivity.startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        myMenu = menu

        ocultarMenu(menu)

        return true
    }

    private fun ocultarMenu(menu: Menu?) {
        if (desc) {
            menu!!.findItem(R.id.menu_main_maior).isVisible = false
            menu!!.findItem(R.id.menu_main_menor).isVisible = true
        } else {
            menu!!.findItem(R.id.menu_main_maior).isVisible = true
            menu!!.findItem(R.id.menu_main_menor).isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_main_maior -> {
                salvarPreferencia(true)
                true
            }
            R.id.menu_main_menor -> {
                salvarPreferencia(false)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}