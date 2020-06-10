package com.github.coutinhonobre.sagamarvel.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.github.coutinhonobre.sagamarvel.data.model.Movie

@Dao
interface MovieDao {

    @Query("select * from movie order by id desc")
    fun getAllMovies(): LiveData<MutableList<Movie>>

    @Query("select * from movie where title = :title")
    fun getMoviesExists(title: String): MutableList<Movie>

    @Query("select * from movie where title = :title and id = :id")
    fun getMovies(title: String, id: Int): LiveData<MutableList<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movies: MutableList<Movie>)

    @Update
    fun updateMovie(movie: Movie)



}
