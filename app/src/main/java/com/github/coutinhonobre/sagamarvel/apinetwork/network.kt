package com.github.coutinhonobre.sagamarvel.apinetwork

import com.github.coutinhonobre.sagamarvel.data.model.Movie
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private const val BASE_URL = "https://private-b34167-rvmarvel.apiary-mock.com/"
private const val TYPE_HEADERS = "Content-Type: application/json"
/*
objeto Moshi que o Retrofit usará
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface ApiService{

    @Headers(TYPE_HEADERS)
    @GET("saga")
    fun getSaga(): Call<MutableList<Movie>>

}

object ApiRetrofit {
    val RETROFIT_SERVICE : ApiService by lazy { retrofit.create(ApiService::class.java) }
}
