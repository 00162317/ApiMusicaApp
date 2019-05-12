package com.example.apimusicaapp

import android.content.ContentValues
import android.content.Intent
import android.content.res.Configuration
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.apimusicaapp.Fragmento.FragmentoInfoSong
import com.example.apimusicaapp.adapter.Adapter
import com.example.apimusicaapp.adapter.Viewer
import com.example.apimusicaapp.data.Database
import com.example.apimusicaapp.data.DatabaseContract
import com.example.apimusicaapp.model.Cancion
import com.example.apimusicaapp.utils.NetworkUtils
import com.example.apimusicaapp.utils.SongSerializer
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), FragmentoInfoSong.OnFragmentInteractionListener {
    var dbHelper=Database(this)
    override fun landscape(song: Cancion) {
        var fragmento= FragmentoInfoSong()
        supportFragmentManager.beginTransaction().replace(R.id.land_segundo,fragmento).commit()
    }

    lateinit var viewAdapter: Adapter
    lateinit var viewManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)

        viewAdapter = Adapter(listOf<Cancion>(),{variable:Cancion->itemClick(variable)})

        recyclerXML.apply {
            adapter = viewAdapter
            layoutManager = viewManager
        }
        SongFetch().execute()

        if(resources.configuration.orientation==Configuration.ORIENTATION_LANDSCAPE){
            supportFragmentManager.beginTransaction().replace(R.id.land_segundo,
                FragmentoInfoSong()
            ).commit()
        }

    }

    fun itemClick(item:Cancion){
        startActivity(Intent(this, Viewer::class.java).putExtra("holi",item))
    }
    inner class SongFetch : AsyncTask<Unit, Unit, List<Cancion>>() {
        override fun doInBackground(vararg params: Unit?): List<Cancion> {
            val url = NetworkUtils.buildURL()
            val resultString = NetworkUtils.getHTTPResult(url)

            val resultJSON = JSONObject(resultString)

            return if (resultJSON.getBoolean("ok")) {
                SongSerializer.parseSongs(
                    resultJSON.getJSONArray("parametro").toString()
                )
            } else {
                listOf<Cancion>()
            }
        }
        override fun onPostExecute(result: List<Cancion>?) {
            listCancion(result)
        }
    }
    fun listCancion(respuesta:List<Cancion>?){
        if (respuesta != null) {
            /*val cancionLista = */if (respuesta.isNotEmpty()) {
                viewAdapter.setData(respuesta)
            } else {
                Log.d("Holi", "No se pudo encontrar los datos")
            }
            meterBaseDatos(respuesta)
        }
    }
    private fun meterBaseDatos(lista:List<Cancion>){
        val db = dbHelper.writableDatabase
        dbHelper.check(db)
        for(i in 0..7){
            val values=ContentValues().apply {
                put(DatabaseContract.CancionEntry.COLUMN_NAME,lista[i].name)
                put(DatabaseContract.CancionEntry.COLUMN_ARTISTA,lista[i].artista)
                put(DatabaseContract.CancionEntry.COLUMN_ANIO,lista[i].anio)
                put(DatabaseContract.CancionEntry.COLUMN_ALBUM,lista[i].album)
            }
            val newRowId=db?.insert(DatabaseContract.CancionEntry.TABLE_NAME,null,values)
            if (newRowId == -1L) {
                Log.d("Hola","NO SE HA GUARDADO")
            } else {
                //Toast.makeText(this, "Guardado con exito", Toast.LENGTH_SHORT).show()
                Log.d("Hola", "Guardado con exito")
            }
        }
    }
    /*
    private fun leerBaseDeDatos(cadena:String,numero:Int):List<Cancion>{
        val db = dbHelper.readableDatabase
        val projection= arrayOf(BaseColumns._ID,
            DatabaseContract.CancionEntry.COLUMN_NAME,
            DatabaseContract.CancionEntry.COLUMN_ARTISTA,
            DatabaseContract.CancionEntry.COLUMN_ANIO,
            DatabaseContract.CancionEntry.COLUMN_ALBUM)
        var lista = List<Cancion>()
    }*/
}
