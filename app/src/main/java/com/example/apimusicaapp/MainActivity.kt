package com.example.apimusicaapp

import android.content.Intent
import android.content.res.Configuration
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.apimusicaapp.adapter.Adapter
import com.example.apimusicaapp.adapter.Viewer
import com.example.apimusicaapp.model.Cancion
import com.example.apimusicaapp.utils.NetworkUtils
import com.example.apimusicaapp.utils.SongSerializer
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(),FragmentoInfoSong.OnFragmentInteractionListener {
    override fun landscape(song: Cancion) {
        var fragmento=FragmentoInfoSong()
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
            supportFragmentManager.beginTransaction().replace(R.id.land_segundo,FragmentoInfoSong()).commit()
        }

    }
    fun itemClick(item:Cancion){
        startActivity(Intent(this, Viewer::class.java))
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
            if (result != null) {
                if (result.isNotEmpty()) {
                    viewAdapter.setData(result)
                } else {
                    Log.d("Holi", "No se pudo encontrar los datos")
                }
            }
        }
    }
}
