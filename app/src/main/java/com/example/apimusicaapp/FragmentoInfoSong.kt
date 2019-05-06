package com.example.apimusicaapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apimusicaapp.model.Cancion
import kotlinx.android.synthetic.main.fragment_fragmento_info_song.*


class FragmentoInfoSong : Fragment(){


    fun initInfo(song:Cancion){
        nombre_viewer.text=song.name
        artista_viewer.text=song.artista
        anio_viewer.text=song.anio.toString()
        album_viewer.text=song.album
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_info_song, container, false)
    }

    interface OnFragmentInteractionListener {
        fun landscape(song:Cancion)
    }
    var listener:OnFragmentInteractionListener?=null

}
