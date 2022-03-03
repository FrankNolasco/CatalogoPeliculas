package com.example.catalogopeliculas.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.catalogopeliculas.clases.Pelicula

@Dao
interface PeliculaDAO {
    @Query(value = "Select * from pelicula")
    fun getAll(): LiveData<List<Pelicula>>
    @Query(value = "Select * from pelicula where idPelicula=:id")
    fun get(id:Int):LiveData<Pelicula>
    @Insert
    fun insertAll(vararg pelicula:Pelicula)
    @Update
    fun update(pelicula: Pelicula)
    @Delete
    fun delete(pelicula: Pelicula)
}