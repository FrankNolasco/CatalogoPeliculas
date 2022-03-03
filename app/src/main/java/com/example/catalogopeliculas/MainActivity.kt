package com.example.catalogopeliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.catalogopeliculas.clases.Pelicula
import android.content.Intent
import androidx.lifecycle.Observer
import com.example.catalogopeliculas.adapter.PeliculaAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var listaPeliculas = emptyList<Pelicula>()

        val dataBase = AppDataBase.getDatabase(this)

        dataBase.peliculas().getAll().observe(this, Observer {
            listaPeliculas = it
            val adapter = PeliculaAdapter(mContext = this,listaPeliculas)
            lista.adapter = adapter
        })

        floatingActionButton.setOnClickListener{
            val intent = Intent(this,NuevaPelicula::class.java)
            startActivity(intent)
        }

        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this,Detail::class.java)
            intent.putExtra("id",listaPeliculas[position].idPelicula)
            startActivity(intent)
        }
    }
}