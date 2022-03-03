package com.example.catalogopeliculas.clases

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Pelicula")
class Pelicula(
    val nombre:String,
    val imagen: String,
    val productor:String,
    val duracion:String,
    val director:String,
    val genero:String,
    val sinopsis:String,
    @PrimaryKey(autoGenerate = true)
    var idPelicula:Int =0,):Serializable

