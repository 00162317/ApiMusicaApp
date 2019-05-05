package com.example.apimusicaapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import com.example.apimusicaapp.R
import com.example.apimusicaapp.model.Cancion
import kotlinx.android.synthetic.main.activity_adapter.view.*

class Adapter(var items: List<Cancion>,val clickListener:(Cancion)->Unit) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int):
            ViewHolder = ViewHolder(
        LayoutInflater.from(p0.context)
            .inflate(R.layout.activity_adapter, p0, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.pasarValores(items[p1],clickListener)
    }

    fun setData(items: List<Cancion>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(var item: View) : RecyclerView.ViewHolder(item) {
        fun pasarValores(objeto: Cancion,eventoClick:(Cancion)->Unit) = with(item) {
            nombre_song.text = objeto.name
            artista_song.text = objeto.artista

            this.setOnClickListener { eventoClick(objeto) }
        }
    }

}
