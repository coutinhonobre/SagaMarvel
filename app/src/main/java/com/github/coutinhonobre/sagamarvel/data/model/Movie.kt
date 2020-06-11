package com.github.coutinhonobre.sagamarvel.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie")
@Parcelize
data class Movie(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "year")
    val year: Int,
    @ColumnInfo(name = "rated")
    val rated: String,
    @ColumnInfo(name = "released")
    val released: String,
    @ColumnInfo(name = "runtime")
    val runtime: String,
    @ColumnInfo(name = "genre")
    val genre: String,
    @ColumnInfo(name = "director")
    val director: String,
    @ColumnInfo(name = "writer")
    val writer: String,
    @ColumnInfo(name = "actors")
    val actors: String,
    @ColumnInfo(name = "plot")
    val plot: String,
    @ColumnInfo(name = "poster")
    val poster: String,
    @ColumnInfo(name = "like")
    var like: Boolean? = false,
    @ColumnInfo(name = "rate")
    var rate: Float? = 1F
) : Parcelable {

    fun isFavorite() = this.like

    fun myRate() = this.rate!!

    fun map(): MutableList<Generica> {
        var map = mutableListOf<Generica>()

        map.add(Generica("Title", this.title, true))
        map.add(Generica("Year", this.year.toString(), true))
        map.add(Generica("Rated", this.rated, true))
        map.add(Generica("Released", this.released, true))
        map.add(Generica("Runtime", this.runtime, true))
        map.add(Generica("Genre", this.genre, true))
        map.add(Generica("Director", this.director, true))

        return map
    }

}