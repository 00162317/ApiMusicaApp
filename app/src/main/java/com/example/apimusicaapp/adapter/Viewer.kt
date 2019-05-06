package com.example.apimusicaapp.adapter

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.apimusicaapp.FragmentoInfoSong
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
        val fragmento = FragmentoInfoSong()
        supportFragmentManager.beginTransaction().replace(R.id.land_segundo,fragmento).commit()
    }

}
