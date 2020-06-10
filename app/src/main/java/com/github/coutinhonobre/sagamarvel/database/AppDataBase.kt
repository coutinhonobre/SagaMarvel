package com.github.coutinhonobre.sagamarvel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.coutinhonobre.sagamarvel.data.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDataBase(): RoomDatabase() {

    abstract fun Dao(): AppDao

    companion object {

        var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "database.db"
                ).build()

                INSTANCE as AppDataBase
            }else{
                INSTANCE as AppDataBase
            }
        }

    }

}
