package com.github.coutinhonobre.sagamarvel.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.coutinhonobre.sagamarvel.R
import com.github.coutinhonobre.sagamarvel.data.model.Movie
import com.github.coutinhonobre.sagamarvel.presentation.movies.MovieAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var movieList: MutableList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieList = mutableListOf()
        for (x in 1..50){
            movieList.add(
                Movie(
                    id = x,
                    title = "Iron Man",
                    year = 2008,
                    rated = "PG-13",
                    released = "02 May 2008",
                    runtime = "126 min",
                    genre = "Action, Adventure, Sci-Fi",
                    director = "Jon Favreau",
                    writer = "Mark Fergus (screenplay), Hawk Ostby (screenplay), Art Marcum (screenplay), Matt Holloway (screenplay), Stan Lee (characters), Don Heck (characters), Larry Lieber (characters), Jack Kirby (characters)",
                    actors = "Robert Downey Jr., Terrence Howard, Jeff Bridges, Gwyneth Paltrow",
                    plot = "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.",
                    poster = "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX500.jpg",
                    like = x % 2 == 0,
                    rate = x
                )
            )
        }

        recyclerView()

    }

    private fun recyclerView() {
        recyclerViewMainMovies.layoutManager =
            GridLayoutManager(this, 2);
        recyclerViewMainMovies.adapter = MovieAdapter(movieList)
    }
}