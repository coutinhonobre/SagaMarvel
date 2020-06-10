package com.github.coutinhonobre.sagamarvel.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.coutinhonobre.sagamarvel.R
import com.github.coutinhonobre.sagamarvel.presentation.movies.MovieViewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var moviesViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        moviesViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MovieViewModel::class.java)

        moviesViewModel.getMovieTitle(intent.getStringExtra(EXTRA_TITLE), intent.getIntExtra(EXTRA_ID, 0)).observe(this, Observer {
            if (it.size > 0){
                Toast.makeText(this, it.get(0).toString(), Toast.LENGTH_LONG).show()
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
}