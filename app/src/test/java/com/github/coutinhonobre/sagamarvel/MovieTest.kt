package com.github.coutinhonobre.sagamarvel

import com.github.coutinhonobre.sagamarvel.data.model.Movie
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieTest {

    private lateinit var movie: Movie

    @Before
    fun inicializar() {
        movie = Movie(
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
            poster = "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX500.jpg"
        );
    }

    @Test
    fun checarFilmeFavoritoTest(){
        movie.like = true
        assertEquals(true, movie.isFavorite()!!)
    }

    @Test
    fun checarFilmeNaoFavoritoTest(){
        movie.like = false
        assertEquals(false, movie.isFavorite())
    }

    @Test
    fun checarFilmeNotaCincoTest(){
        movie.rate = 5
        assertEquals(5, movie.myRate()!!)
    }

    @Test
    fun checarFilmeSemNotaTest(){
        assertEquals(1, movie.myRate()!!)
    }


}