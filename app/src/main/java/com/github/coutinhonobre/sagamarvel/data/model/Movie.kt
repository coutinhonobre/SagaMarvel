package com.github.coutinhonobre.sagamarvel.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val title : String,
    val year : Int,
    val rated : String,
    val released : String,
    val runtime : String,
    val genre : String,
    val director : String,
    val writer : String,
    val actors : String,
    val plot : String,
    val poster : String,
    var like: Boolean? = false
) : Parcelable {

    fun isFavorite()  = this.like

}