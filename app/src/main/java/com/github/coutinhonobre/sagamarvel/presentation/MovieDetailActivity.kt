package com.github.coutinhonobre.sagamarvel.presentation

import android.R.attr.data
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.github.coutinhonobre.sagamarvel.R
import com.github.coutinhonobre.sagamarvel.data.model.Movie
import com.github.coutinhonobre.sagamarvel.presentation.detail.DetailAdapter
import com.github.coutinhonobre.sagamarvel.presentation.movies.MovieViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.card_dados_basicos.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var moviesViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        moviesViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MovieViewModel::class.java)

        moviesViewModel.getMovieTitle(intent.getStringExtra(EXTRA_TITLE), intent.getIntExtra(EXTRA_ID, 0)).observe(this, Observer {
            if (it.size > 0){
                var movie = it[0]
                imageMovieDetailImage.contentDescription = movie.title
                imageButtonMovieDetailLike.setBackgroundResource(marcarFavorito(movie))
                textMovieDetailTitulo.text = "Title: ${movie.title}"
                textMovieDetailGenero.text =  "Genre: ${movie.genre}"
                textMovieDetailData.text = "Released: ${movie.released}"
                textMovieDetailYear.text = "Year: ${movie.year}"
                textMovieDetailRated.text = "Rated: ${movie.rated}"
                textMovieDetailRuntime.text = "Runtime: ${movie.runtime}"
                textMovieDetaildirector.text = "Director: ${movie.director}"
                textMovieDetailPlot.text = "Plot: ${movie.plot}"



                with(recyclerMovieDetailActors){
                    layoutManager = LinearLayoutManager(this@MovieDetailActivity)
                    adapter = DetailAdapter(movie.actors.split(",").toMutableList())
                }

                with(recyclerMovieDetailWriter){
                    layoutManager = LinearLayoutManager(this@MovieDetailActivity)
                    adapter = DetailAdapter(movie.writer.split(",").toMutableList())
                }

                imageMovieDetailImage.load(movie.poster) {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_foreground)
                    transformations()
                }

                imageButtonMovieDetailLike.setOnClickListener {
                    movie.like = !movie.like!!
                    imageButtonMovieDetailLike.setBackgroundResource(marcarFavorito(movie))
                    GlobalScope.launch {
                        moviesViewModel.update(movie)
                    }
                }
            }
        })

    }

    companion object {
        private const val EXTRA_ID = "EXTRA_ID"
        private const val EXTRA_TITLE = "EXTRA_TITLE"

        fun getStartIntent(context: Context, id: Int, title: String): Intent {
            return Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(EXTRA_ID, id)
                putExtra(EXTRA_TITLE, title)
            }
        }
    }

    private fun marcarFavorito(movie: Movie) =
        if (movie.like!!) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
}