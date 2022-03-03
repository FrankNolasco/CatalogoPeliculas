package com.example.catalogopeliculas
import android.net.Uri
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.catalogopeliculas.clases.Pelicula
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_nueva_pelicula.*
import kotlinx.android.synthetic.main.item_pelicula.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Detail : AppCompatActivity() {
    private lateinit var dataBase: AppDataBase

    private lateinit var pelicula: Pelicula
    private lateinit var peliculaLiveData: LiveData<Pelicula>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        dataBase = AppDataBase.getDatabase(this)

        val idAuto = intent.getIntExtra("id",0)

        peliculaLiveData = dataBase.peliculas().get(idAuto)
        peliculaLiveData.observe(this, Observer{
            pelicula = it
            Nombre.text = pelicula.nombre
            Duracion.text = pelicula.duracion
            Productor.text = pelicula.productor
            Genero.text = pelicula.genero
            Director.text = pelicula.director
            Sinopsis.text = pelicula.sinopsis
            if (pelicula.imagen!=""){
                Imagen.setImageURI(pelicula.imagen.toUri())
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.edit_item->{
                val intent = Intent(this, NuevaPelicula::class.java)
                intent.putExtra("Pelicula",pelicula)
                startActivity(intent)
            }

            R.id.delete_item->{
                peliculaLiveData.removeObservers(this)
                CoroutineScope(Dispatchers.IO).launch {
                    dataBase.peliculas().delete(pelicula)
                    this@Detail.finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}