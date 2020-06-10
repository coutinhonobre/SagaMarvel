package com.github.coutinhonobre.sagamarvel.presentation.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.GrayscaleTransformation
import com.github.coutinhonobre.sagamarvel.R
import com.github.coutinhonobre.sagamarvel.data.model.Movie
import kotlinx.android.synthetic.main.card_movies.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieAdapter(var movieList: MutableList<Movie>,
                   var moviesViewModel: MovieViewModel,
                   private val onItemClickListener: ((movie: Movie) -> Unit)): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View, private val onItemClickListener: ((movie: Movie) -> Unit)) : RecyclerView.ViewHolder(itemView) {
        fun bindView(movie: Movie, moviesViewModel: MovieViewModel){
            itemView.textCardMoviesTitulo.text = movie.title
            itemView.textCardMoviesGenero.text = movie.genre
            itemView.textCardMoviesData.text = movie.released
            itemView.imageButtonCardMoviesLike.setBackgroundResource(marcarFavorito(movie))
            itemView.imageCardMoviesMiniatura.contentDescription = movie.title

            itemView.imageCardMoviesMiniatura.load(movie.poster) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
            }

            itemView.imageButtonCardMoviesLike.setOnClickListener {
                movie.like = !movie.like!!
                itemView.imageButtonCardMoviesLike.setBackgroundResource(marcarFavorito(movie))
                GlobalScope.launch {
                    moviesViewModel.update(movie)
                }
            }

            itemView.setOnClickListener{
                onItemClickListener.invoke(movie)
            }
        }

        private fun marcarFavorito(movie: Movie) =
            if (movie.like!!) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_movies, parent, false), onItemClickListener
    )

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(movieList[position], this.moviesViewModel)
    }
}