package com.example.catalogopeliculas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.net.toUri
import com.example.catalogopeliculas.R
import com.example.catalogopeliculas.clases.Pelicula
import kotlinx.android.synthetic.main.item_pelicula.view.*

class PeliculaAdapter(private val mContext: Context, private val listaPeliculas:List<Pelicula>): ArrayAdapter<Pelicula>(mContext,0,listaPeliculas){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout= LayoutInflater.from(mContext).inflate(R.layout.item_pelicula, parent, false)
        val tel=listaPeliculas[position]
        layout.ItemNombre.text = tel.nombre
        layout.ItemSinopsis.text = tel.sinopsis

        if (tel.imagen!=""){
            layout.ItemImagen.setImageURI(tel.imagen.toUri())
        }

        return layout
    }
}