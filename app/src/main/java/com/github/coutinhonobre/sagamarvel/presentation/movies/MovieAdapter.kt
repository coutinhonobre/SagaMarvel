package com.github.coutinhonobre.sagamarvel.presentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.coutinhonobre.sagamarvel.R
import com.github.coutinhonobre.sagamarvel.data.model.Movie
import kotlinx.android.synthetic.main.card_movies.view.*

class MovieAdapter(var movieList: MutableList<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(movie: Movie){
            itemView.textCardMoviesTitulo.setText(movie.title)
            itemView.textCardMoviesGenero.setText(movie.genre)
            itemView.textCardMoviesData.setText(movie.released)
            itemView.imageButtonCardMoviesLike.isEnabled = movie.like!!
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_movies, parent, false)
    )

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindView(movieList[position])
    }
}