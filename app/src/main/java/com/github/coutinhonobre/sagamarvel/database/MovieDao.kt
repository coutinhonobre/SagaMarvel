package com.github.coutinhonobre.sagamarvel.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.coutinhonobre.sagamarvel.data.model.Movie

@Dao
interface MovieDao {

    @Query("select * from movie order by id desc")
    fun getAllMovies(): LiveData<MutableList<Movie>>

    @Query("select * from movie where title = :title")
    fun getUserExists(title: String): MutableList<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)


}
