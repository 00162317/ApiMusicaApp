package com.example.apimusicaapp.utils

import android.util.Log
import com.example.apimusicaapp.model.Cancion
import org.json.JSONArray
import org.json.JSONObject

class SongSerializer {
    companion object {

        fun parseSongs(songText: String): List<Cancion> {
            val songJSON = JSONArray(songText)
            Log.d("holi", songJSON.length().toString())
            return MutableList(songJSON.length()) {
                parseSong(songJSON[it].toString())
            }
            Log.d("holi", songJSON[1].toString())
        }

        fun parseSong(songText: String): Cancion {
            val songJSON = JSONObject(songText)

            return with(songJSON) {
                Cancion(getString("name"), getString("artista"), getInt("anio"), getString("album"))
            }
        }
    }
}