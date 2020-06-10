package com.github.coutinhonobre.sagamarvel.presentation

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.github.coutinhonobre.sagamarvel.R
import com.github.coutinhonobre.sagamarvel.data.model.Generica
import com.github.coutinhonobre.sagamarvel.data.model.Movie
import com.github.coutinhonobre.sagamarvel.presentation.detail.GenericAdapter
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


                var listaGenerica = movie.map()
                listaGenerica.add(Generica("Actors", "", true))

                atores(movie, listaGenerica)

                listaGenerica.add(Generica("Writer", "", true))

                escritores(movie, listaGenerica)

                with(recyclerMovieDetailDados){
                    layoutManager = LinearLayoutManager(this@MovieDetailActivity)
                    adapter = GenericAdapter(listaGenerica)
                }


                imageMovieDetailImage.load(movie.poster) {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_foreground)
                    transformations()
                    imageMovieDetailImage.scaleType = ImageView.ScaleType.FIT_CENTER
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

    private fun escritores(
        movie: Movie,
        listaGenerica: MutableList<Generica>
    ) {
        var writeList = mutableListOf<Generica>()
        movie.writer.split(",").toMutableList().forEach {
            writeList.add(Generica(it, ""))
        }

        if (writeList.size > 0)
            listaGenerica.addAll(writeList)
    }

    private fun atores(
        movie: Movie,
        listaGenerica: MutableList<Generica>
    ) {
        var actorList = mutableListOf<Generica>()
        movie.actors.split(",").toMutableList().forEach {
            actorList.add(Generica(it, ""))
        }

        if (actorList.size > 0)
            listaGenerica.addAll(actorList)
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