package com.github.coutinhonobre.sagamarvel.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.github.coutinhonobre.sagamarvel.apinetwork.ApiRetrofit
import com.github.coutinhonobre.sagamarvel.data.model.Mensagem
import com.github.coutinhonobre.sagamarvel.data.model.Movie
import com.github.coutinhonobre.sagamarvel.data.model.TipoMensagem
import com.github.coutinhonobre.sagamarvel.database.AppDataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository(val context: Context) {

    private val database = AppDataBase.getInstance(context)

    var mensagem = MutableLiveData<Mensagem>(Mensagem(tipo = TipoMensagem.NOT, descricao = ""))

    private var getUserAccount = ApiRetrofit.RETROFIT_SERVICE

    fun getMovies() = database.Dao().getAllMovies()

    fun getMoviesExists(title: String) = database.Dao().getMoviesExists(title)

    fun getMovies(title: String, id: Int) = database.Dao().getMovies(title, id)

    fun update(movie: Movie) = database.Dao().updateMovie(movie)

    fun fetchDataFromServerMovies(){
        getUserAccount.getSaga().enqueue(object : Callback<MutableList<Movie>>{
            override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {
                mensagem.apply {
                    value = Mensagem(TipoMensagem.ERROR, "Requisicao Falhou!")
                }
            }

            override fun onResponse(
                call: Call<MutableList<Movie>>,
                response: Response<MutableList<Movie>>
            ) {
                if (response.isSuccessful){
                    mensagem.apply {
                        value = Mensagem(TipoMensagem.SUCCESS, "${response.code()} - ${response.body()}")
                    }
                    var movies = response.body()!!

                    GlobalScope.launch {
                        adicaoAllMovies(movies)
                    }

                }else{
                    mensagem.apply {
                        value = Mensagem(TipoMensagem.ERROR, "${response.code()} - ${response.errorBody()}")
                    }
                }
            }

        })
    }

    private fun adicaoAllMovies(movies: MutableList<Movie>) {
        movies.forEach {
            if (database.Dao().getMoviesExists(it.title).size == 0) database.Dao().addMovie(it)
            else database.Dao().updateMovie(it)
        }
    }


}