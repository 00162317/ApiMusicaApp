package com.example.apimusicaapp.adapter

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apimusicaapp.R
import com.example.apimusicaapp.model.Cancion
import com.example.apimusicaapp.utils.NetworkUtils
import com.example.apimusicaapp.utils.SongSerializer
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_adapter.*
import kotlinx.android.synthetic.main.activity_viewer.*
import org.json.JSONObject

class Viewer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewer)
    }

    fun initInfo(song:Cancion){
        nombre_viewer.text=song.name
        artista_viewer.text=song.artista
        anio_viewer.text=song.anio.toString()
        album_viewer.text=song.album
    }
/*
    inner class SongFetch : AsyncTask<Unit, Unit, List<Cancion>>() {
    }
*/}
