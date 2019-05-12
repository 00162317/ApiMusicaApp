package com.example.apimusicaapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apimusicaapp.model.Cancion
import kotlinx.android.synthetic.main.fragment_fragmento_info_song.*
import kotlinx.android.synthetic.main.fragment_fragmento_info_song.view.*


class FragmentoInfoSong : Fragment(){

    companion object {
        fun newInstance(item : List<Cancion>):FragmentoInfoSong{
            var frag  = FragmentoInfoSong()
            return frag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var ejemplo = arguments?.getParcelable<Cancion>("holi")

        var view= inflater.inflate(R.layout.fragment_fragmento_info_song, container, false)

        if (ejemplo != null) {
            init(ejemplo,view)
        }

        return view

    }
    fun init(ejemplo:Cancion,view:View){
        view.nombre_viewer.text=ejemplo?.name
        view.artista_viewer.text=ejemplo?.artista
        view.anio_viewer.text=ejemplo?.anio.toString()
        view.album_viewer.text=ejemplo?.album
        Log.d("Hola", ejemplo?.name)
        Log.d("Hola", ejemplo?.artista)
        Log.d("Hola", ejemplo?.anio.toString())
        Log.d("Hola", ejemplo?.album)
    }
    interface OnFragmentInteractionListener {
        fun landscape(song:Cancion)
    }
    var listener:OnFragmentInteractionListener?=null

}
