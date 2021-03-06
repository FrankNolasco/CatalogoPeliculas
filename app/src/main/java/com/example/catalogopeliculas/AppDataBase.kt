package com.example.catalogopeliculas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.catalogopeliculas.clases.Pelicula
import com.example.catalogopeliculas.dao.PeliculaDAO

@Database(entities = [Pelicula::class], version = 1)
abstract class AppDataBase:RoomDatabase() {
    abstract fun peliculas():PeliculaDAO
    companion object{
        @Volatile
        private var INSTANCE: AppDataBase?=null

        fun getDatabase(context: Context):AppDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(lock =this ){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}