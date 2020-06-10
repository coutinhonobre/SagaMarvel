package com.github.coutinhonobre.sagamarvel.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.coutinhonobre.sagamarvel.R

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)


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